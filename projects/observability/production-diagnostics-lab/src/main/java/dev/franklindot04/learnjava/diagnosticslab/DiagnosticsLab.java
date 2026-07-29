package dev.franklindot04.learnjava.diagnosticslab;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

record WorkItem(String id, String customerSegment, boolean valid, Map<String, String> attributes) {
    WorkItem {
        id = requireText(id, "id");
        customerSegment = requireText(customerSegment, "customerSegment");
        attributes = Map.copyOf(Redactor.redact(attributes == null ? Map.of() : attributes));
    }

    private static String requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " is required");
        }
        return value.trim();
    }
}

record ProcessingResult(String requestId, boolean accepted, String outcome, int attempts) {
}

record RequestContext(String requestId, String correlationId, String operation) {
}

record StructuredEvent(Instant timestamp, String eventName, String requestId, String outcome, Map<String, String> fields) {
    StructuredEvent {
        fields = Map.copyOf(Redactor.redact(fields == null ? Map.of() : fields));
    }
}

record OperationSpan(String spanId, String parentSpanId, String operation, String requestId, String outcome, long durationNanos, Map<String, String> attributes) {
    OperationSpan {
        attributes = Map.copyOf(Redactor.redact(attributes == null ? Map.of() : attributes));
    }
}

enum HealthStatus {
    UP, DEGRADED, DOWN;

    static HealthStatus worst(HealthStatus left, HealthStatus right) {
        return left.ordinal() >= right.ordinal() ? left : right;
    }
}

record ComponentHealth(String component, HealthStatus status, String detail) {
}

record HealthReport(HealthStatus overallStatus, List<ComponentHealth> components) {
    HealthReport {
        components = List.copyOf(components);
    }
}

record DiagnosticSnapshot(List<StructuredEvent> recentEvents, Map<String, Long> metrics, HealthReport healthReport, List<OperationSpan> recentSpans, Map<String, Long> summaryCounts) {
    DiagnosticSnapshot {
        recentEvents = List.copyOf(recentEvents);
        metrics = Map.copyOf(metrics);
        recentSpans = List.copyOf(recentSpans);
        summaryCounts = Map.copyOf(summaryCounts);
    }
}

final class Redactor {
    private Redactor() {
    }

    static Map<String, String> redact(Map<String, String> input) {
        Map<String, String> output = new LinkedHashMap<>();
        input.forEach((key, value) -> output.put(key, isSensitive(key) ? "[REDACTED]" : String.valueOf(value)));
        return output;
    }

    static boolean isSensitive(String key) {
        String normalized = key == null ? "" : key.toLowerCase(Locale.ROOT);
        return normalized.contains("password") || normalized.contains("token") || normalized.contains("authorization") || normalized.contains("secret");
    }
}

final class EventRecorder<T> {
    private final int capacity;
    private final ArrayDeque<T> values = new ArrayDeque<>();

    EventRecorder(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
    }

    synchronized void record(T value) {
        if (values.size() == capacity) {
            values.removeFirst();
        }
        values.addLast(value);
    }

    synchronized List<T> snapshot() {
        return List.copyOf(new ArrayList<>(values));
    }
}

final class MetricRegistry {
    private final Map<String, AtomicLong> values = new HashMap<>();

    synchronized void increment(String name) {
        add(name, 1);
    }

    synchronized void add(String name, long delta) {
        values.computeIfAbsent(name, ignored -> new AtomicLong()).addAndGet(delta);
    }

    synchronized Map<String, Long> snapshot() {
        Map<String, Long> copy = new LinkedHashMap<>();
        values.forEach((key, value) -> copy.put(key, value.get()));
        return Map.copyOf(copy);
    }
}

record RetryPolicy(int maxAttempts) {
    RetryPolicy {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("maxAttempts must be positive");
        }
    }
}

enum DependencyOutcome {
    SUCCESS, TIMEOUT, DEGRADED
}

final class SimulatedDependency {
    private final ArrayDeque<DependencyOutcome> scripted = new ArrayDeque<>();
    private volatile HealthStatus healthStatus = HealthStatus.UP;

    SimulatedDependency(List<DependencyOutcome> outcomes) {
        scripted.addAll(outcomes);
    }

