package dev.franklindot04.learnjava.reliableservice;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ReliableCheckoutServiceTest {
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

    private CheckoutRequest request(String key) {
        return new CheckoutRequest(key, "fp-" + key, "sku", 1, 100);
    }

    @Test
    void healthyCheckoutSucceeds() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(), new FailurePlan(), new FailurePlan());

        CheckoutResponse response = service.checkout(request("healthy"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.SUCCESS, response.status());
        assertEquals(1, service.metrics().paymentCharges);
        assertEquals(1, service.metrics().inventoryReservations);
        assertTrue(response.diagnostics().contains("notification sent"));
    }

    @Test
    void inventoryTransientFailureRetriesAndSucceeds() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(FailureType.TRANSIENT), new FailurePlan(), new FailurePlan());

        CheckoutResponse response = service.checkout(request("retry"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.SUCCESS, response.status());
        assertTrue(response.diagnostics().contains("inventory TRANSIENT on attempt 1"));
        assertTrue(response.diagnostics().contains("inventory retry backoff 10ms"));
        assertTrue(response.diagnostics().contains("inventory reserved on attempt 2"));
        assertTrue(service.journal().events().contains("inventory recovered after retry"));
    }

    @Test
    void paymentNonRetryableFailureStopsProcessingClearly() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(), new FailurePlan(FailureType.FATAL), new FailurePlan());

        CheckoutResponse response = service.checkout(request("payment"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.FAILED, response.status());
        assertEquals("FATAL", response.message());
        assertEquals(0, service.metrics().paymentCharges);
        assertTrue(service.journal().events().stream().anyMatch(event -> event.contains("checkout failed")));
    }

    @Test
    void notificationFailureDegradesGracefully() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(), new FailurePlan(), new FailurePlan(FailureType.FATAL));

        CheckoutResponse response = service.checkout(request("notify"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.DEGRADED, response.status());
        assertEquals("checkout accepted; notification deferred", response.message());
        assertEquals(1, service.metrics().degraded);
        assertTrue(response.diagnostics().contains("notification fallback recorded"));
    }

    @Test
    void repeatedInventoryFailuresOpenBreakerAndOpenBreakerFailsFast() {
        MutableClock clock = new MutableClock();
        ReliableCheckoutService service = service(clock, new FailurePlan(FailureType.FATAL, FailureType.FATAL), new FailurePlan(), new FailurePlan());

        assertEquals(CheckoutStatus.FAILED, service.checkout(request("one"), Duration.ofSeconds(1)).status());
        assertEquals(CheckoutStatus.FAILED, service.checkout(request("two"), Duration.ofSeconds(1)).status());
        CheckoutResponse fastFailure = service.checkout(request("three"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.FAILED, fastFailure.status());
        assertEquals("inventory circuit breaker open", fastFailure.message());
        assertTrue(service.journal().events().contains("inventory breaker opened"));
    }

    @Test
    void successfulHalfOpenProbeRestoresService() {
        MutableClock clock = new MutableClock();
        ReliableCheckoutService service = service(clock, new FailurePlan(FailureType.FATAL, FailureType.FATAL), new FailurePlan(), new FailurePlan());
        service.checkout(request("one"), Duration.ofSeconds(1));
        service.checkout(request("two"), Duration.ofSeconds(1));
        clock.plus(Duration.ofSeconds(5));

        CheckoutResponse probe = service.checkout(request("probe"), Duration.ofSeconds(1));
        CheckoutResponse afterRecovery = service.checkout(request("after"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.SUCCESS, probe.status());
        assertEquals(CheckoutStatus.SUCCESS, afterRecovery.status());
        assertTrue(service.journal().events().contains("inventory breaker half-open probe allowed"));
    }

    @Test
    void duplicateKeyPreventsDuplicateSideEffectsAndChangedRequestIsRejected() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(), new FailurePlan(), new FailurePlan());

        assertEquals(CheckoutStatus.SUCCESS, service.checkout(request("dup"), Duration.ofSeconds(1)).status());
        assertEquals(CheckoutStatus.SUCCESS, service.checkout(request("dup"), Duration.ofSeconds(1)).status());
        CheckoutResponse changed = service.checkout(new CheckoutRequest("dup", "changed", "sku", 1, 100), Duration.ofSeconds(1));

        assertEquals(1, service.metrics().paymentCharges);
        assertEquals(1, service.metrics().inventoryReservations);
        assertEquals(CheckoutStatus.FAILED, changed.status());
        assertEquals("idempotency key reused with changed request", changed.message());
    }

    @Test
    void saturatedBulkheadRejectsExcessInventoryWork() throws Exception {
        ReliableCheckoutService service = service(new MutableClock(), new BlockingFailurePlan(), new FailurePlan(), new FailurePlan());
        CountDownLatch entered = BlockingFailurePlan.entered;
        CountDownLatch release = BlockingFailurePlan.release;
        FutureTask<CheckoutResponse> first = new FutureTask<>(() -> service.checkout(request("first"), Duration.ofSeconds(1)));
        Thread thread = new Thread(first);
        thread.start();

        assertTrue(entered.await(1, TimeUnit.SECONDS));
        CheckoutResponse rejected = service.checkout(request("second"), Duration.ofSeconds(1));

        assertEquals(CheckoutStatus.FAILED, rejected.status());
        assertEquals("inventory bulkhead saturated", rejected.message());
        release.countDown();
        assertEquals(CheckoutStatus.SUCCESS, first.get(1, TimeUnit.SECONDS).status());
    }

    @Test
    void expiredDeadlineRejectsWorkBeforeSideEffects() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(), new FailurePlan(), new FailurePlan());

        CheckoutResponse response = service.checkout(request("expired"), Duration.ZERO);

        assertEquals(CheckoutStatus.REJECTED, response.status());
        assertEquals("deadline expired before work", response.message());
        assertEquals(0, service.metrics().paymentCharges);
    }

    @Test
    void recoveryJournalRecordsMeaningfulDecisions() {
        ReliableCheckoutService service = service(new MutableClock(), new FailurePlan(), new FailurePlan(), new FailurePlan(FailureType.FATAL));

        service.checkout(request("journal"), Duration.ofSeconds(1));

        assertTrue(service.journal().events().contains("notification degraded"));
        assertFalse(service.journal().events().isEmpty());
    }

    private ReliableCheckoutService service(MutableClock clock, FailurePlan inventory, FailurePlan payment, FailurePlan notification) {
        return new ReliableCheckoutService(clock, 2, 1, inventory, payment, notification);
    }

    static final class BlockingFailurePlan extends FailurePlan {
        static final CountDownLatch entered = new CountDownLatch(1);
        static final CountDownLatch release = new CountDownLatch(1);

        @Override
        void maybeFail() throws PlannedFailure {
            entered.countDown();
            try {
                release.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new PlannedFailure(FailureType.FATAL);
            }
        }
    }
}
