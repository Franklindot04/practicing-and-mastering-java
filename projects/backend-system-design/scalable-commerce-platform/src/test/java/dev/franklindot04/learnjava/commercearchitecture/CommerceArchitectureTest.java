package dev.franklindot04.learnjava.commercearchitecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CommerceArchitectureTest {
    @Test
    void normalCheckoutConfirmsOrderAndWritesOutbox() {
        var system = fixture(5);

        var result = system.checkout.checkout(request("pay-1"), true, true);

        assertEquals(CommerceArchitecture.CheckoutStatus.CONFIRMED, result.status());
        assertEquals(2500, result.totalCents());
        assertEquals(4, system.inventory.available("sku-1"));
        assertEquals(1, system.outbox.size());
    }

    @Test
    void inventoryContentionPreservesInvariant() {
        var system = fixture(1);

        var first = system.checkout.checkout(request("pay-1"), true, true);
        var second = system.checkout.checkout(request("pay-2"), true, true);

        assertEquals(CommerceArchitecture.CheckoutStatus.CONFIRMED, first.status());
        assertEquals(CommerceArchitecture.CheckoutStatus.REJECTED, second.status());
        assertEquals(0, system.inventory.available("sku-1"));
    }

    @Test
    void duplicatePaymentUsesStoredResult() {
        var payment = new CommerceArchitecture.PaymentGateway();

        assertEquals("AUTHORIZED", payment.authorize("same-key", true));
        assertEquals("AUTHORIZED", payment.authorize("same-key", false));
        assertEquals(1, payment.sideEffects());
    }

    @Test
    void optionalDependencyFailureProducesDegradedConfirmedResult() {
        var system = fixture(3);

        var result = system.checkout.checkout(request("pay-1"), true, false);

        assertEquals(CommerceArchitecture.CheckoutStatus.DEGRADED_CONFIRMED, result.status());
        assertEquals(List.of("notification-degraded"), result.notes());
    }

    @Test
    void cacheStampedeBoundsBackendLoad() {
        var cache = new CommerceArchitecture.Cache<String, String>();

        assertEquals("value", cache.get("catalogue", () -> "value"));
        cache.expire("catalogue");
        assertEquals("value-2", cache.get("catalogue", () -> "value-2"));
        assertEquals("value-2", cache.get("catalogue", () -> "unused"));

        assertEquals(2, cache.backendLoads());
    }

    @Test
    void searchProjectionLagIsMeasuredAndCaughtUp() {
        var search = new CommerceArchitecture.SearchProjection();
        var product = new CommerceArchitecture.Product("sku-1", "Notebook", 2500, 1);

        search.acceptChange(product);

        assertTrue(search.search("sku-1").isEmpty());
        assertEquals(100, search.lagMillis());
        search.catchUp();
        assertEquals(product, search.search("sku-1").orElseThrow());
        assertEquals(0, search.lagMillis());
    }

    @Test
    void checkoutSagaFailureReleasesInventory() {
        var system = fixture(2);

        var result = system.checkout.checkout(request("pay-1"), false, true);

        assertEquals(CommerceArchitecture.CheckoutStatus.COMPENSATED, result.status());
        assertEquals(2, system.inventory.available("sku-1"));
        assertEquals(List.of("payment-declined", "inventory-released"), result.notes());
    }

    @Test
    void poisonEventIsQuarantinedAfterRepeatedFailures() {
        var handler = new CommerceArchitecture.DeadLetterHandling();
        var store = new CommerceArchitecture.ProcessedMessageStore();
        var event = new CommerceArchitecture.Event("evt-1", "OrderConfirmed", "order-1", 0);

        handler.handle(event, store, 3, () -> false);
        handler.handle(event.nextAttempt(), store, 3, () -> false);
        handler.handle(event.nextAttempt().nextAttempt(), store, 3, () -> false);

        assertEquals(1, handler.deadLetters().size());
        assertEquals("evt-1", handler.deadLetters().get(0).id());
    }

    @Test
    void regionalFailureRejectsUnsafeFailoverAndAllowsSafeOne() {
        var model = new CommerceArchitecture.RegionalFailoverModel();
        var primaryDown = new CommerceArchitecture.Region("primary", false, Duration.ZERO, true);

        var unsafe = model.evaluate(primaryDown,
                new CommerceArchitecture.Region("secondary", true, Duration.ofMinutes(10), true),
                Duration.ofMinutes(2),
                Duration.ofMinutes(15));
        var safe = model.evaluate(primaryDown,
                new CommerceArchitecture.Region("secondary", true, Duration.ofSeconds(20), true),
                Duration.ofMinutes(2),
                Duration.ofMinutes(15));

        assertFalse(unsafe.allowed());
        assertEquals("rpo-exceeded", unsafe.reason());
        assertTrue(safe.allowed());
    }

    @Test
    void capacityPressureActivatesLoadSheddingAndPreservesCriticalCapacity() {
        var report = CommerceArchitecture.capacity(1200, 900, 500);

        assertEquals(300, report.shedRps());
        assertEquals(500, report.preservedCriticalRps());
    }

    @Test
    void schemaEvolutionAcceptsCompatibleContractAndRejectsBreakingContract() {
        var oldContract = new CommerceArchitecture.Contract("OrderConfirmed", Set.of("orderId", "customerId"));
        var compatible = new CommerceArchitecture.Contract("OrderConfirmed", Set.of("orderId", "customerId", "currency"));
        var breaking = new CommerceArchitecture.Contract("OrderConfirmed", Set.of("orderId"));

        assertTrue(CommerceArchitecture.compatible(oldContract, compatible));
        assertFalse(CommerceArchitecture.compatible(oldContract, breaking));
    }

    @Test
    void reconciliationRepairsProjectionWithAuditableActions() {
        var source = Map.of("order-1", 2500, "order-2", 1200);
        var projection = new HashMap<>(Map.of("order-1", 2000, "order-2", 1200));
        var job = new CommerceArchitecture.ReconciliationJob();

        var actions = job.reconcile(source, projection);

        assertEquals(List.of("repair:order-1"), actions);
        assertEquals(source, projection);
    }

    @Test
    void partitionRouterDetectsHotPartitions() {
        var router = new CommerceArchitecture.PartitionRouter(4);
        int partition = router.route("tenant-large");
        router.route("tenant-large");
        router.route("tenant-large");

        assertEquals(List.of(partition), router.hotPartitions(3));
    }

    private static CommerceArchitecture.CheckoutRequest request(String paymentKey) {
        return new CommerceArchitecture.CheckoutRequest("customer-1",
                List.of(new CommerceArchitecture.CartLine("sku-1", 1)),
                paymentKey);
    }

    private static Fixture fixture(int inventoryCount) {
        var catalog = new CommerceArchitecture.CatalogModule();
        catalog.upsert(new CommerceArchitecture.Product("sku-1", "Notebook", 2500, 1));
        var inventory = new CommerceArchitecture.InventoryModule();
        inventory.stock("sku-1", inventoryCount);
        var payment = new CommerceArchitecture.PaymentGateway();
        var orders = new CommerceArchitecture.OrderModule();
        var outbox = new CommerceArchitecture.Outbox();
        var checkout = CommerceArchitecture.checkoutSystem(catalog, inventory, payment, orders, outbox);
        return new Fixture(inventory, outbox, checkout);
    }

    private record Fixture(CommerceArchitecture.InventoryModule inventory, CommerceArchitecture.Outbox outbox,
                           CommerceArchitecture.CheckoutModule checkout) {
    }
}