    synchronized DependencyOutcome call() {
        DependencyOutcome outcome = scripted.isEmpty() ? DependencyOutcome.SUCCESS : scripted.removeFirst();
        if (outcome == DependencyOutcome.DEGRADED) {
            healthStatus = HealthStatus.DEGRADED;
        }
        if (outcome == DependencyOutcome.TIMEOUT) {
            healthStatus = HealthStatus.DOWN;
        }
        return outcome;
    }

    HealthStatus healthStatus() {
        return healthStatus;
    }
}

final class DiagnosticContext {
    private static final ThreadLocal<RequestContext> CURRENT = new ThreadLocal<>();

    static Optional<RequestContext> current() {
        return Optional.ofNullable(CURRENT.get());
    }

    static Scope with(RequestContext context) {
        RequestContext previous = CURRENT.get();
        CURRENT.set(context);
        return new Scope(previous);
    }

    static final class Scope implements AutoCloseable {
        private final RequestContext previous;

        Scope(RequestContext previous) {
            this.previous = previous;
        }

        @Override
        public void close() {
            if (previous == null) {
                CURRENT.remove();
            } else {
                CURRENT.set(previous);
            }
        }
    }
}

final class ContextAwareExecutor implements AutoCloseable {
    private final ExecutorService executor;

    ContextAwareExecutor(ExecutorService executor) {
        this.executor = Objects.requireNonNull(executor);
    }

    <T> Future<T> submit(RequestContext context, Callable<T> task) {
        return executor.submit(() -> {
            try (DiagnosticContext.Scope ignored = DiagnosticContext.with(context)) {
                return task.call();
            }
        });
    }

    @Override
    public void close() {
        executor.shutdownNow();
    }
}

final class ProcessingPipeline {
    private final SimulatedDependency dependency;
    private final RetryPolicy retryPolicy;
    private final EventRecorder<StructuredEvent> events;
    private final EventRecorder<OperationSpan> spans;
    private final MetricRegistry metrics;
    private final int queueCapacity;
    private final AtomicInteger queueDepth = new AtomicInteger();
    private final AtomicInteger activeWork = new AtomicInteger();
    private final AtomicLong spanSequence = new AtomicLong();

    ProcessingPipeline(SimulatedDependency dependency, RetryPolicy retryPolicy, EventRecorder<StructuredEvent> events, EventRecorder<OperationSpan> spans, MetricRegistry metrics, int queueCapacity) {
        this.dependency = dependency;
        this.retryPolicy = retryPolicy;
        this.events = events;
        this.spans = spans;
        this.metrics = metrics;
        this.queueCapacity = queueCapacity;
    }

    ProcessingResult process(WorkItem item, RequestContext context) {
        metrics.increment("requests.received");
        String rootSpanId = nextSpanId();
        events.record(event("request.received", context, "received", Map.of("segment", item.customerSegment())));
        if (!item.valid()) {
            metrics.increment("requests.failed");
            spans.record(span(rootSpanId, null, "process", context, "validation_failed", Map.of()));
            events.record(event("validation.failed", context, "failure", item.attributes()));
            return new ProcessingResult(context.requestId(), false, "validation_failed", 0);
        }
        if (queueDepth.incrementAndGet() > queueCapacity) {
            queueDepth.decrementAndGet();
            metrics.increment("requests.failed");
            metrics.increment("queue.saturated");
            events.record(event("queue.saturated", context, "failure", Map.of("queue", "orders")));
            return new ProcessingResult(context.requestId(), false, "queue_saturated", 0);
        }
        activeWork.incrementAndGet();
        metrics.add("active.work", 1);
        try {
            int attempts = 0;
            while (attempts < retryPolicy.maxAttempts()) {
                attempts++;
                DependencyOutcome dependencyOutcome = dependency.call();
                spans.record(span(nextSpanId(), rootSpanId, "dependency.call", context, dependencyOutcome.name().toLowerCase(Locale.ROOT), Map.of("attempt", String.valueOf(attempts))));
                if (dependencyOutcome == DependencyOutcome.SUCCESS || dependencyOutcome == DependencyOutcome.DEGRADED) {
                    metrics.increment("requests.succeeded");
                    metrics.add("operation.duration.nanos", 1);
                    if (attempts > 1) {
                        metrics.increment("retries.succeeded");
                    }
                    if (dependencyOutcome == DependencyOutcome.DEGRADED) {
                        metrics.increment("dependency.degraded");
                    }
                    events.record(event("request.completed", context, "success", Map.of("attempts", String.valueOf(attempts))));
                    spans.record(span(rootSpanId, null, "process", context, "success", Map.of()));
                    return new ProcessingResult(context.requestId(), true, "success", attempts);
                }
                metrics.increment("dependency.failures");
                if (attempts < retryPolicy.maxAttempts()) {
                    metrics.increment("retries.total");
                    events.record(event("dependency.retry", context, "retry", Map.of("attempt", String.valueOf(attempts))));
                }
            }
            metrics.increment("requests.failed");
            events.record(event("request.failed", context, "failure", Map.of("reason", "retry_exhausted")));
            spans.record(span(rootSpanId, null, "process", context, "retry_exhausted", Map.of()));
            return new ProcessingResult(context.requestId(), false, "retry_exhausted", retryPolicy.maxAttempts());
        } finally {
            activeWork.decrementAndGet();
            metrics.add("active.work", -1);
            queueDepth.decrementAndGet();
        }
    }

