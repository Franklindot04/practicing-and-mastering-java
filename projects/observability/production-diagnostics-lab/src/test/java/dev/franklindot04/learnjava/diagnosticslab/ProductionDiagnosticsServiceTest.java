package dev.franklindot04.learnjava.diagnosticslab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

class ProductionDiagnosticsServiceTest {
    @Test
    void successfulProcessingRecordsMetricsEventsAndSpans() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.SUCCESS), 2, 10, 2);

        ProcessingResult result = service.process(work("order-1", true), context("req-1"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertTrue(result.accepted());
        assertEquals(1, snapshot.metrics().get("requests.succeeded"));
        assertTrue(snapshot.recentEvents().stream().anyMatch(event -> event.eventName().equals("request.completed")));
        assertTrue(snapshot.recentSpans().stream().anyMatch(span -> span.operation().equals("dependency.call")));
    }

    @Test
    void validationFailureDoesNotCallDependencyAndRecordsSafeFields() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.SUCCESS), 2, 10, 2);

        ProcessingResult result = service.process(new WorkItem("bad", "standard", false, Map.of("apiToken", "unsafe-demo-value")), context("req-2"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertFalse(result.accepted());
        assertEquals("validation_failed", result.outcome());
        assertEquals("[REDACTED]", snapshot.recentEvents().stream()
                .filter(event -> event.eventName().equals("validation.failed"))
                .findFirst()
                .orElseThrow()
                .fields()
                .get("apiToken"));
    }

    @Test
    void retryThenSuccessKeepsBusinessOutcomeSuccessful() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.TIMEOUT, DependencyOutcome.SUCCESS), 2, 10, 2);

        ProcessingResult result = service.process(work("order-2", true), context("req-3"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertTrue(result.accepted());
        assertEquals(2, result.attempts());
        assertEquals(1, snapshot.metrics().get("retries.total"));
        assertEquals(1, snapshot.metrics().get("retries.succeeded"));
    }

    @Test
    void retryExhaustionRecordsFailureAndDependencyFailures() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.TIMEOUT, DependencyOutcome.TIMEOUT), 2, 10, 2);

        ProcessingResult result = service.process(work("order-3", true), context("req-4"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertFalse(result.accepted());
        assertEquals("retry_exhausted", result.outcome());
        assertEquals(2, snapshot.metrics().get("dependency.failures"));
        assertEquals(1, snapshot.metrics().get("requests.failed"));
    }

    @Test
    void activeWorkAndQueueDepthAreCleanedUpAfterFailure() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.TIMEOUT), 1, 10, 2);

        service.process(work("order-4", true), context("req-5"));

        assertEquals(0, service.pipeline().activeWork());
        assertEquals(0, service.pipeline().queueDepth());
        assertEquals(0, service.snapshot().metrics().get("active.work"));
    }

    @Test
    void boundedStorageEvictsOldestEventsAndSnapshotsAreImmutable() {
        ProductionDiagnosticsService service = service(List.of(), 1, 3, 2);

        service.process(work("order-5", true), context("req-6"));
        service.process(work("order-6", true), context("req-7"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertEquals(3, snapshot.recentEvents().size());
        assertThrows(UnsupportedOperationException.class, () -> snapshot.recentEvents().add(snapshot.recentEvents().get(0)));
    }

    @Test
    void healthDegradesWhenDependencyReportsDegraded() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.DEGRADED), 1, 10, 2);

        service.process(work("order-7", true), context("req-8"));

        assertEquals(HealthStatus.DEGRADED, service.snapshot().healthReport().overallStatus());
    }

    @Test
    void contextAwareExecutorPropagatesAndIsolatesContext() throws Exception {
        try (ContextAwareExecutor executor = new ContextAwareExecutor(Executors.newSingleThreadExecutor())) {
            RequestContext first = context("req-9");
            RequestContext second = context("req-10");

            assertEquals(first, executor.submit(first, () -> DiagnosticContext.current().orElseThrow()).get());
            assertEquals(second, executor.submit(second, () -> DiagnosticContext.current().orElseThrow()).get());
        }
    }

    @Test
    void traceLikeRecordsKeepParentRelationships() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.SUCCESS), 1, 10, 2);

        service.process(work("order-8", true), context("req-11"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertTrue(snapshot.recentSpans().stream().anyMatch(span -> span.parentSpanId() != null));
    }

    @Test
    void queueSaturationProducesFailedResult() {
        ProductionDiagnosticsService service = service(List.of(), 1, 10, 0);

        ProcessingResult result = service.process(work("order-9", true), context("req-12"));

        assertFalse(result.accepted());
        assertEquals("queue_saturated", result.outcome());
        assertEquals(1, service.snapshot().metrics().get("queue.saturated"));
    }

    @Test
    void diagnosticsDoNotChangeBusinessOutcomeForSameDependencyScript() {
        ProductionDiagnosticsService first = service(List.of(DependencyOutcome.TIMEOUT, DependencyOutcome.SUCCESS), 2, 10, 2);
        ProductionDiagnosticsService second = service(List.of(DependencyOutcome.TIMEOUT, DependencyOutcome.SUCCESS), 2, 10, 2);

        ProcessingResult left = first.process(work("order-10", true), context("req-13"));
        ProcessingResult right = second.process(work("order-10", true), context("req-13"));

        assertEquals(left, right);
    }

    @Test
    void immutableSnapshotCarriesSummaryCounts() {
        ProductionDiagnosticsService service = service(List.of(DependencyOutcome.SUCCESS), 1, 10, 2);

        service.process(work("order-11", true), context("req-14"));
        DiagnosticSnapshot snapshot = service.snapshot();

        assertEquals(1, snapshot.summaryCounts().get("successes"));
        assertThrows(UnsupportedOperationException.class, () -> snapshot.metrics().put("x", 1L));
    }

    private ProductionDiagnosticsService service(List<DependencyOutcome> outcomes, int attempts, int capacity, int queueCapacity) {
        return new ProductionDiagnosticsService(new SimulatedDependency(outcomes), new RetryPolicy(attempts), capacity, queueCapacity);
    }

    private WorkItem work(String id, boolean valid) {
        return new WorkItem(id, "standard", valid, Map.of("productCategory", "book"));
    }

    private RequestContext context(String requestId) {
        return new RequestContext(requestId, "corr-" + requestId, "processOrder");
    }
}
