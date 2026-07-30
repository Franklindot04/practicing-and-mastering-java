package dev.franklindot04.learnjava.reliableservice;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public final class ReliableCheckoutService {
    private static final int MAX_INVENTORY_ATTEMPTS = 3;
    private static final Duration INITIAL_BACKOFF = Duration.ofMillis(10);
    private static final Duration MAX_BACKOFF = Duration.ofMillis(40);
    private static final Duration BREAKER_OPEN_DURATION = Duration.ofSeconds(5);

    private final Map<String, CheckoutResponse> idempotency = new ConcurrentHashMap<>();
    private final Map<String, String> fingerprints = new ConcurrentHashMap<>();
    private final Semaphore load;
    private final Semaphore inventoryBulkhead;
    private final RecoveryJournal journal = new RecoveryJournal();
    private final ReliabilityMetrics metrics = new ReliabilityMetrics();
    private final FailurePlan inventory;
    private final FailurePlan payment;
    private final FailurePlan notification;
    private final Clock clock;
    private Instant breakerOpenedAt = Instant.MIN;
    private int consecutiveInventoryFailures;
    private boolean breakerOpen;

    public ReliableCheckoutService(Clock clock, int concurrency, int bulkheadPermits, FailurePlan inventory, FailurePlan payment, FailurePlan notification) {
        if (concurrency < 1 || bulkheadPermits < 1) {
            throw new IllegalArgumentException("limits must be positive");
        }
        this.clock = clock;
        this.load = new Semaphore(concurrency);
        this.inventoryBulkhead = new Semaphore(bulkheadPermits);
        this.inventory = inventory;
        this.payment = payment;
        this.notification = notification;
    }

    public CheckoutResponse checkout(CheckoutRequest request, Duration budget) {
        if (request.quantity() < 1 || request.cents() < 1 || request.idempotencyKey() == null || request.idempotencyKey().isBlank()) {
            return failed("invalid request");
        }
        RequestContext context = new RequestContext(clock, clock.instant().plus(budget));
        if (context.expired()) {
            return reject("deadline expired before work");
        }
        if (!load.tryAcquire()) {
            return reject("service overloaded");
        }
        try {
            String existingFingerprint = fingerprints.putIfAbsent(request.idempotencyKey(), request.fingerprint());
            if (existingFingerprint != null && !existingFingerprint.equals(request.fingerprint())) {
                return failed("idempotency key reused with changed request");
            }
            return idempotency.computeIfAbsent(request.idempotencyKey(), ignored -> process(request, context));
        } finally {
            load.release();
        }
    }

    private CheckoutResponse process(CheckoutRequest request, RequestContext context) {
        List<String> diagnostics = new ArrayList<>();
        metrics.accepted++;
        if (context.expired()) {
            return reject("deadline expired");
        }
        try {
            reserveInventory(diagnostics, context);
            chargePayment(diagnostics);
            diagnostics.add("shipping quote selected");
            try {
                notification.maybeFail();
                diagnostics.add("notification sent");
            } catch (PlannedFailure failure) {
                metrics.degraded++;
                journal.record("notification degraded");
                diagnostics.add("notification fallback recorded");
                return new CheckoutResponse(CheckoutStatus.DEGRADED, "checkout accepted; notification deferred", List.copyOf(diagnostics));
            }
            journal.record("checkout success");
            return new CheckoutResponse(CheckoutStatus.SUCCESS, "checkout completed", List.copyOf(diagnostics));
        } catch (Exception failure) {
            journal.record("checkout failed: " + failure.getMessage());
            return new CheckoutResponse(CheckoutStatus.FAILED, failure.getMessage(), List.copyOf(diagnostics));
        }
    }

    private void reserveInventory(List<String> diagnostics, RequestContext context) throws Exception {
        updateBreakerForProbeWindow();
        if (breakerOpen) {
            throw new IllegalStateException("inventory circuit breaker open");
        }
        if (!inventoryBulkhead.tryAcquire()) {
            throw new RejectedExecutionException("inventory bulkhead saturated");
        }
        try {
            for (int attempt = 1; attempt <= MAX_INVENTORY_ATTEMPTS; attempt++) {
                if (context.expired()) {
                    throw new IllegalStateException("deadline expired during inventory");
                }
                try {
                    inventory.maybeFail();
                    consecutiveInventoryFailures = 0;
                    breakerOpen = false;
                    metrics.inventoryReservations++;
                    diagnostics.add("inventory reserved on attempt " + attempt);
                    if (attempt > 1) {
                        journal.record("inventory recovered after retry");
                    }
                    return;
                } catch (PlannedFailure failure) {
                    diagnostics.add("inventory " + failure.type + " on attempt " + attempt);
                    if (!failure.retryable() || attempt == MAX_INVENTORY_ATTEMPTS) {
                        recordInventoryFailure();
                        throw failure;
                    }
                    diagnostics.add("inventory retry backoff " + backoffFor(attempt + 1).toMillis() + "ms");
                }
            }
        } finally {
            inventoryBulkhead.release();
        }
    }

    private void updateBreakerForProbeWindow() {
        if (breakerOpen && !clock.instant().isBefore(breakerOpenedAt.plus(BREAKER_OPEN_DURATION))) {
            breakerOpen = false;
            journal.record("inventory breaker half-open probe allowed");
        }
    }

    private void recordInventoryFailure() {
        consecutiveInventoryFailures++;
        if (consecutiveInventoryFailures >= 2) {
            breakerOpen = true;
            breakerOpenedAt = clock.instant();
            journal.record("inventory breaker opened");
        }
    }

    private Duration backoffFor(int attempt) {
        long raw = INITIAL_BACKOFF.toMillis() * (1L << Math.max(0, attempt - 2));
        return Duration.ofMillis(Math.min(raw, MAX_BACKOFF.toMillis()));
    }

    private void chargePayment(List<String> diagnostics) throws PlannedFailure {
        payment.maybeFail();
        metrics.paymentCharges++;
        diagnostics.add("payment charged");
    }

    private CheckoutResponse reject(String message) {
        metrics.rejected++;
        journal.record(message);
        return new CheckoutResponse(CheckoutStatus.REJECTED, message, List.of(message));
    }

    private CheckoutResponse failed(String message) {
        journal.record(message);
        return new CheckoutResponse(CheckoutStatus.FAILED, message, List.of(message));
    }

    public RecoveryJournal journal() {
        return journal;
    }

    public ReliabilityMetrics metrics() {
        return metrics;
    }
}
