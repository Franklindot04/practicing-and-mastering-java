package dev.franklindot04.learnjava.systemdesign;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ArchitecturePatternsTest {
    @Test
    void modularMonolithExposesPortsAndPublishesEvents() {
        var monolith = new ArchitecturePatterns.ModularMonolith();

        var customer = monolith.customers().findCustomer("42");
        var receipt = monolith.orders().placeOrder(customer.customerId(), 3);

        assertEquals("customer-42", customer.displayName());
        assertEquals("42", receipt.customerId());
        assertEquals(List.of(new ArchitecturePatterns.DomainEvent("OrderPlaced", receipt.orderId())), monolith.events());
    }

    @Test
    void apiAggregationUsesBudgetFallbacksCircuitStateAndCorrelation() {
        var profile = new ArchitecturePatterns.DownstreamClient("profile", Duration.ofMillis(20), false);
        var recommendations = new ArchitecturePatterns.DownstreamClient("recommendations", Duration.ofMillis(90), false);
        var payment = new ArchitecturePatterns.DownstreamClient("payment", Duration.ofMillis(10), true);
        var aggregator = new ArchitecturePatterns.ApiAggregator(List.of(profile, recommendations, payment));

        var response = aggregator.aggregate("corr-123", Duration.ofMillis(120));

        assertEquals("corr-123", response.correlationId());
        assertEquals("value-profile-corr-123", response.sections().get("profile"));
        assertEquals("fallback-recommendations", response.sections().get("recommendations"));
        assertEquals("fallback-payment", response.sections().get("payment"));
        assertEquals(List.of("recommendations", "payment"), response.degraded());
        assertTrue(payment.circuitOpen());
    }

    @Test
    void cacheAsideHandlesHitsMissesExpiryInvalidationNegativeValuesAndStampedePrevention() {
        var cache = new ArchitecturePatterns.CacheAside<String, String>();

        assertEquals(Optional.of("catalogue"), cache.get("p1", 0, Duration.ofMillis(100), () -> Optional.of("catalogue")));
        assertEquals(Optional.of("catalogue"), cache.get("p1", 50, Duration.ofMillis(100), Optional::empty));
        assertEquals(Optional.of("new-catalogue"), cache.get("p1", 150, Duration.ofMillis(100), () -> Optional.of("new-catalogue")));
        cache.invalidate("p1");
        assertEquals(Optional.empty(), cache.get("missing", 200, Duration.ofMillis(100), Optional::empty));
        assertEquals(Optional.empty(), cache.get("missing", 250, Duration.ofMillis(100), () -> Optional.of("should-not-load")));
        cache.put("hot", "stale", 10);
        assertEquals(Optional.of("stale"), cache.staleWhileLoading("hot"));
        assertEquals(Optional.of("stale"), cache.get("hot", 20, Duration.ofMillis(100), () -> Optional.of("fresh")));

        var metrics = cache.metrics();
        assertEquals(2, metrics.hits());
        assertEquals(4, metrics.misses());
        assertEquals(3, metrics.backendLoads());
        assertEquals(2, metrics.staleResponses());
        assertEquals(1, metrics.negativeHits());
    }

    @Test
    void partitionedRepositoryRoutesDetectsHotPartitionsAndPlansRebalance() {
        var repository = new ArchitecturePatterns.PartitionedRepository(4);

        int partition = repository.route("tenant-a");
        repository.route("tenant-a");
        repository.route("tenant-a");
        repository.route("tenant-b");

        assertEquals(Math.floorMod("tenant-a".hashCode(), 4), partition);
        assertEquals(List.of(partition), repository.hotPartitions(3));
        assertEquals(4, repository.crossPartitionQueryCost());
        assertFalse(repository.rebalancePlan(8, List.of("tenant-a", "tenant-b")).isEmpty());
    }

    @Test
    void sagaRecordsSuccessCompensationIdempotencyAndReconciliation() {
        var saga = new ArchitecturePatterns.CheckoutSaga(2);

        assertEquals(ArchitecturePatterns.SagaStatus.COMPLETED, saga.checkout("checkout-1", true, true));
        assertEquals(ArchitecturePatterns.SagaStatus.COMPENSATED, saga.checkout("checkout-2", false, true));
        assertEquals(ArchitecturePatterns.SagaStatus.COMPLETED, saga.checkout("checkout-1", true, true));
        assertEquals(ArchitecturePatterns.SagaStatus.NEEDS_RECONCILIATION, saga.checkout("checkout-3", false, false));

        assertEquals(0, saga.availableInventory());
        assertTrue(saga.audit().contains("released:checkout-2"));
        assertTrue(saga.audit().contains("duplicate:checkout-1"));
        assertTrue(saga.audit().contains("compensation-failed:checkout-3"));
    }

    @Test
    void architectureFitnessChecksDetectBoundaryContractRetryAndSecurityViolations() {
        var fitness = new ArchitecturePatterns.ArchitectureFitness();

        var violations = fitness.evaluate(
                List.of("orders.api.OrderPort", "orders.internal.OrderRepository"),
                List.of("OrderPort", "PaymentInternalRepository"),
                new ArchitecturePatterns.ApiContract("CreateOrder", ArchitecturePatterns.set("orderId", "customerId")),
                new ArchitecturePatterns.ApiContract("CreateOrder", ArchitecturePatterns.set("orderId")),
                new ArchitecturePatterns.RetryPolicy(false, 3),
                List.of(new ArchitecturePatterns.SecurityBoundary("adminRefund", false)));

        assertEquals(List.of(
                "forbidden dependency: orders.internal.OrderRepository",
                "incompatible contract: CreateOrder",
                "interface bypass: PaymentInternalRepository",
                "missing security boundary: adminRefund",
                "unsafe retry configuration"), violations);
    }
}
