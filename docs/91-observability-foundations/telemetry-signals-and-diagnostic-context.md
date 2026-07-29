# Telemetry Signals And Diagnostic Context

## Choosing Evidence

Start with an operational question:

| Question | Better evidence |
| --- | --- |
| Are users experiencing errors? | Request error rate, status classification, user-visible outcome metrics. |
| Which dependency is slow? | Dependency latency histogram, timeout count, trace-like operation records. |
| Why are workers falling behind? | Queue depth, active workers, processing duration, retry events, saturation signals. |
| Did a release change behavior? | Deployment marker, version field, startup logs, before/after metric comparison. |

Java services commonly emit application signals, JVM signals, operating-system signals, and infrastructure signals. Application evidence explains business and request behavior. JVM evidence explains heap, allocation, garbage collection, threads, class loading, CPU, and runtime pressure.

## Signal Quality

Useful telemetry is complete enough, fresh enough, trustworthy enough, and cheap enough for the question being asked.

| Quality | Diagnostic question |
| --- | --- |
| Completeness | Are important paths instrumented, including failures and timeouts? |
| Freshness | Is the evidence recent enough for mitigation? |
| Trust | Are clocks, units, labels, and sampling rules understood? |
| Cost | Is data volume proportional to value? |

More telemetry is not automatically better. A service that logs every loop iteration may hide the one boundary event that matters. A metric with `userId` as a label may become expensive and unsafe.

## Java-Oriented Scenario

```text
CheckoutApi
  -> validates request
  -> reserves inventory
  -> asks PaymentGateway
  -> stores order
  -> publishes OrderAccepted event
```

Useful evidence might include:

- A counter for requests received by operation and outcome.
- A histogram for checkout duration and dependency duration.
- A structured log event for validation failure and final outcome.
- A trace-like record for inventory, payment, persistence, and publish steps.
- A gauge for worker queue depth.
- A health report for dependency reachability and degraded states.

Do not attach raw card data, password values, authorization headers, session tokens, or unredacted user input to any telemetry record.

## Diagnostic Context Checklist

- [ ] Use stable operation names.
- [ ] Separate low-cardinality labels from high-cardinality correlation fields.
- [ ] Record outcome, status, and error classification.
- [ ] Use UTC timestamps.
- [ ] Keep durations in explicit units.
- [ ] Preserve root-cause information without exposing secrets.
- [ ] Include service, version, and environment when safe.
- [ ] Avoid real customer data in educational examples.

