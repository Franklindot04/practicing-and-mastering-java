# Kafka Topics Partitions Offsets And Consumer Groups

## Kafka Building Blocks

A Kafka cluster contains brokers. A topic is split into partitions. Each partition is an ordered log of records. A record has a key, value, headers, timestamp, and offset. The offset is a position in one partition, not a global message number.

Partitions have leaders and replicas. Producers write to partition leaders. Consumers read from leaders. Replication improves availability, but producers only receive the acknowledgement level they request.

## Keys, Partitions, And Ordering

Kafka preserves order within one partition. It does not preserve a single global order across all partitions in a topic. A Java producer usually chooses a key so related records land on the same partition:

```java
String key = orderId.toString();
ProducerRecord<String, byte[]> record =
    new ProducerRecord<>("orders.events", key, serializedEvent);
```

Good keys balance two needs: related events must stay ordered, while traffic should spread across partitions. `orderId` is often good for per-order workflows. A tenant ID alone may create hot partitions if one tenant dominates traffic. A random key spreads load but breaks per-entity ordering.

## Consumer Groups

A consumer group is a set of consumers sharing work for a topic. Kafka assigns each partition to at most one consumer in the group at a time. If the group has more consumers than partitions, some consumers are idle. If membership changes, Kafka rebalances partition assignments.

Rebalancing matters because a consumer may be stopped while it has processed records that are not yet committed. Handlers should be idempotent, commit deliberately, and close cleanly when partitions are revoked.

## Offsets And Commits

Consumers poll records and commit offsets to record progress. With automatic commits, the client commits on an interval. With manual commits, application code decides when a batch or record is safe to mark processed.

Commit after processing reduces message loss risk but allows duplicates after a crash. Commit before processing reduces duplicate reads but can lose work if the process dies before the side effect completes. Production Java consumers usually pair manual commits with idempotent writes.

## Lag

Consumer lag is the distance between the latest produced offset and the committed or current consumed offset. Lag can mean the consumer is slow, paused, failing on poison records, rebalancing too often, undersized, or blocked on an external dependency.

Lag is a symptom. Investigate processing latency, error rates, retry volume, rebalance frequency, partition skew, broker health, and downstream dependency health before simply adding consumers.
