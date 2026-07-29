# Observability Vocabulary And Models

## System State And Internal Behavior

External state is what users and callers can observe: responses, errors, latency, missing data, or stale results. Internal behavior is how the system produced that state: validation paths, retries, database calls, lock contention, garbage collection, dependency failures, and queue pressure.

Good observability connects the two without assuming telemetry automatically explains every failure. Evidence can be incomplete, delayed, sampled, or misleading.

## Known And Unknown Failure Modes

| Failure type | Example | Useful evidence |
| --- | --- | --- |
| Known known | The service returns 404 for missing tasks. | Request count, status count, structured validation logs. |
| Known unknown | A dependency may become slow under load. | Dependency latency histogram, timeout count, retry count. |
| Unexpected mode | A retry path amplifies queue pressure after a partial outage. | Request correlation, retry events, queue metrics, worker traces. |

An observable system makes unexpected behavior easier to investigate. It does not remove the need for testing, incident practice, or careful engineering judgment.

## Symptoms, Causes, Signals, And Context

A symptom is visible impact: users receive errors, pages load slowly, jobs miss deadlines, or data becomes stale. A cause is a contributing mechanism, such as a saturated executor, an invalid deployment setting, a broken dependency contract, or a memory leak.

Signals become more useful when they carry context:

- Stable service name and operation name.
- Request, correlation, trace-like, or job identifier.
- Outcome and error classification.
- Duration and retry attempt.
- Dependency name using a low-cardinality value.
- Safe domain attributes such as product category or queue name.

Correlation links multiple evidence records. It should help answer "which records describe the same unit of work?" without storing secrets or personal data.

## Cardinality And Dimensionality

Dimensionality is the set of labels, tags, attributes, or fields attached to telemetry. Cardinality is the number of distinct values for each dimension.

| Dimension | Cardinality | Safer use |
| --- | --- | --- |
| `operation=createOrder` | Low | Metric label, log field, span attribute. |
| `status=success` | Low | Metric label, dashboard grouping. |
| `requestId=req-8271` | High | Log field or trace correlation, not a metric label. |
| `email=learner@example.test` | High and sensitive | Avoid or redact. |

High-cardinality data may be useful in logs or traces for investigation. It is usually unsafe as a metric label because it can create unbounded time series.

## Boundaries And Planes

Service boundaries show where ownership, failure handling, and diagnostic responsibility change. Dependency maps help identify which services, queues, databases, caches, and workers participate in a workflow.

Control plane signals describe management behavior: configuration changes, deployments, routing decisions, scaling events, and feature-flag changes. Data plane signals describe user or job traffic: requests, messages, queries, retries, and responses.

