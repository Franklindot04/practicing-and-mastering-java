# Observability Plan

The simulation exposes metrics, audit entries, reconciliation reports, dead-letter counts, duplicate counts, and load-shedding counts. A deployed system would add structured logs, traces, dashboards, SLOs, burn-rate alerts, and incident review workflows.

## Purpose

Observability in this capstone exists to explain system behaviour under success, failure, duplicate delivery, and degraded dependency scenarios. It is intentionally report-based so tests can assert signals without requiring a telemetry stack.

## Signals

- Metrics: confirmed orders, inventory failures, duplicates, dead letters, and load shedding.
- Audit: catalogue changes, checkout activity, and compensation outcomes.
- Reconciliation: reserved units, confirmed orders, and quarantined events.
- Fitness checks: presence of key architecture guardrails.

## Monitoring Expectations

In production, these signals would become dashboards and alerts. Examples include checkout failure rate, payment timeout rate, reservation age, outbox backlog, dead-letter count by reason, projection lag, duplicate-message rate, and load-shedding rate. Alerting should be tied to user impact or operational risk, not just any non-zero count.

## Limitations

The capstone does not emit OpenTelemetry spans, structured logs, metrics endpoints, SLO burn-rate alerts, or persistent audit records. The reports reduce uncertainty in local tests; they do not replace production observability.
