package dev.franklindot04.learnjava.commercearchitecture;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

public final class CommerceArchitecture {
    private CommerceArchitecture() {
    }

    public record Product(String id, String name, int priceCents, int version) {
    }

    public record CartLine(String productId, int quantity) {
    }

    public record CheckoutRequest(String customerId, List<CartLine> lines, String paymentKey) {
    }

    public enum CheckoutStatus {
        CONFIRMED, DEGRADED_CONFIRMED, REJECTED, COMPENSATED, NEEDS_RECONCILIATION
    }

    public record CheckoutResult(CheckoutStatus status, String orderId, int totalCents, List<String> notes) {
    }

    public record Event(String id, String type, String aggregateId, int attempt) {
        Event nextAttempt() {
            return new Event(id, type, aggregateId, attempt + 1);
        }
    }

    public static final class CatalogModule {
        private final Map<String, Product> products = new HashMap<>();

        public void upsert(Product product) {
            products.put(product.id(), product);
        }

        public Optional<Product> find(String id) {
            return Optional.ofNullable(products.get(id));
        }
    }

    public static final class SearchProjection {
        private final Map<String, Product> indexed = new HashMap<>();
        private final Queue<Product> pending = new ArrayDeque<>();
        private long lastLagMillis;

        public void acceptChange(Product product) {
            pending.add(product);
            lastLagMillis += 100;
        }

        public Optional<Product> search(String id) {
            return Optional.ofNullable(indexed.get(id));
        }

        public void catchUp() {
            while (!pending.isEmpty()) {
                Product product = pending.remove();
                indexed.put(product.id(), product);
            }
            lastLagMillis = 0;
        }

        public void rebuildFrom(List<Product> products) {
            indexed.clear();
            products.forEach(product -> indexed.put(product.id(), product));
            lastLagMillis = 0;
        }

        public long lagMillis() {
            return lastLagMillis;
        }
    }

    public static final class PricingModule {
        public int price(Product product, int quantity) {
            return product.priceCents() * quantity;
        }
    }

    public static final class InventoryModule {
        private final Map<String, Integer> available = new HashMap<>();

        public void stock(String productId, int quantity) {
            available.put(productId, quantity);
        }

        public synchronized boolean reserve(String productId, int quantity) {
            int current = available.getOrDefault(productId, 0);
            if (current < quantity) {
                return false;
            }
            available.put(productId, current - quantity);
            return true;
        }

        public synchronized void release(String productId, int quantity) {
            available.merge(productId, quantity, Integer::sum);
        }

        public int available(String productId) {
            return available.getOrDefault(productId, 0);
        }
    }

    public static final class PaymentGateway {
        private final Map<String, String> results = new HashMap<>();
        private int sideEffects;

        public String authorize(String key, boolean succeeds) {
            if (results.containsKey(key)) {
                return results.get(key);
            }
            sideEffects++;
            String result = succeeds ? "AUTHORIZED" : "DECLINED";
            results.put(key, result);
            return result;
        }

        public int sideEffects() {
            return sideEffects;
        }
    }

    public static final class OrderModule {
        private final Map<String, CheckoutResult> orders = new LinkedHashMap<>();
        private int sequence;

        public CheckoutResult confirm(int totalCents, List<String> notes) {
            String orderId = "order-" + (++sequence);
            CheckoutResult result = new CheckoutResult(CheckoutStatus.CONFIRMED, orderId, totalCents, notes);
            orders.put(orderId, result);
            return result;
        }

        public Optional<CheckoutResult> find(String orderId) {
            return Optional.ofNullable(orders.get(orderId));
        }
    }

    public static final class Outbox {
        private final Queue<Event> events = new ArrayDeque<>();

        public void add(Event event) {
            events.add(event);
        }

        public Optional<Event> poll() {
            return Optional.ofNullable(events.poll());
        }

        public int size() {
            return events.size();
        }
    }

    public static final class ProcessedMessageStore {
        private final Set<String> processed = new HashSet<>();

        public boolean first(String id) {
            return processed.add(id);
        }
    }

    public static final class DeadLetterHandling {
        private final List<Event> deadLetters = new ArrayList<>();

        public void handle(Event event, ProcessedMessageStore store, int maxAttempts, Supplier<Boolean> handler) {
            if (!store.first(event.id() + "-" + event.attempt())) {
                return;
            }
            if (handler.get()) {
                return;
            }
            if (event.attempt() + 1 >= maxAttempts) {
                deadLetters.add(event.nextAttempt());
            }
        }

        public List<Event> deadLetters() {
            return List.copyOf(deadLetters);
        }
    }

    public static final class Cache<K, V> {
        private final Map<K, V> values = new HashMap<>();
        private final Set<K> loading = new HashSet<>();
        private int backendLoads;

        public V get(K key, Supplier<V> loader) {
            if (values.containsKey(key)) {
                return values.get(key);
            }
            if (loading.contains(key)) {
                return null;
            }
            loading.add(key);
            backendLoads++;
            V value = loader.get();
            values.put(key, value);
            loading.remove(key);
            return value;
        }

        public void expire(K key) {
            values.remove(key);
        }

        public int backendLoads() {
            return backendLoads;
        }
    }

