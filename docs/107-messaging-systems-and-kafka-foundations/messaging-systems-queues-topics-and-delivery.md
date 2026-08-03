# Messaging Systems Queues Topics And Delivery

## Why Messaging Exists

A broker accepts messages from producers and makes them available to consumers. That gives systems temporal decoupling, buffering, fan-out, and isolation from short consumer outages. It does not prove that business work completed. A broker can store `OrderSubmitted` successfully while payment, inventory, email, or projection consumers later fail.

Java applications usually interact with messaging through a client library, framework adapter, or application port:

```java
interface MessageProducer<T> {
    SendOutcome send(MessageEnvelope<T> envelope);
}

interface MessageConsumer<T> {
    ProcessingResult handle(MessageEnvelope<T> envelope);
}
```

The application boundary should keep broker details out of the domain model. Domain code should know that an order event exists; adapter code should know whether it became a Kafka record, RabbitMQ delivery, or JMS message.

## Queues And Topics

A queue normally distributes each message to one competing consumer. Multiple consumer instances can share the work, which improves throughput but means a single logical message is handled by one member of that group.

A topic normally broadcasts each message to many independent subscribers. Each subscriber group receives its own copy or independent position. Fan-out is useful when billing, search indexing, analytics, and notification all need to react to the same fact.

Broker terminology differs. Kafka topics are append-only logs consumed by groups. RabbitMQ topics are often modeled with exchanges, queues, bindings, and routing keys. JMS defines Queue and Topic abstractions, but provider behavior and extensions vary.

## Delivery And Ownership

Common roles:

- producer: creates and sends a message
- publisher: commonly used when sending to a topic or exchange
- consumer: receives and processes a message
- subscriber: commonly used when receiving from a topic
- broker: accepts, stores, routes, and delivers messages

Ownership is split. A producer owns the message contract and the fact it publishes. A consumer owns its reaction. The broker owns storage and delivery mechanics within its configured durability and availability limits.

## Durability, Acknowledgements, And Redelivery

Durability means the broker attempts to keep accepted messages across failures according to configuration. Acknowledgement means a consumer or client tells the broker which delivery can be considered handled. Neither concept eliminates duplicate processing.

If a consumer completes a side effect and crashes before acknowledgement, the broker may redeliver. If a consumer acknowledges before the side effect completes, the message may be lost from that consumer's perspective. Production consumers therefore use idempotency keys, inbox tables, or natural business constraints.

## Buffering And Backpressure

Queues and logs absorb bursts, but they are not infinite. Watch queue depth, consumer lag, disk usage, retention pressure, unacknowledged message counts, and processing latency. Backpressure can appear as producer timeouts, growing batches, throttling, delayed retries, or rejected publications.

## Failure Modes

Producer failure can leave the application uncertain: did the broker accept the message, or did the acknowledgement fail on the way back? Consumer failure can cause redelivery. Broker failure can reduce availability, lose unreplicated data, or delay routing depending on the technology and configuration.

The safest design assumes at-least-once effects unless a narrower guarantee has been proven for a specific boundary.
