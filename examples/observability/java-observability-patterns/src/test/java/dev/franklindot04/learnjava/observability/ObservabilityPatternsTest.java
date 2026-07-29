package dev.franklindot04.learnjava.observability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

class ObservabilityPatternsTest {
    @Test
    void structuredEventUsesStableFormattingAndRedactsSensitiveAttributes() {
        StructuredEvent event = new StructuredEvent(
                Instant.parse("2026-01-02T03:04:05Z"),
                "checkout.completed",
                Severity.INFO,
                "checkout",
                "req-1",
                Outcome.SUCCESS,
                50,
                Map.of("itemCategory", "book", "password", "unsafe-demo-value"));

        assertEquals(
                "timestamp=2026-01-02T03:04:05Z event=checkout.completed severity=INFO operation=checkout requestId=req-1 outcome=SUCCESS durationNanos=50 itemCategory=book password=[REDACTED]",
                event.format());
    }

    @Test
    void diagnosticContextPropagatesAndCleansUpAcrossExecutorBoundary() throws Exception {
        RequestContext context = new RequestContext("req-2", "trace-2", "shipOrder");
        try (ContextAwareExecutor executor = new ContextAwareExecutor(Executors.newSingleThreadExecutor())) {
            assertEquals(context, executor.submit(context, () -> DiagnosticContext.current().orElseThrow()).get());
            assertFalse(executor.submit(new RequestContext("req-3", "trace-3", "other"), () -> DiagnosticContext.current()
                    .orElseThrow()
                    .requestId()
                    .equals("req-2")).get());
        }
    }

    @Test
    void metricsTrackCountersGaugesAndTimerDistributions() {
        MetricRegistry registry = new MetricRegistry();
        registry.increment("requests_total");
        registry.increment("requests_total");
        registry.gauge("queue_depth", () -> 7);
        registry.recordTimer("checkout_duration", 10);
        registry.recordTimer("checkout_duration", 30);

        assertEquals(2, registry.counterValue("requests_total"));
        assertEquals(7, registry.gaugeValue("queue_depth"));
        assertEquals(new DistributionSnapshot(2, 10, 30, 20.0), registry.timerSnapshot("checkout_duration"));
    }

    @Test
    void healthRunnerAggregatesWorstStatusAndCatchesExceptions() {
        HealthReport report = new HealthCheckRunner().run(Map.of(
                "database", () -> new ComponentHealth("database", HealthStatus.UP, "ok"),
                "queue", () -> new ComponentHealth("queue", HealthStatus.DEGRADED, "depth high"),
                "cache", () -> {
                    throw new IllegalStateException("unavailable");
                }));

        assertEquals(HealthStatus.DOWN, report.overallStatus());
        assertEquals(3, report.components().size());
    }

    @Test
    void boundedRecorderEvictsOldestAndSnapshotIsImmutable() {
        DiagnosticEventRecorder recorder = new DiagnosticEventRecorder(2);
        recorder.record(event("one"));
        recorder.record(event("two"));
        recorder.record(event("three"));

        List<StructuredEvent> snapshot = recorder.snapshot();
        assertEquals(List.of("two", "three"), snapshot.stream().map(StructuredEvent::eventName).toList());
        assertThrows(UnsupportedOperationException.class, () -> snapshot.add(event("four")));
    }

    @Test
    void exceptionSummaryPreservesRootCauseAndRedactsContext() {
        RuntimeException failure = new RuntimeException("wrapper", new IllegalArgumentException("bad input"));
        ExceptionSummary summary = ExceptionSummary.from(failure, Map.of("operation", "checkout", "apiToken", "unsafe-demo-value"));

        assertEquals("RuntimeException", summary.type());
        assertEquals("IllegalArgumentException", summary.rootCauseType());
        assertEquals("[REDACTED]", summary.context().get("apiToken"));
    }

    @Test
    void timerReportsNonNegativeDuration() {
        OperationTimer timer = OperationTimer.start();
        assertTrue(timer.elapsedNanos() >= 0);
    }

    @Test
    void deterministicSamplerIsRepeatableAndCanReduceVolume() {
        DeterministicSampler first = new DeterministicSampler(0.5, 123);
        DeterministicSampler second = new DeterministicSampler(0.5, 123);

        List<Boolean> firstDecisions = List.of(first.shouldKeep(), first.shouldKeep(), first.shouldKeep(), first.shouldKeep());
        List<Boolean> secondDecisions = List.of(second.shouldKeep(), second.shouldKeep(), second.shouldKeep(), second.shouldKeep());

        assertEquals(firstDecisions, secondDecisions);
        assertTrue(firstDecisions.contains(false));
    }

    @Test
    void redactorMatchesSensitiveKeysCaseInsensitively() {
        Map<String, String> redacted = Redactor.redact(Map.of(
                "Authorization", "unsafe-demo-value",
                "clientSecret", "unsafe-demo-value",
                "safe", "value"));

        assertEquals("[REDACTED]", redacted.get("Authorization"));
        assertEquals("[REDACTED]", redacted.get("clientSecret"));
        assertEquals("value", redacted.get("safe"));
    }

    private StructuredEvent event(String name) {
        return new StructuredEvent(
                Instant.parse("2026-01-02T03:04:05Z"),
                name,
                Severity.INFO,
                "test",
                "req",
                Outcome.SUCCESS,
                1,
                Map.of());
    }
}
