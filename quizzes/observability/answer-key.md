# Observability Answer Key

## Multiple Choice

1. B
2. C
3. C
4. A
5. B

## Vocabulary And Signal

Short answers should say telemetry is diagnostic evidence emitted by a system, such as logs, metrics, traces, events, profiles, dumps, and health states. Symptoms are visible impact; causes are contributing mechanisms. The three pillars are useful but incomplete because incidents may need profiles, dumps, audit trails, deployment records, and domain signals. Leading indicators might include queue depth and dependency latency; lagging indicators might include failed orders and stale reports. Cardinality explosion means labels create too many distinct time series.

Classification: 1 log, 2 metric, 3 trace, 4 profile, 5 dump, 6 audit trail, 7 domain signal, 8 health evidence.

## Logging, Metrics, Health, And Tracing

1. `inventory.reservation.failed` is a reasonable stable event name.
2. It creates noise, inconsistent severity, and duplicate stack traces.
3. Usually `info` or `warn`, depending on expectedness and impact; not automatically `error`.
4. Counters: requests received, retries attempted. Gauges: queue depth, active workers.
5. They are high-cardinality and may leak sensitive data.
6. Liveness: worker loop is running. Readiness: queue and database dependency are usable within a timeout.
7. Correlation IDs connect evidence; trace IDs identify trace records.
8. Missing wrapping, lost `ThreadLocal`, missing instrumentation, or cleanup at the wrong time.
9. Sampling decides what to keep; filtering removes data after it exists.
10. IDs make names high-cardinality and hard to aggregate.

## Alerting, SLO, And Incident

1. It has impact, owner, severity, routing, and a clear first action.
2. Usually no; dashboard-only signals should not interrupt unless tied to user impact or strong leading evidence.
3. SLI is a measurement, SLO is a target, SLA is an external commitment.
4. 500 failed valid requests.
5. Error budget is being consumed quickly.
6. Symptoms describe user impact; internal causes may be noisy or downstream.
7. Logs, metrics, traces, thread dump, JFR recording, deployment notes, or configuration changes are valid examples.
8. They may contain secrets, tokens, personal data, and business data from memory.
9. Mitigation reduces impact; diagnosis explains contributing factors.
10. Incidents usually have multiple contributing factors and system conditions.

## Java Code Reading

1. It logs personal data and an authorization header.
2. The request ID creates unbounded metric names.
3. Executor work may lose request or trace context.
4. Example: increment `checkout.failures` with safe dimensions such as operation and error class.
5. Submit two tasks with different contexts to the same single-thread executor and assert each sees only its own context.
6. It writes data during a health check.
7. The database call can hang or amplify load without a timeout.
8. Liveness might only check the process loop; readiness may check whether dependencies are safe enough to receive work.

## Scenario Diagnosis

Slow checkout: symptom is high checkout latency. Cause candidates include payment timeouts and retry behavior. Missing evidence includes payment service health, trace samples, retry success rate, queue depth, and deployment changes. Mitigation might reduce retries, route around payment, or protect queues if evidence supports it. Do not conclude payment is the sole root cause yet.

Stale reports: high queue depth and delayed job logs point to saturation. Normal database errors point away from database failure but do not fully prove it. Helpful trace-like records include enqueue, dequeue, render, store, and completion spans. Slow SLO burn often starts as ticket or daytime response unless user impact becomes urgent. Corrective actions might include worker concurrency review, queue dashboards, and alert tuning.

