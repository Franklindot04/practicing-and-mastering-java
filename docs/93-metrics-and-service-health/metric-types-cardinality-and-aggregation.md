# Metric Types, Cardinality, And Aggregation

## Counters, Gauges, And Distributions

Counters are monotonic: `requests_total`, `orders_failed_total`, or `dependency_timeouts_total`. Gauges move up and down: `queue_depth`, `active_threads`, or `heap_used_bytes`. Histograms record observations into buckets so percentiles can be estimated from a distribution.

Use explicit units in names or metadata:

- `duration_seconds`
- `heap_used_bytes`
- `requests_total`
- `queue_depth`

Base units reduce confusion. A dashboard that mixes milliseconds and seconds invites mistakes.

## Aggregation And Percentiles

Aggregating counters is usually straightforward: totals can be summed by operation or service. Aggregating percentiles is harder because an average of p95 values is not the global p95. Prefer distributions when cross-instance percentile reasoning matters.

Bucket selection is a tradeoff. Wide buckets are cheaper but less precise. Narrow buckets are more precise but increase storage and processing cost.

## Cardinality

| Label | Cardinality | Guidance |
| --- | --- | --- |
| `operation=listTasks` | Low | Good metric label. |
| `outcome=success` | Low | Good metric label. |
| `exception=TimeoutException` | Medium when controlled | Useful if normalized. |
| `userId=12345` | High | Avoid as metric label. |
| `requestId=req-abc` | Unbounded | Use in logs/traces, not metrics. |

Cardinality explosion happens when labels create too many time series. It increases cost, slows queries, and can cause important metrics to be dropped.

## Metric Lifecycle

Metrics are contracts with dashboards, alerts, and learners. Rename or remove them deliberately:

- Keep names stable.
- Document units and label meanings.
- Avoid labels that contain secrets or personal data.
- Remove unused metrics after downstream references are updated.
- Test important metric increments and classifications.

