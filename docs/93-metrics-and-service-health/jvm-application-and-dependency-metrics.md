# JVM, Application, And Dependency Metrics

Java services need evidence from several layers.

## Application Metrics

Track user-visible and domain outcomes:

- Request rate and outcome.
- Validation rejection count.
- Operation duration distribution.
- Background job successes and failures.
- Queue depth and rejected work.
- Cache hit and miss counts.
- Executor active work and completed work.

Business or domain metrics should use safe categories. `plan=free` may be useful. A raw email address is not safe.

## JVM And Runtime Metrics

| Area | Examples |
| --- | --- |
| Heap | Used bytes, committed bytes, max bytes. |
| Allocation | Allocation rate and pressure. |
| Garbage collection | Collection count, pause duration, time spent. |
| Threads | Live, daemon, blocked, waiting, runnable counts. |
| Class loading | Loaded and unloaded class counts. |
| CPU | Process CPU and system CPU concepts. |
| File descriptors | Open descriptors or handles as a resource concept. |

JVM metrics help explain runtime pressure, but they are not automatically causes. A garbage collection spike may be a cause candidate, a symptom of allocation growth, or unrelated background noise.

## Dependency Metrics

Track clients and pools:

- Database query duration and error class.
- Connection pool active, idle, and pending counts.
- Retry count by dependency and safe reason.
- Timeout count.
- Circuit or degraded-state count when such a pattern exists.
- Queue publish and consume rates.

Error classification should be stable: `timeout`, `connection_refused`, `validation_rejected`, `rate_limited`, or `unavailable`. Avoid raw exception messages as labels.