    public static final class PartitionRouter {
        private final int partitions;
        private final Map<Integer, Integer> counts = new HashMap<>();

        public PartitionRouter(int partitions) {
            this.partitions = partitions;
        }

        public int route(String key) {
            int partition = Math.floorMod(key.hashCode(), partitions);
            counts.merge(partition, 1, Integer::sum);
            return partition;
        }

        public List<Integer> hotPartitions(int threshold) {
            return counts.entrySet().stream()
                    .filter(entry -> entry.getValue() >= threshold)
                    .map(Map.Entry::getKey)
                    .sorted()
                    .toList();
        }
    }

    public record Region(String name, boolean available, Duration replicationLag, boolean dataResidencyAllowed) {
    }

    public record FailoverDecision(boolean allowed, String reason) {
    }

    public static final class RegionalFailoverModel {
        public FailoverDecision evaluate(Region primary, Region secondary, Duration rpo, Duration rto) {
            if (primary.available()) {
                return new FailoverDecision(false, "primary-healthy");
            }
            if (!secondary.available()) {
                return new FailoverDecision(false, "secondary-unavailable");
            }
            if (!secondary.dataResidencyAllowed()) {
                return new FailoverDecision(false, "data-residency-blocked");
            }
            if (secondary.replicationLag().compareTo(rpo) > 0) {
                return new FailoverDecision(false, "rpo-exceeded");
            }
            if (rto.isNegative() || rto.isZero()) {
                return new FailoverDecision(false, "rto-invalid");
            }
            return new FailoverDecision(true, "manual-failover-approved");
        }
    }

    public record CapacityReport(int offeredRps, int safeRps, int shedRps, int preservedCriticalRps) {
    }

    public static CapacityReport capacity(int offeredRps, int safeRps, int criticalRps) {
        int shed = Math.max(0, offeredRps - safeRps);
        return new CapacityReport(offeredRps, safeRps, shed, Math.min(criticalRps, safeRps));
    }

    public record Contract(String name, Set<String> requiredFields) {
    }

    public static boolean compatible(Contract oldContract, Contract newContract) {
        return newContract.requiredFields().containsAll(oldContract.requiredFields());
    }

    public record Metrics(int degradedDependencies, int deadLetters, int reconciliations, int loadShed) {
    }

    public record ArchitectureReport(String summary, Metrics metrics, List<String> risks) {
    }

    public static final class ReconciliationJob {
        public List<String> reconcile(Map<String, Integer> source, Map<String, Integer> projection) {
            List<String> actions = new ArrayList<>();
            source.forEach((key, value) -> {
                if (!value.equals(projection.get(key))) {
                    projection.put(key, value);
                    actions.add("repair:" + key);
                }
            });
            return actions.stream().sorted(Comparator.naturalOrder()).toList();
        }
    }

    public static final class CheckoutModule {
        private final CatalogModule catalog;
        private final PricingModule pricing;
        private final InventoryModule inventory;
        private final PaymentGateway payment;
        private final OrderModule orders;
        private final Outbox outbox;

        public CheckoutModule(CatalogModule catalog, PricingModule pricing, InventoryModule inventory,
                              PaymentGateway payment, OrderModule orders, Outbox outbox) {
            this.catalog = catalog;
            this.pricing = pricing;
            this.inventory = inventory;
            this.payment = payment;
            this.orders = orders;
            this.outbox = outbox;
        }

        public CheckoutResult checkout(CheckoutRequest request, boolean paymentSucceeds, boolean optionalNotificationAvailable) {
            List<String> notes = new ArrayList<>();
            int total = 0;
            for (CartLine line : request.lines()) {
                Product product = catalog.find(line.productId()).orElse(null);
                if (product == null) {
                    return new CheckoutResult(CheckoutStatus.REJECTED, "", 0, List.of("product-not-found"));
                }
                if (!inventory.reserve(product.id(), line.quantity())) {
                    return new CheckoutResult(CheckoutStatus.REJECTED, "", 0, List.of("inventory-unavailable"));
                }
                total += pricing.price(product, line.quantity());
            }
            if (!"AUTHORIZED".equals(payment.authorize(request.paymentKey(), paymentSucceeds))) {
                request.lines().forEach(line -> inventory.release(line.productId(), line.quantity()));
                return new CheckoutResult(CheckoutStatus.COMPENSATED, "", total, List.of("payment-declined", "inventory-released"));
            }
            if (!optionalNotificationAvailable) {
                notes.add("notification-degraded");
            }
            CheckoutResult confirmed = orders.confirm(total, notes);
            outbox.add(new Event("event-" + confirmed.orderId(), "OrderConfirmed", confirmed.orderId(), 0));
            return notes.isEmpty()
                    ? confirmed
                    : new CheckoutResult(CheckoutStatus.DEGRADED_CONFIRMED, confirmed.orderId(), total, notes);
        }
    }

    public static CheckoutModule checkoutSystem(CatalogModule catalog, InventoryModule inventory,
                                                PaymentGateway payment, OrderModule orders, Outbox outbox) {
        return new CheckoutModule(catalog, new PricingModule(), inventory, payment, orders, outbox);
    }
}
