# Messaging Systems And Kafka Foundations

Messaging moves work between applications without requiring every participant to be online, fast, or deployed together. This section maps the broker-independent ideas from Stage 26 to concrete broker behavior, with Kafka as the first detailed technology.

## Scope

This section is Java-oriented and infrastructure-light. It explains concepts, configuration choices, and failure modes without requiring a running broker for learning or default repository tests.

## Recommended Reading Order

1. [Messaging Systems Queues Topics And Delivery](messaging-systems-queues-topics-and-delivery.md)
2. [Kafka Topics Partitions Offsets And Consumer Groups](kafka-topics-partitions-offsets-and-consumer-groups.md)
3. [Kafka Producer Consumer And Delivery Semantics](kafka-producer-consumer-and-delivery-semantics.md)
4. [Kafka Retention Replay Compaction And Operations](kafka-retention-replay-compaction-and-operations.md)

## Learning Goals

After this section, you should be able to:

- compare queues, topics, fan-out, and competing consumers
- explain producer, publisher, consumer, and subscriber responsibilities
- describe Kafka topics, partitions, offsets, records, keys, replicas, and consumer groups
- choose partition keys while reasoning about ordering and hot partitions
- explain producer acknowledgements, batching, retries, and idempotent producer concepts
- compare automatic and manual offset commits
- explain rebalances, lag, replay, retry topics, and dead-letter topics
- describe the limited scope of Kafka exactly-once semantics
- avoid confusing broker durability with business completion

## Review Questions

1. Why does broker acceptance not prove that a downstream business action completed?
2. Why does Kafka preserve order only within a partition?
3. What can go wrong if a consumer commits an offset before completing its side effect?
4. Why can replay repair a projection but still be dangerous for external side effects?
5. Which metrics would you inspect for a Kafka consumer falling behind?
