package dev.franklindot04.learnjava.capstone.commerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class CommercePlatformTest {
  private final AtomicInteger sequence = new AtomicInteger();
  private final CommercePlatform platform = new CommercePlatform(
      Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC),
      () -> "id-" + sequence.incrementAndGet());

  @Test
  void successfulCheckoutPublishesOrderShipmentAndNotificationEvents() {
    String cart = stockedCart(2, 1);
    CommercePlatform.CheckoutResult result = platform.checkout(cart, "checkout-1", CommercePlatform.PaymentMode.ACCEPT);

    assertTrue(result.accepted());
    assertEquals(CommercePlatform.OrderStatus.CONFIRMED, result.order().status());
    assertEquals("processed", platform.processNextMessage().status());
    assertEquals("processed", platform.processNextMessage().status());
    assertEquals(1, platform.report().orders());
  }

  @Test
  void rejectsInventoryUnavailableAndConcurrentContention() {
    String first = stockedCart(1, 1);
    String second = platform.createCart("customer-b").id();
    platform.addToCart(second, "sku-1", 1);

    assertTrue(platform.checkout(first, "checkout-2", CommercePlatform.PaymentMode.ACCEPT).accepted());
    assertFalse(platform.checkout(second, "checkout-3", CommercePlatform.PaymentMode.ACCEPT).accepted());
    assertEquals(1, platform.report().inventoryFailures());
  }

  @Test
  void deduplicatesCheckoutAndPaymentRequests() {
    String cart = stockedCart(3, 1);
    CommercePlatform.CheckoutResult first = platform.checkout(cart, "same-key", CommercePlatform.PaymentMode.ACCEPT);
    CommercePlatform.CheckoutResult second = platform.checkout(cart, "same-key", CommercePlatform.PaymentMode.ACCEPT);
    CommercePlatform.PaymentResult payment = platform.charge("same-key", 999, CommercePlatform.PaymentMode.ACCEPT);

    assertEquals(first.order().id(), second.order().id());
    assertEquals(first.order().totalCents(), payment.amountCents());
    assertTrue(platform.report().duplicates() >= 2);
  }

  @Test
  void compensatesPaymentRejectionTimeoutAndReportsCompensationFailure() {
    String rejectedCart = stockedCart(5, 1);
    String timeoutCart = platform.createCart("customer-timeout").id();
    platform.addToCart(timeoutCart, "sku-1", 1);
    String failedCart = platform.createCart("customer-failed").id();
    platform.addToCart(failedCart, "sku-1", 1);

    assertEquals("payment rejected", platform.checkout(rejectedCart, "reject", CommercePlatform.PaymentMode.REJECT).reason());
    assertEquals("payment timeout", platform.checkout(timeoutCart, "timeout", CommercePlatform.PaymentMode.TIMEOUT).reason());
    platform.setCompensateReservations(false);
    assertTrue(platform.checkout(failedCart, "compensation", CommercePlatform.PaymentMode.REJECT).failed());
  }

  @Test
  void surfacesStaleCacheAndSearchProjectionLag() {
    platform.addProduct("sku-1", "Old name", 1000, 2);
    assertEquals("Old name", platform.productFromCache("sku-1").orElseThrow().name());
    platform.addProduct("sku-1", "New name", 1000, 2);

    assertEquals("Old name", platform.productFromCache("sku-1").orElseThrow().name());
    assertTrue(platform.projectedProduct("sku-1").isEmpty());
    platform.evictProductCache("sku-1");
    assertEquals("New name", platform.productFromCache("sku-1").orElseThrow().name());
  }

  @Test
  void handlesRedeliveryPoisonEventsAndSchemaEvolution() {
    CommercePlatform.Event poison = platform.poisonEvent();
    CommercePlatform.Event incompatible = platform.incompatibleEvent();

    assertEquals("dead-letter", platform.process(poison).status());
    assertEquals("duplicate", platform.process(poison).status());
    assertEquals("dead-letter", platform.process(incompatible).status());
    assertEquals(2, platform.report().deadLetters());
  }

  @Test
  void reconciliationDependencyDegradationCapacityAndRecoveryAreObservable() {
    String cart = stockedCart(2, 1);
    platform.setOptionalDependencyHealthy(false);
    platform.setCapacityBudget(1);

    assertTrue(platform.checkout(cart, "limited-1", CommercePlatform.PaymentMode.ACCEPT).accepted());
    assertFalse(platform.checkout(cart, "limited-2", CommercePlatform.PaymentMode.ACCEPT).accepted());
    assertFalse(platform.report().optionalDependencyHealthy());
    assertEquals(1, platform.reconcile().confirmedOrders());
    assertTrue(platform.fitness().passes());
  }

  @Test
  void compatibleSchemaEventsUpdateProjectionAndRestartStyleReplayIsIdempotent() {
    platform.addProduct("sku-9", "Replayable", 100, 1);
    CommercePlatform.ProcessingResult first = platform.processNextMessage();
    CommercePlatform.Event replayed = new CommercePlatform.Event(first.detail(), "ProductChanged", "sku-9", 1,
        new CommercePlatform.Product("sku-9", "Replayable", true));

    assertEquals("processed", first.status());
    assertEquals("duplicate", platform.process(replayed).status());
    assertEquals("Replayable", platform.projectedProduct("sku-9").orElseThrow().name());
  }

  private String stockedCart(int stock, int quantity) {
    platform.addProduct("sku-1", "Java Mug", 1500, stock);
    String cart = platform.createCart("customer-a").id();
    platform.addToCart(cart, "sku-1", quantity);
    return cart;
  }
}
