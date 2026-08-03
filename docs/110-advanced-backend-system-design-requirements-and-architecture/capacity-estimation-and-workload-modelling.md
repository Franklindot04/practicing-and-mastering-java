# Capacity Estimation And Workload Modelling

Capacity modelling is not fortune telling. It is a disciplined way to expose scale-sensitive decisions before implementation. Early estimates should use ranges, headroom, and validation plans because product demand, traffic shape, payload size, and data growth are uncertain.

## Core Inputs

Start with the smallest useful model:

- monthly active users, daily active users, and peak concurrent users
- requests per second at average, peak, and launch-event load
- read/write ratio by endpoint or workflow
- payload size for requests, responses, events, logs, and stored records
- storage growth per day and retention period
- cacheable read percentage and expected cache hit rate
- queue ingress rate, consumer throughput, retry rate, and acceptable queue depth
- regional distribution of users and traffic
- external dependency latency and rate limits
- worker capacity and connection limits
- failure capacity, such as surviving one instance, zone, or region loss

## Simple Estimation Example

Assume a product catalogue receives 2,000 reads per second at peak and 20 writes per second. Average response payload is 12 KiB. Without compression or caching, peak egress is roughly:

```text
2,000 reads/second * 12 KiB = 24,000 KiB/second
24,000 KiB/second ~= 23.4 MiB/second
23.4 MiB/second * 86,400 seconds ~= 1.9 TiB/day at sustained peak
```

The sustained-peak number is intentionally conservative. A better model separates average, peak, and burst windows. Even this simple estimate reveals questions about CDN caching, response trimming, pagination, network transfer cost, and client-side freshness expectations.

## Concurrency And Latency

Little's Law is a useful approximation:

```text
concurrency ~= throughput * average latency
```

If checkout handles 300 requests per second and the average end-to-end latency is 500 ms, about 150 requests are in flight. If a dependency slows to 3 seconds, in-flight work rises to 900. That can exhaust servlet threads, connection pools, bulkheads, queues, memory, or payment-provider rate limits.

## Queue And Worker Capacity

Queues absorb bursts only when consumers can catch up. Estimate:

- ingress events per second
- handler processing time
- worker concurrency
- retry volume
- poison-message rate
- acceptable lag
- maximum queue depth before user impact

Example:

```text
each worker handles 25 events/second
8 workers handle 200 events/second
peak ingress is 260 events/second for 10 minutes
backlog growth is 60 events/second * 600 seconds = 36,000 events
```

The design must decide whether that backlog is acceptable, whether to add workers, shed non-critical work, delay lower-priority consumers, or reduce event volume.

## Partition Counts And Hotspots

Partitioning choices affect write throughput, read locality, ordering, rebalancing, and operational complexity. A good partition key spreads load while preserving the ordering and query patterns that matter.

Model:

- expected keys per second
- skew, such as one tenant, product, region, or celebrity event receiving unusual traffic
- cross-partition query cost
- secondary-index needs
- rebalancing plan
- future partition-count changes

A hash of `customerId` may distribute cart traffic well. A hash of `country` may create hot partitions because traffic is not evenly distributed. A timestamp range may make recent partitions hot while older partitions are idle.

## Headroom And Failure Capacity

Capacity plans should include headroom. A system running at 90 percent saturation during normal peak has little room for retries, garbage collection pauses, deployment overlap, regional evacuation, or dependency slowness.

Failure capacity asks what remains after losing capacity:

- one process
- one node
- one availability zone
- one consumer group member
- one cache cluster
- one database replica
- one region

Multi-region does not automatically improve reliability. It adds replication lag, routing complexity, operational cost, conflict risks, and failover failure modes. A single-region design with strong backup and restore may be more honest for some products.

## Cost Ranges And Uncertainty

Capacity estimates should produce cost ranges, not false precision. Include compute, storage, network transfer, managed-service premiums, logs, traces, backups, cross-region replication, operational labour, and engineering complexity.

Document uncertainty:

- low, expected, and high traffic scenarios
- cache hit-rate sensitivity
- payload-size sensitivity
- retry-rate sensitivity
- regional traffic assumptions
- data-retention assumptions

## Validation Plan

Early validation can include production-like load tests, replay of anonymized traffic, synthetic workload generators, metrics review, database query plans, and Java simulations. Stage 28 uses deterministic simulations and fitness checks to teach reasoning, but those checks do not prove production readiness. Stage 29 focuses on testing strategy, performance, profiling, and JVM tuning in greater depth.
