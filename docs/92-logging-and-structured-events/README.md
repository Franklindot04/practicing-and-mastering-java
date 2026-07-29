# Logging And Structured Events

Application logs are diagnostic event records. They should explain important decisions at service boundaries, not become a prose diary of every line the program executed.

## Study Order

1. [Structured Event Design](structured-event-design.md)
2. [Java Logging Context And Exceptions](java-logging-context-and-exceptions.md)
3. [Safe Logging And Operational Practices](safe-logging-and-operational-practices.md)

## Log Event Shape

| Field | Purpose |
| --- | --- |
| `timestamp` | UTC time when the event happened. |
| `event` | Stable event name such as `order.validation.failed`. |
| `severity` | `trace`, `debug`, `info`, `warn`, or `error`. |
| `operation` | Stable business or technical operation. |
| `requestId` | Correlates logs for one request or job. |
| `traceId` and `spanId` | Connects log events to trace-like records when available. |
| `outcome` | Success, failure, rejected, timed_out, retried, or degraded. |
| `durationMs` | Explicit duration unit for completed operations. |

## Severity Guide

| Severity | Use |
| --- | --- |
| Trace | Very detailed local investigation. Usually off by default. |
| Debug | Developer-oriented diagnostic detail. |
| Info | Meaningful lifecycle, boundary, and outcome events. |
| Warn | Unexpected but handled condition or degraded path. |
| Error | Failed operation requiring investigation or caller-visible failure. |

Avoid severity inflation. If every handled validation failure is `error`, real operational failures become harder to find.

## Review Questions

1. Why are stable fields easier to query than free-form text?
2. What should be logged at a service boundary?
3. Why should audit logs be designed separately from diagnostic logs?
4. How can duplicate logging hide the root cause?
5. What makes a log event unsafe?

