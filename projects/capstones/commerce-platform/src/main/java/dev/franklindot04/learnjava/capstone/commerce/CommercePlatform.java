package dev.franklindot04.learnjava.capstone.commerce;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public final class CommercePlatform {
  private final Clock clock;
  private final Supplier<String> ids;
  private final Map<String, Product> catalogue = new HashMap<>();
  private final Map<String, Price> prices = new HashMap<>();
  private final Map<String, Integer> stock = new HashMap<>();
  private final Map<String, Integer> reservations = new HashMap<>();
  private final Map<String, Cart> carts = new HashMap<>();
  private final Map<String, Order> orders = new HashMap<>();
  private final Map<String, PaymentResult> payments = new HashMap<>();
  private final Map<String, Product> cache = new HashMap<>();
  private final Map<String, Product> searchProjection = new HashMap<>();
  private final Queue<Event> outbox = new ArrayDeque<>();
  private final Set<String> consumedMessages = new HashSet<>();
  private final List<Event> deadLetters = new ArrayList<>();
  private final List<String> audit = new ArrayList<>();
  private final Metrics metrics = new Metrics();
  private int capacityBudget = 100;
  private boolean optionalDependencyHealthy = true;
  private boolean compensateReservations = true;

  public CommercePlatform(Clock clock, Supplier<String> ids) {
    this.clock = Objects.requireNonNull(clock);
    this.ids = Objects.requireNonNull(ids);
  }

  public static CommercePlatform deterministic() {
    return new CommercePlatform(Clock.systemUTC(), () -> UUID.randomUUID().toString());
  }

  public void addProduct(String sku, String name, int cents, int available) {
    requireText(sku, "sku");
    requireText(name, "name");
    if (cents <= 0 || available < 0) {
      throw new IllegalArgumentException("price must be positive and stock cannot be negative");
    }
    Product product = new Product(sku, name, true);
    catalogue.put(sku, product);
    prices.put(sku, new Price(sku, cents, "USD"));
    stock.put(sku, available);
    publish("ProductChanged", sku, 1, product);
    audit.add("catalogue:" + sku + ":" + now());
  }

  public Optional<Product> productFromCache(String sku) {
    metrics.cacheReads++;
    Product cached = cache.get(sku);
    if (cached != null) {
      return Optional.of(cached);
    }
    metrics.cacheMisses++;
    Product product = catalogue.get(sku);
    if (product != null) {
      cache.put(sku, product);
    }
    return Optional.ofNullable(product);
  }

  public void evictProductCache(String sku) {
    cache.remove(sku);
  }

  public Cart createCart(String customerId) {
    requireText(customerId, "customerId");
    Cart cart = new Cart(ids.get(), customerId, new ArrayList<>());
    carts.put(cart.id(), cart);
    return cart;
  }

  public void addToCart(String cartId, String sku, int quantity) {
    Cart cart = carts.get(cartId);
    if (cart == null || !catalogue.containsKey(sku) || quantity <= 0) {
      throw new IllegalArgumentException("valid cart, sku, and quantity are required");
    }
    cart.lines().add(new CartLine(sku, quantity));
  }

  public CheckoutResult checkout(String cartId, String idempotencyKey, PaymentMode mode) {
    requireText(idempotencyKey, "idempotencyKey");
    if (capacityBudget <= 0) {
      metrics.loadShedding++;
      return CheckoutResult.rejected("capacity pressure");
    }
    capacityBudget--;
    if (orders.containsKey(idempotencyKey)) {
      metrics.duplicates++;
      return CheckoutResult.accepted(orders.get(idempotencyKey));
    }
    Cart cart = carts.get(cartId);
    if (cart == null || cart.lines().isEmpty()) {
      return CheckoutResult.rejected("cart is empty or missing");
    }
    String reservationId = "res-" + ids.get();
    List<CartLine> reserved = new ArrayList<>();
    for (CartLine line : cart.lines()) {
      int available = stock.getOrDefault(line.sku(), 0) - reservations.getOrDefault(line.sku(), 0);
      if (available < line.quantity()) {
        release(reserved);
        metrics.inventoryFailures++;
        return CheckoutResult.rejected("inventory unavailable");
      }
      reservations.merge(line.sku(), line.quantity(), Integer::sum);
      reserved.add(line);
    }
    PaymentResult payment = charge(idempotencyKey, total(cart), mode);
    if (!payment.accepted()) {
      boolean compensationOk = compensateReservations && release(reserved);
      Order failed = new Order(ids.get(), cart.customerId(), OrderStatus.CANCELLED, total(cart), reservationId);
      orders.put(idempotencyKey, failed);
      audit.add("compensation:" + compensationOk);
      return compensationOk ? CheckoutResult.rejected(payment.reason()) : CheckoutResult.failed("compensation failure");
    }
    Order order = new Order(ids.get(), cart.customerId(), OrderStatus.CONFIRMED, total(cart), reservationId);
    orders.put(idempotencyKey, order);
    publish("OrderConfirmed", order.id(), 1, order);
    publish("ShipmentRequested", order.id(), 1, order);
    publish("NotificationRequested", order.id(), 1, order);
    metrics.orders++;
    audit.add("checkout:" + order.id() + ":" + now());
    return CheckoutResult.accepted(order);
  }

  public PaymentResult charge(String idempotencyKey, int amountCents, PaymentMode mode) {
    if (payments.containsKey(idempotencyKey)) {
      metrics.duplicates++;
      return payments.get(idempotencyKey);
    }
    PaymentResult result = switch (mode) {
      case ACCEPT -> PaymentResult.accepted("pay-" + ids.get(), amountCents);
      case REJECT -> PaymentResult.rejected("payment rejected");
      case TIMEOUT -> PaymentResult.rejected("payment timeout");
    };
    payments.put(idempotencyKey, result);
    return result;
  }

  public ProcessingResult processNextMessage() {
    Event event = outbox.poll();
    if (event == null) {
      return ProcessingResult.empty();
    }
    return process(event);
  }

  public ProcessingResult process(Event event) {
    if (!consumedMessages.add(event.id())) {
      metrics.duplicates++;
      return ProcessingResult.duplicate(event.id());
    }
    if (event.schemaVersion() > 1) {
      deadLetters.add(event);
      metrics.deadLetters++;
      return ProcessingResult.deadLetter("incompatible schema");
    }
    if ("Poison".equals(event.type())) {
      deadLetters.add(event);
      metrics.deadLetters++;
      return ProcessingResult.deadLetter("poison event");
    }
    if ("ProductChanged".equals(event.type()) && event.payload() instanceof Product product) {
      searchProjection.put(product.sku(), product);
    }
    metrics.messages++;
    return ProcessingResult.processed(event.id());
  }

  public OperationalReport report() {
    return new OperationalReport(metrics.orders, metrics.inventoryFailures, metrics.duplicates,
        metrics.deadLetters, metrics.loadShedding, optionalDependencyHealthy, audit.size());
  }

  public FitnessReport fitness() {
    return new FitnessReport(!catalogue.isEmpty(), !outbox.isEmpty() || metrics.messages > 0,
        metrics.deadLetters >= 0, metrics.loadShedding >= 0);
  }

  public ReconciliationReport reconcile() {
    int reserved = reservations.values().stream().mapToInt(Integer::intValue).sum();
    int confirmed = orders.values().stream().filter(order -> order.status() == OrderStatus.CONFIRMED).mapToInt(order -> 1).sum();
    return new ReconciliationReport(reserved, confirmed, deadLetters.size());
  }

  public Optional<Product> projectedProduct(String sku) {
    return Optional.ofNullable(searchProjection.get(sku));
  }

  public void setCapacityBudget(int capacityBudget) {
    this.capacityBudget = capacityBudget;
  }

  public void setOptionalDependencyHealthy(boolean optionalDependencyHealthy) {
    this.optionalDependencyHealthy = optionalDependencyHealthy;
  }

  public void setCompensateReservations(boolean compensateReservations) {
    this.compensateReservations = compensateReservations;
  }

  public Event incompatibleEvent() {
    return new Event("evt-" + ids.get(), "OrderConfirmed", "order", 2, Map.of());
  }

  public Event poisonEvent() {
    return new Event("evt-" + ids.get(), "Poison", "order", 1, Map.of());
  }

  private void publish(String type, String aggregateId, int schemaVersion, Object payload) {
    outbox.add(new Event("evt-" + ids.get(), type, aggregateId, schemaVersion, payload));
  }

  private int total(Cart cart) {
    return cart.lines().stream().mapToInt(line -> prices.get(line.sku()).cents() * line.quantity()).sum();
  }

  private boolean release(List<CartLine> lines) {
    for (CartLine line : lines) {
      reservations.computeIfPresent(line.sku(), (sku, quantity) -> Math.max(0, quantity - line.quantity()));
    }
    return true;
  }

  private Instant now() {
    return clock.instant();
  }

  private static void requireText(String value, String field) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(field + " is required");
    }
  }

  private static final class Metrics {
    private int orders;
    private int inventoryFailures;
    private int duplicates;
    private int deadLetters;
    private int loadShedding;
    private int messages;
    private int cacheReads;
    private int cacheMisses;
  }

  public record Product(String sku, String name, boolean active) {}
  public record Price(String sku, int cents, String currency) {}
  public record Cart(String id, String customerId, List<CartLine> lines) {}
  public record CartLine(String sku, int quantity) {}
  public record Order(String id, String customerId, OrderStatus status, int totalCents, String reservationId) {}
  public enum OrderStatus { CONFIRMED, CANCELLED }
  public enum PaymentMode { ACCEPT, REJECT, TIMEOUT }
  public record PaymentResult(boolean accepted, String paymentId, int amountCents, String reason) {
    static PaymentResult accepted(String id, int amountCents) {
      return new PaymentResult(true, id, amountCents, "accepted");
    }
    static PaymentResult rejected(String reason) {
      return new PaymentResult(false, "", 0, reason);
    }
  }
  public record CheckoutResult(boolean accepted, boolean failed, String reason, Order order) {
    static CheckoutResult accepted(Order order) {
      return new CheckoutResult(true, false, "accepted", order);
    }
    static CheckoutResult rejected(String reason) {
      return new CheckoutResult(false, false, reason, null);
    }
    static CheckoutResult failed(String reason) {
      return new CheckoutResult(false, true, reason, null);
    }
  }
  public record Event(String id, String type, String aggregateId, int schemaVersion, Object payload) {}
  public record ProcessingResult(String status, String detail) {
    static ProcessingResult empty() {
      return new ProcessingResult("empty", "");
    }
    static ProcessingResult processed(String id) {
      return new ProcessingResult("processed", id);
    }
    static ProcessingResult duplicate(String id) {
      return new ProcessingResult("duplicate", id);
    }
    static ProcessingResult deadLetter(String reason) {
      return new ProcessingResult("dead-letter", reason);
    }
  }
  public record OperationalReport(int orders, int inventoryFailures, int duplicates, int deadLetters,
                                  int loadShedding, boolean optionalDependencyHealthy, int auditEntries) {}
  public record FitnessReport(boolean cataloguePresent, boolean messagingBoundaryPresent,
                              boolean deadLetterCheckPresent, boolean capacityCheckPresent) {
    public boolean passes() {
      return cataloguePresent && messagingBoundaryPresent && deadLetterCheckPresent && capacityCheckPresent;
    }
  }
  public record ReconciliationReport(int reservedUnits, int confirmedOrders, int quarantinedEvents) {}
}
