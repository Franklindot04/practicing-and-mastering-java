# RabbitMQ Exchanges Queues Routing And Acknowledgements

## Exchanges, Queues, And Bindings

RabbitMQ publishers send messages to exchanges. Exchanges route messages to queues through bindings. Consumers receive messages from queues. A routing key and exchange type determine which queues receive a message.

Common exchange types:

- direct: exact routing-key match
- topic: pattern match such as `orders.*.paid`
- fanout: broadcast to all bound queues
- headers: route by message headers at a high level

A Java application should keep routing decisions near the adapter boundary:

```java
record RabbitRoute(String exchange, String routingKey, String queue) {
}
```

Domain code should not need to know channel state or broker connection details.

## Queue Properties

Durable queues survive broker restart when configured with durable metadata. Persistent messages request disk persistence. These settings improve durability, but they do not guarantee business completion. A consumer can still fail after delivery, or an external database can reject the side effect.

Exclusive queues are tied to one connection. Auto-delete queues disappear when no longer used. They are useful for temporary subscribers and tests, not durable workflows.

## Acknowledgements And Negative Acknowledgements

With manual acknowledgement, a consumer explicitly acknowledges a delivery after successful handling. A negative acknowledgement can reject or requeue the delivery. Requeueing a poison message can create a tight failure loop that starves healthy work.

Acknowledgement timing is a correctness decision:

- acknowledge before side effects: less duplicate delivery, higher loss risk
- acknowledge after side effects: safer completion, duplicate risk after crashes
- reject without requeue: useful for permanent failures when dead-letter routing is configured

Consumers still need idempotency because redelivery can happen after successful side effects.

## Prefetch, Concurrency, And Fairness

Prefetch limits unacknowledged messages per consumer or channel. A high prefetch can improve throughput but may let one slow consumer hold many messages. A low prefetch improves fairness but may reduce throughput.

Queue depth and unacknowledged counts should be read together. High queue depth means backlog. High unacknowledged count means consumers have deliveries in flight and may be slow, overloaded, or stuck.

## Connections And Channels

RabbitMQ clients use TCP connections and lightweight channels. Applications usually keep long-lived connections and create channels for publishing or consuming patterns. Channel errors often require recreating the channel. Connection failures require reconnect and topology recovery decisions.
