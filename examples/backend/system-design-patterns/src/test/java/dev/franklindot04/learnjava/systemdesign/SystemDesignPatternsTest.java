package dev.franklindot04.learnjava.systemdesign;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SystemDesignPatternsTest {
    @Test
    void cacheAsideLoadsOnceUntilInvalidated() {
        AtomicInteger loads = new AtomicInteger();
        CacheAside<String, String> cache = new CacheAside<>(key -> {
            loads.incrementAndGet();
            return Optional.of("value-" + key);
        });

        assertEquals(Optional.of("value-a"), cache.get("a"));
        assertEquals(Optional.of("value-a"), cache.get("a"));
        assertEquals(1, loads.get());

        cache.invalidate("a");

        assertEquals(Optional.of("value-a"), cache.get("a"));
        assertEquals(2, loads.get());
    }

    @Test
    void fixedWindowLimiterRejectsAfterLimit() {
        Clock fixed = Clock.fixed(Instant.parse("2026-07-26T00:00:00Z"), ZoneOffset.UTC);
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(2, Duration.ofMinutes(1), fixed);

        assertTrue(limiter.allow("client-a"));
        assertTrue(limiter.allow("client-a"));
        assertFalse(limiter.allow("client-a"));
        assertTrue(limiter.allow("client-b"));
    }

    @Test
    void circuitBreakerOpensAfterRepeatedFailuresAndUsesFallback() {
        CircuitBreaker breaker = new CircuitBreaker(2);

        assertEquals("fallback", breaker.call(() -> {
            throw new IllegalStateException("down");
        }, () -> "fallback"));
        assertEquals(CircuitBreaker.State.CLOSED, breaker.state());
        assertEquals("fallback", breaker.call(() -> {
            throw new IllegalStateException("down");
        }, () -> "fallback"));

        assertEquals(CircuitBreaker.State.OPEN, breaker.state());
        assertEquals("fallback", breaker.call(() -> "primary", () -> "fallback"));

        breaker.allowProbe();

        assertEquals("primary", breaker.call(() -> "primary", () -> "fallback"));
        assertEquals(CircuitBreaker.State.CLOSED, breaker.state());
    }

    @Test
    void retryPolicyStopsAfterLimit() {
        AtomicInteger attempts = new AtomicInteger();
        RetryPolicy retry = new RetryPolicy(3);

        assertEquals("ok", retry.execute(() -> attempts.incrementAndGet() == 3 ? "ok" : failNow()));
        assertEquals(3, attempts.get());
        assertThrows(IllegalStateException.class, () -> new RetryPolicy(1).execute(this::failNow));
    }

    @Test
    void idempotencyStoreReturnsOriginalResultForDuplicateKey() {
        AtomicInteger executions = new AtomicInteger();
        InMemoryIdempotencyStore store = new InMemoryIdempotencyStore();

        assertEquals("created-1", store.deduplicate("request-1", () -> "created-" + executions.incrementAndGet()));
        assertEquals("created-1", store.deduplicate("request-1", () -> "created-" + executions.incrementAndGet()));
        assertEquals(1, executions.get());
    }

    @Test
    void bulkheadRejectsWhenNoPermitIsAvailable() {
        Bulkhead bulkhead = new Bulkhead(1);

        Optional<String> result = bulkhead.tryCall(() -> {
            Optional<String> nested = bulkhead.tryCall(() -> "nested");
            assertTrue(nested.isEmpty());
            return "outer";
        });

        assertEquals(Optional.of("outer"), result);
    }

    @Test
    void partitionRouterChoosesKnownPartitionForSameKey() {
        PartitionRouter router = new PartitionRouter(List.of("p0", "p1", "p2"), 3);

        assertEquals(router.route("customer-123"), router.route("customer-123"));
        assertTrue(router.partitions().contains(router.route("customer-123")));
    }

    @Test
    void readAndWriteServicesShareStoreThroughSeparateInterfaces() {
        TaskServices.InMemoryTaskStore store = new TaskServices.InMemoryTaskStore();
        TaskServices.TaskWriter writer = store;
        TaskServices.TaskReader reader = store;

        writer.create("t1", "Review design");

        assertEquals("Review design", reader.findById("t1").orElseThrow().title());
        assertEquals(1, reader.listOpen().size());
    }

    @Test
    void healthAggregationReportsFirstUnhealthyDependency() {
        HealthStatus api = HealthStatus.aggregate("api", List.of(
                new HealthStatus("database", true, "ok"),
                new HealthStatus("notifications", false, "timeout")));

        assertFalse(api.healthy());
        assertEquals("notifications:timeout", api.detail());
    }

    @Test
    void fallbackServiceUsesSecondaryResultWhenPrimaryFails() {
        FallbackService<String> service = new FallbackService<>();

        assertEquals("cached", service.call(() -> {
            throw new IllegalStateException("primary down");
        }, () -> "cached"));
    }

    @Test
    void architectureDecisionRecordCopiesMutableLists() {
        List<String> alternatives = new java.util.ArrayList<>(List.of("microservices"));
        ArchitectureDecisionRecord adr = new ArchitectureDecisionRecord(
                "Start modular",
                "One team owns the product",
                "Use a modular monolith first",
                alternatives,
                List.of("simpler deployment"),
                "independent scaling pressure");

        alternatives.add("shared database");

        assertEquals(List.of("microservices"), adr.alternatives());
    }

    private String failNow() {
        throw new IllegalStateException("not yet");
    }
}
