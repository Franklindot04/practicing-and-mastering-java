# Metrics And Service Health

Metrics summarize behavior over time. They are strongest for rates, resource pressure, latency distributions, trends, and service health, but they lose detail compared with logs and traces.

## Study Order

1. [Metric Types, Cardinality, And Aggregation](metric-types-cardinality-and-aggregation.md)
2. [JVM, Application, And Dependency Metrics](jvm-application-and-dependency-metrics.md)
3. [Service Health And Dashboard Design](service-health-and-dashboard-design.md)

## Metric Primitives

| Type | Use |
| --- | --- |
| Counter | Monotonic total such as requests received or failures. |
| Gauge | Current value such as queue depth, active jobs, or heap used. |
| Histogram | Distribution of observations such as request duration. |
| Summary | Conceptual precomputed distribution summary. |
| Timer | Duration observations, often backed by a histogram. |

Rates are calculated from counters over a time window. Deltas compare two observations. Cumulative values should not decrease unless the process restarts or the metric resets.

## From Questions To Metrics

| Operational question | Useful metric |
| --- | --- |
| Is traffic normal? | Request rate by operation. |
| Are users seeing failures? | Error rate by operation and safe error class. |
| Is latency worse? | Duration histogram with p50, p95, and p99 views. |
| Are workers saturated? | Queue depth, active workers, rejected work count. |
| Is a dependency unhealthy? | Dependency timeout count, latency distribution, retry count. |

## Review Questions

1. Why is an average latency often misleading?
2. When is a counter better than a gauge?
3. Why should request IDs not be metric labels?
4. What is the risk of deep health checks?
5. How should dashboards begin with operational questions?

