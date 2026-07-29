# Java Observability Patterns

This dependency-light Maven project demonstrates local observability habits with plain Java and JUnit. It does not use a hosted observability service, background agent, collector, or external infrastructure.

## What The Examples Demonstrate

| Example | Purpose |
| --- | --- |
| `StructuredEvent` | Immutable safe event fields and deterministic formatting. |
| `RequestContext` and `ContextAwareExecutor` | Explicit diagnostic context propagation and cleanup across executor boundaries. |
| `MetricRegistry` | Small educational counters, gauges, and timer observations. |
| `HealthCheckRunner` | Exception-safe component health and aggregate reports. |
| `DiagnosticEventRecorder` | Bounded, thread-safe recent event storage with immutable snapshots. |
| `ExceptionSummary` | Safe exception type, message, root-cause type, and redacted context. |
| `OperationTimer` | `System.nanoTime` elapsed measurements without fixed performance thresholds. |
| `DeterministicSampler` | Seedable sampling that reduces diagnostic event volume without changing business behavior. |
| `Redactor` | Case-insensitive redaction for sensitive keys such as password, token, authorization, and secret. |

## Limitations

These classes are intentionally small so learners can read them. They are not a production logging, metrics, tracing, or health-check library. Production Java systems normally use established telemetry libraries because real systems need efficient exporters, schemas, batching, backpressure, context propagation, security review, and operational support.

## Safe Logging Rules

- Do not log credentials, secrets, raw tokens, authorization headers, or private keys.
- Prefer stable event names and fields over prose-only messages.
- Keep request IDs and trace-like IDs out of metric labels.
- Use deterministic formatting in tests.
- Validate observability behavior without fragile timing thresholds.

Run tests with:

```bash
mvn -f examples/observability/java-observability-patterns/pom.xml test
```

