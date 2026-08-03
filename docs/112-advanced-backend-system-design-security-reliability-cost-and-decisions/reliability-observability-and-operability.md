# Reliability Observability And Operability

Reliability is a product property, not only an infrastructure property. Operability means owners can understand, change, repair, and recover the system under pressure.

## SLOs And Error Budgets

SLIs measure user-relevant signals: availability, latency, correctness, durability, freshness, queue lag, and successful workflow completion. SLOs set target values for those signals. Error budgets make reliability trade-offs visible.

Examples:

- checkout success rate after valid user submission
- order durability after acknowledgement
- search freshness after catalogue update
- notification start latency
- projection rebuild completion time
- dead-letter volume by event type

## Health And Saturation

Readiness tells whether an instance can receive traffic. Liveness tells whether it should be restarted. They should not be identical. A process can be alive while not ready because dependencies or warm-up are incomplete.

Track saturation: CPU, memory, garbage collection, thread pools, connection pools, queue depth, consumer lag, rate-limit usage, and dependency latency. Saturation often explains failures before errors spike.

## Observability

Metrics show trends and alert conditions. Logs explain discrete events. Traces connect work across services. Dashboards should reflect user journeys and system ownership, not only machine resources.

Correlation IDs should cross HTTP calls, messages, logs, metrics exemplars where supported, and traces. Do not put secrets or private data into correlation fields.

Alerts should be actionable. Alert on symptoms that need human action, such as SLO burn rate, checkout failure, payment uncertainty, dead-letter growth, projection lag, or capacity saturation. Avoid alerting on every internal metric without a runbook.

## Resilience Patterns

Timeouts prevent unbounded waiting. Retries handle transient failures only when bounded and safe. Circuit breakers reduce repeated calls to failing dependencies. Bulkheads isolate resources. Load shedding protects critical capacity. Graceful degradation keeps optional features from blocking core journeys. Fallbacks should be honest and measurable.

Chaos tests and capacity tests can reveal assumptions, but they must be controlled. Stage 28 simulations teach policy reasoning; production readiness needs deeper validation and operational evidence.

## Operations

Runbooks should cover triage, mitigation, rollback, repair, replay, communication, and follow-up. Incident command clarifies who coordinates, who diagnoses, who communicates, and who approves risky action.

Canaries, blue-green deployments, feature flags, rollback, and roll-forward plans reduce change risk. Owners should know when rollback is unsafe, such as after destructive schema migrations or externally visible side effects.