    HealthReport healthReport() {
        List<ComponentHealth> components = List.of(
                new ComponentHealth("dependency", dependency.healthStatus(), "simulated dependency"),
                new ComponentHealth("queue", queueDepth.get() >= queueCapacity ? HealthStatus.DEGRADED : HealthStatus.UP, "depth=" + queueDepth.get()),
                new ComponentHealth("workers", activeWork.get() > queueCapacity ? HealthStatus.DEGRADED : HealthStatus.UP, "active=" + activeWork.get()));
        HealthStatus overall = components.stream().map(ComponentHealth::status).reduce(HealthStatus.UP, HealthStatus::worst);
        return new HealthReport(overall, components);
    }

    int activeWork() {
        return activeWork.get();
    }

    int queueDepth() {
        return queueDepth.get();
    }

    private StructuredEvent event(String name, RequestContext context, String outcome, Map<String, String> fields) {
        return new StructuredEvent(Instant.EPOCH, name, context.requestId(), outcome, fields);
    }

    private OperationSpan span(String spanId, String parentSpanId, String operation, RequestContext context, String outcome, Map<String, String> attributes) {
        return new OperationSpan(spanId, parentSpanId, operation, context.requestId(), outcome, 1L, attributes);
    }

    private String nextSpanId() {
        return "span-" + spanSequence.incrementAndGet();
    }
}

final class ProductionDiagnosticsService {
    private final EventRecorder<StructuredEvent> events;
    private final EventRecorder<OperationSpan> spans;
    private final MetricRegistry metrics;
    private final ProcessingPipeline pipeline;

    ProductionDiagnosticsService(SimulatedDependency dependency, RetryPolicy retryPolicy, int capacity, int queueCapacity) {
        this.events = new EventRecorder<>(capacity);
        this.spans = new EventRecorder<>(capacity);
        this.metrics = new MetricRegistry();
        this.pipeline = new ProcessingPipeline(dependency, retryPolicy, events, spans, metrics, queueCapacity);
    }

    ProcessingResult process(WorkItem item, RequestContext context) {
        try (DiagnosticContext.Scope ignored = DiagnosticContext.with(context)) {
            return pipeline.process(item, context);
        }
    }

    DiagnosticSnapshot snapshot() {
        Map<String, Long> metricSnapshot = metrics.snapshot();
        Map<String, Long> summary = Map.of(
                "events", (long) events.snapshot().size(),
                "spans", (long) spans.snapshot().size(),
                "successes", metricSnapshot.getOrDefault("requests.succeeded", 0L),
                "failures", metricSnapshot.getOrDefault("requests.failed", 0L));
        return new DiagnosticSnapshot(events.snapshot(), metricSnapshot, pipeline.healthReport(), spans.snapshot(), summary);
    }

    ProcessingPipeline pipeline() {
        return pipeline;
    }
}
