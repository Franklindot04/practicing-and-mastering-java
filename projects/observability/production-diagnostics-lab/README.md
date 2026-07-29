# Production Diagnostics Lab

This standalone Maven project simulates a local order-processing service. It does not open network ports, start containers, contact cloud services, install agents, or send telemetry to an external backend.

## Architecture

```text
ProductionDiagnosticsService
  -> ProcessingPipeline
     -> validation
     -> bounded queue accounting
     -> worker execution
     -> SimulatedDependency with deterministic outcomes
     -> RetryPolicy
     -> StructuredEvent recorder
     -> MetricRegistry
     -> trace-like OperationSpan recorder
     -> HealthReport
```

## Instrumentation Points

- Request intake records request count and a safe `request.received` event.
- Validation failures record failure metrics and a rejected outcome.
- Dependency calls record attempts, retries, dependency failures, and trace-like child spans.
- Queue saturation records a failed result and degraded health.
- Active work is cleaned up in a `finally` block.
- Diagnostic snapshots copy recent events, metrics, health, and recent spans into immutable collections.

## Operational Questions

The lab can answer questions such as:

- Are requests failing before or after validation?
- Did retries change the final business outcome?
- Is a dependency degraded or timing out?
- Is queue pressure contributing to failures?
- Did request context cross a worker boundary?
- Which trace-like child operation belongs to the parent request?

## Scenarios

Tests cover successful processing, validation failure, retry then success, retry exhaustion, queue saturation, degraded dependency health, context isolation, parent-child span relationships, redaction, immutable snapshots, equivalent business outcome with diagnostics enabled, and bounded storage.

## Limitations

This is an educational model, not a complete tracing, metrics, logging, health, or production incident platform. Real systems need established telemetry libraries, reviewed schemas, storage policies, alert routing, privacy controls, and operational ownership.

Future extensions could add richer span links, more metric dimensions, dashboard sketches, or runbook exercises. Those are intentionally not implemented here.

Run tests with:

```bash
mvn -f projects/observability/production-diagnostics-lab/pom.xml test
```

