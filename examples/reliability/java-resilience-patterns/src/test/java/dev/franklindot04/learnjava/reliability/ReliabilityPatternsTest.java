package dev.franklindot04.learnjava.reliability;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ReliabilityPatternsTest {
    static final class MutableClock extends Clock {
        private Instant now = Instant.parse("2026-01-01T00:00:00Z");

        @Override
        public ZoneId getZone() {
            return ZoneOffset.UTC;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }

        @Override
        public Instant instant() {
            return now;
        }

        void plus(Duration duration) {
            now = now.plus(duration);
        }
    }

    static class TransientFailure extends Exception {
        TransientFailure(String message) {
            super(message);
        }
    }

    static class FatalFailure extends Exception {}

    @Test
    void retrySucceedsOnFirstAttempt() throws Exception {
        MutableClock clock = new MutableClock();
        RetryExecutor executor = retryExecutor(clock, failure -> true, ignored -> {});
        AtomicInteger calls = new AtomicInteger();

        assertEquals("ok", executor.execute(() -> {
            calls.incrementAndGet();
            return "ok";
        }));
        assertEquals(1, calls.get());
    }

    @Test
    void retryHandlesTransientFailureThenSuccessWithDeterministicJitter() throws Exception {
        MutableClock clock = new MutableClock();
        List<Long> delays = new ArrayList<>();
        RetryExecutor executor = retryExecutor(clock, failure -> failure instanceof TransientFailure, delays::add);
        AtomicInteger calls = new AtomicInteger();

        assertEquals("done", executor.execute(() -> {
            if (calls.incrementAndGet() == 1) {
                throw new TransientFailure("temporary");
            }
            return "done";
        }));

        assertEquals(2, calls.get());
        assertEquals(List.of(11L), delays);
    }

    @Test
    void retryDoesNotRepeatNonRetryableFailure() {
        MutableClock clock = new MutableClock();
        RetryExecutor executor = retryExecutor(clock, failure -> failure instanceof TransientFailure, ignored -> {});

        assertThrows(FatalFailure.class, () -> executor.execute(() -> {
            throw new FatalFailure();
        }));
    }

    @Test
    void retryExhaustionPreservesOriginalFailure() {
        MutableClock clock = new MutableClock();
        RetryExecutor executor = retryExecutor(clock, failure -> failure instanceof TransientFailure, ignored -> {});
        AtomicInteger calls = new AtomicInteger();

        TransientFailure failure = assertThrows(TransientFailure.class, () -> executor.execute(() -> {
            calls.incrementAndGet();
            throw new TransientFailure("original-" + calls.get());
        }));

        assertEquals("original-1", failure.getMessage());
        assertEquals(3, calls.get());
    }

    @Test
    void backoffCalculatesCapAndInjectedJitter() {
        ExponentialBackoff backoff = new ExponentialBackoff(Duration.ofMillis(10), Duration.ofMillis(25), currentCap -> currentCap - 1);

        assertEquals(Duration.ZERO, backoff.delayForAttempt(1));
        assertEquals(Duration.ofMillis(9), backoff.delayForAttempt(2));
        assertEquals(Duration.ofMillis(19), backoff.delayForAttempt(3));
        assertEquals(Duration.ofMillis(24), backoff.delayForAttempt(5));
    }

    @Test
    void requestBudgetExhaustionStopsRetrying() {
        MutableClock clock = new MutableClock();
        RetryPolicy policy = new RetryPolicy(3, Duration.ofMillis(10), Duration.ofMillis(20), Duration.ofMillis(5));
        RetryExecutor executor = new RetryExecutor(policy, failure -> true, new ExponentialBackoff(Duration.ofMillis(10), Duration.ofMillis(20), cap -> cap), ignored -> {}, clock);

        assertThrows(TransientFailure.class, () -> executor.execute(() -> {
            throw new TransientFailure("budget");
        }));
    }

    @Test
    void invalidConfigurationIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new RetryPolicy(0, Duration.ofMillis(1), Duration.ofMillis(1), Duration.ofMillis(1)));
        assertThrows(IllegalArgumentException.class, () -> new ExponentialBackoff(Duration.ZERO, Duration.ofMillis(1), cap -> cap));
        assertThrows(IllegalArgumentException.class, () -> new Bulkhead(0));
        assertThrows(IllegalArgumentException.class, () -> new LoadShedder(0));
    }

    @Test
    void circuitBreakerOpensRejectsHalfOpensClosesAndReopens() throws Exception {
        MutableClock clock = new MutableClock();
        CircuitBreaker breaker = new CircuitBreaker(2, Duration.ofSeconds(5), clock);

        assertThrows(TransientFailure.class, () -> breaker.call(() -> { throw new TransientFailure("one"); }));
        assertThrows(TransientFailure.class, () -> breaker.call(() -> { throw new TransientFailure("two"); }));
        assertEquals(CircuitBreakerState.OPEN, breaker.snapshot().state());
        assertThrows(IllegalStateException.class, () -> breaker.call(() -> "rejected"));

        clock.plus(Duration.ofSeconds(5));
        assertEquals(CircuitBreakerState.HALF_OPEN, breaker.snapshot().state());
        assertEquals("ok", breaker.call(() -> "ok"));
        assertEquals(CircuitBreakerState.CLOSED, breaker.snapshot().state());

        assertThrows(TransientFailure.class, () -> breaker.call(() -> { throw new TransientFailure("three"); }));
        assertThrows(TransientFailure.class, () -> breaker.call(() -> { throw new TransientFailure("four"); }));
        clock.plus(Duration.ofSeconds(5));
        assertEquals(CircuitBreakerState.HALF_OPEN, breaker.snapshot().state());
        assertThrows(TransientFailure.class, () -> breaker.call(() -> { throw new TransientFailure("probe"); }));
        assertEquals(CircuitBreakerState.OPEN, breaker.snapshot().state());
    }

    @Test
    void bulkheadRejectsWhenFullAndReleasesAfterSuccessAndException() throws Exception {
        Bulkhead bulkhead = new Bulkhead(1);
        CountDownLatch entered = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        FutureTask<String> inFlight = new FutureTask<>(() -> bulkhead.execute(() -> {
            entered.countDown();
            release.await();
            return "ok";
        }));
        Thread thread = new Thread(inFlight);
        thread.start();

        assertTrue(entered.await(1, TimeUnit.SECONDS));
        assertEquals(1, bulkhead.active());
        assertEquals(0, bulkhead.available());
        assertThrows(BulkheadRejectedException.class, () -> bulkhead.execute(() -> "no"));

        release.countDown();
        assertEquals("ok", inFlight.get(1, TimeUnit.SECONDS));
        assertEquals(0, bulkhead.active());
        assertEquals(1, bulkhead.available());

        assertThrows(RuntimeException.class, () -> bulkhead.execute(() -> { throw new RuntimeException("boom"); }));
        assertEquals(0, bulkhead.active());
        assertEquals(1, bulkhead.available());
    }

    @Test
    void idempotencyReturnsStoredResultRejectsChangedFingerprintAndSuppressesConcurrentDuplicates() throws Exception {
        InMemoryIdempotencyStore<String> store = new InMemoryIdempotencyStore<>();
        AtomicInteger sideEffects = new AtomicInteger();

        assertEquals("stored", store.executeOnce("key", "fingerprint", () -> {
            sideEffects.incrementAndGet();
            return "stored";
        }));
        assertEquals("stored", store.executeOnce("key", "fingerprint", () -> "duplicate"));
        assertEquals(1, sideEffects.get());
        assertThrows(IllegalArgumentException.class, () -> store.executeOnce("key", "changed", () -> "wrong"));

        InMemoryIdempotencyStore<Integer> concurrent = new InMemoryIdempotencyStore<>();
        AtomicInteger concurrentEffects = new AtomicInteger();
        CountDownLatch firstStarted = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            Future<Integer> first = pool.submit(() -> concurrent.executeOnce("same", "fp", () -> {
                firstStarted.countDown();
                Thread.sleep(20);
                return concurrentEffects.incrementAndGet();
            }));
            assertTrue(firstStarted.await(1, TimeUnit.SECONDS));
            Future<Integer> second = pool.submit(() -> concurrent.executeOnce("same", "fp", concurrentEffects::incrementAndGet));

            assertEquals(1, first.get());
            assertEquals(1, second.get());
            assertEquals(1, concurrentEffects.get());
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    void loadShedderRejectsSaturationAndExpiredBudgetWithCounters() throws Exception {
        MutableClock clock = new MutableClock();
        LoadShedder shedder = new LoadShedder(1);
        CountDownLatch entered = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        FutureTask<String> inFlight = new FutureTask<>(() -> shedder.execute(new RequestBudget(clock, Duration.ofSeconds(1)), () -> {
            entered.countDown();
            release.await();
            return "accepted";
        }));
        Thread thread = new Thread(inFlight);
        thread.start();

        assertTrue(entered.await(1, TimeUnit.SECONDS));
        assertThrows(RejectedExecutionException.class, () -> shedder.execute(new RequestBudget(clock, Duration.ofSeconds(1)), () -> "rejected"));

        MutableClock expiredClock = new MutableClock();
        RequestBudget expired = new RequestBudget(expiredClock, Duration.ofMillis(1));
        expiredClock.plus(Duration.ofMillis(1));
        assertThrows(RejectedExecutionException.class, () -> shedder.execute(expired, () -> "late"));

        release.countDown();
        assertEquals("accepted", inFlight.get(1, TimeUnit.SECONDS));
        assertEquals(1, shedder.accepted());
        assertEquals(2, shedder.rejected());
    }

    private RetryExecutor retryExecutor(MutableClock clock, FailureClassifier classifier, DelayRecorder recorder) {
        return new RetryExecutor(
                new RetryPolicy(3, Duration.ofMillis(10), Duration.ofMillis(25), Duration.ofSeconds(1)),
                classifier,
                new ExponentialBackoff(Duration.ofMillis(10), Duration.ofMillis(25), cap -> cap + 1),
                millis -> {
                    recorder.record(millis);
                    clock.plus(Duration.ofMillis(millis));
                },
                clock
        );
    }

    @FunctionalInterface
    interface DelayRecorder {
        void record(long millis);
    }
}
