# Kafka Foundations

Kafka is a distributed event streaming platform often used when systems need durable streams of records, scalable consumers, and replayable event history.

This section is foundational. It does not provision a cluster, configure authentication, use cloud-provider services, include credentials, or prescribe production tuning defaults.

## Topics

- [Kafka Architecture](kafka-architecture.md)
- [Topics Partitions Offsets And Records](topics-partitions-offsets-records.md)
- [Producers Consumers And Groups](producers-consumers-groups.md)
- [Keys Ordering Retention And Replay](keys-ordering-retention-replay.md)
- [Strengths Tradeoffs And Mistakes](strengths-tradeoffs-mistakes.md)

## Terminology

| Term | Meaning |
| --- | --- |
| Broker | Kafka server that stores partitions and serves producers and consumers. |
| Topic | Named stream of records. |
| Partition | Ordered slice of a topic. |
| Offset | Position of a record within a partition. |
| Record | Key, value, headers, timestamp, and metadata. |
| Producer | Application that writes records. |
| Consumer | Application that reads records. |
| Consumer group | Consumers sharing work for a topic. |

## Big Idea

Kafka is not just a queue. It stores ordered records in partitions and lets consumers track their positions.
