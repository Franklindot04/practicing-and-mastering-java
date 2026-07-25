# Messaging And Streaming Solutions

## Solution 1: Choose Queue Topic Or Direct Call

Payment authorization usually needs direct request/response because checkout needs an immediate answer. Password reset email and thumbnail generation can use queues because they are background work. Audit logging and dashboard projections can use publish/subscribe when several independent consumers need the same fact.

Messaging is unnecessary when the work is local, immediate, and has no independent consumer.

## Solution 2: Delivery Semantics Design

Use at-least-once delivery and make the consumer idempotent. Store a processed message id or invoice operation id before acknowledging. If the consumer writes the receipt record and crashes before acknowledgement, redelivery should find the existing processed record and skip the side effect.

Exactly-once is difficult because the producer, broker, consumer, acknowledgement, database, and email side effect can fail independently.

## Solution 3: Retry And Dead-Letter Policy

Timeouts are likely transient, so retry with backoff and a limit. Malformed payloads are likely permanent, so retrying repeatedly wastes capacity. Dead-letter records should include message id, correlation id, message type, payload version, failure reason, attempts, consumer name, and timestamps.

The dead-letter queue creates visibility; it does not repair the message by itself.

## Solution 4: Idempotent Consumer

The consumer should check durable idempotency storage before applying the read-model update. If the message was processed, acknowledge and skip. If not, update the read model and mark the message processed in a safe transaction before acknowledging.

A unique database constraint helps when two workers race to process the same logical operation.

## Solution 5: Ordering And Keys

Use `taskId` as the key so records for the same task are routed consistently. In Kafka this usually keeps those records in one partition, preserving partition order for that task.

Random keys can scatter related records across partitions, removing per-task ordering. Retries can still delay one record and make effects appear out of sequence outside the broker.

## Solution 6: Kafka Consumer Group Reading

With three partitions and two consumers, one consumer may own two partitions and the other may own one. Each partition has offsets, and the group records progress per partition. Lag means the group is behind the latest records.

When a third consumer joins, partitions can rebalance so each consumer may own one partition.

## Solution 7: RabbitMQ Routing

A topic exchange fits this routing. Bind audit with `task.*` so it receives created, closed, and failed messages. Bind email with `task.created` and `task.failed`. A failures-only queue can bind with `task.failed`.

This is RabbitMQ-style routing through exchange, binding, and routing key decisions.

## Solution 8: Serialization Boundary

Use an interface such as `MessageSerializer` with `serialize` and `deserialize` methods. Domain handlers should receive domain events or message envelopes, not raw broker records.

Compatibility tests should verify older payloads still deserialize and unsupported versions fail clearly.

## Solution 9: Operational Troubleshooting

Start by confirming producer publish success, then inspect queue depth or consumer lag. Check consumer throughput, processing latency, retry counts, and dead-letter volume. Use correlation ids to trace delayed messages from original request through publish and consumer logs.

One mitigation might be temporarily increasing consumer instances if the dependency can handle it and messages are safe to process in parallel.

## Solution 10: Messaging Abstraction Review

Move broker APIs behind producer and consumer ports. Domain services should emit domain events to a boundary, and adapters should translate those events to Kafka records or RabbitMQ messages later.

A clean package shape might include `domain.task`, `messaging.port`, `messaging.envelope`, `messaging.retry`, `messaging.idempotency`, and future `adapter.kafka` or `adapter.rabbitmq` packages.
