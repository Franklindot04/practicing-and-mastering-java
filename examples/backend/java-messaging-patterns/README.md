# Java Messaging Patterns

This Maven project demonstrates messaging design patterns without requiring Kafka, RabbitMQ, Docker, cloud services, network access, or a running broker.

The in-memory broker is a teaching simulation only. It does not provide production durability, clustering, security, backpressure, partitioning, consumer group rebalancing, or broker-level delivery guarantees.

## What It Demonstrates

- Message producer and consumer interfaces
- Immutable message envelope
- Message id, correlation id, timestamp, and type
- Queue-style delivery
- Publish/subscribe delivery
- Duplicate delivery simulation
- Idempotent consumer behavior
- Retry and dead-letter handling
- Serialization boundary
- Consumer failure handling

## Run Tests

From this folder:

```sh
mvn test
```

## Design Sketch

```text
MessageProducer
  |
  v
InMemoryMessageBroker
  |
  +--> queue consumer
  |
  +--> topic subscriber A
  |
  +--> topic subscriber B
```

Real broker adapters should live behind the same producer and consumer boundaries, while domain code stays unaware of broker-specific APIs.
