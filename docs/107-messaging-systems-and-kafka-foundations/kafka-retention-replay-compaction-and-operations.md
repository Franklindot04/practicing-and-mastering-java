# Kafka Retention Replay Compaction And Operations

## Retention And Replay

Kafka retains records by time, size, or compaction policy. Consumers can reset offsets and replay retained data to rebuild projections, reprocess analytics, or recover from a bug. Replay is powerful but dangerous when handlers perform external side effects.

Projection rebuilds should separate deterministic state reconstruction from protected side effects. Replaying `OrderPaid` may rebuild a report; it must not charge the customer again.

## Compaction

Log compaction keeps the latest record per key while allowing Kafka to act as a changelog. It is useful for state snapshots and table-like topics. Compaction is not a substitute for audit history when every change must be retained.

Tombstones represent deletes for compacted topics. Consumers must understand whether missing state means never existed, deleted, or expired by retention.

## Retry And Dead-Letter Topics

Kafka has no built-in delayed queue. Common Java designs publish failed records to retry topics with delay tiers or to a scheduler-backed retry system. Attempt headers should record original topic, partition, offset, exception class, failure time, and retry count.

Dead-letter topics hold records that exhausted retry budget or failed permanently. They need owners, alerts, retention policy, privacy review, and replay tooling. A dead-letter topic without a repair process is only a slower incident.

## Operational Metrics

Watch:

- producer send rate, error rate, batch size, request latency, buffer availability
- topic throughput, partition skew, under-replicated partitions, offline partitions
- consumer lag, poll latency, processing latency, commit failures, rebalance frequency
- retry topic volume, dead-letter volume, poison-record rate
- schema failures, message-size failures, authorization failures
- disk usage, retention pressure, replication health

## Operational Readiness

Production systems need runbooks for lag spikes, poison records, broker incidents, credential rotation, TLS failures, replay, schema rollback, hot partitions, and consumer deployment. The team should know who owns each topic, each contract, and each dead-letter destination.

Capacity planning should consider record size, partitions, expected throughput, retention, replication factor, consumer parallelism, and downstream side-effect latency. Adding partitions can improve parallelism, but it can also alter key distribution and operational behavior.
