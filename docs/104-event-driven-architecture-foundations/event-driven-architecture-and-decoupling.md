# Event-Driven Architecture And Decoupling

Event-driven architecture connects parts of a system through events. A producer records or publishes a fact, a channel carries that fact, and one or more consumers react without requiring the producer to call each consumer directly.

In Java terms, the shape often starts as an interface:

```java
public interface EventPublisher {
    void publish(DomainEvent event);
}

public interface EventConsumer<T extends DomainEvent> {
    void handle(T event);
}
```

The interface is simple, but the design questions are not. A good event-driven design needs explicit ownership, delivery expectations, failure handling, and operational visibility.

## Producers Consumers And Channels

A producer is the component that observes or commits a change and makes the event available. A consumer is a component that reacts to the event. A channel is the path between them: an in-memory dispatcher in a lesson, a queue, a topic, a stream, a webhook, or another delivery mechanism in production.

Stage 26 discusses channels conceptually. It does not configure Kafka, RabbitMQ, JMS, cloud queues, or streaming infrastructure.

## Asynchronous Communication

In synchronous request-response, the caller waits for a direct response:

```java
PaymentResult result = paymentClient.authorize(command);
```

In asynchronous event-driven communication, the producer publishes a fact and continues:

```java
publisher.publish(new OrderSubmitted(orderId, customerId, total));
```

The consumer may process the event milliseconds, minutes, or hours later. That delay is not automatically a bug; it is part of the design and must be visible to users and operators.

## Temporal Decoupling

Temporal decoupling means producer and consumer do not need to be available at the same instant. The producer can publish when the consumer is down, and the consumer can catch up later if the channel is durable.

An in-memory example does not provide this durability. It can demonstrate the concept, but if the process exits, the event is gone.

## Spatial Decoupling

Spatial decoupling means the producer does not need to know every consumer. An order service can publish `OrderSubmitted`, while inventory, payment, analytics, and notification consumers subscribe independently.

This is autonomy, not magic. Producers still own the event contract, consumers still depend on event meaning, and teams still need compatibility rules.

## Request Response Versus Event Driven

Request-response is often better for immediate answers, validation, and user-facing reads. Event-driven communication is often better for fan-out, background work, integration boundaries, and workflows where several services need to react to the same business fact.

| Question | Request-response | Event-driven |
| --- | --- | --- |
| Main message | "Do this" or "give me this" | "This happened" |
| Caller waits | Usually yes | Usually no |
| Coupling | Caller knows receiver | Producer knows event contract |
| Failure visibility | Immediate error or timeout | Lag, retry, dead letter, missing reaction |
| Consistency | Often immediate for one boundary | Often eventual across boundaries |

## Synchronous And Asynchronous Workflows

A workflow can mix both styles. A checkout API may synchronously validate a request and save an order, then publish `OrderSubmitted` so inventory and payment processing continue asynchronously.

The user experience must match the workflow. If confirmation is eventual, the UI should show `PENDING`, `CONFIRMED`, or `CANCELLED` rather than pretending every step completed instantly.

## Notifications State Transfer And Streams

Event notification says only that something happened:

```java
record OrderChanged(String orderId) implements DomainEvent {}
```

Consumers then query the producer for details. This keeps events small but creates follow-up coupling and stale-read risks.

Event-carried state transfer includes the data consumers usually need:

```java
record OrderSubmitted(String orderId, String customerId, long totalCents)
        implements DomainEvent {}
```

Event streams are ordered histories of events for a subject or partition. A stream can support replay and rebuilding read models, but only if the system intentionally stores and governs that history.

## Sending A Message Is Not Enough

A system is not event-driven just because it sends messages. If every message is a disguised remote procedure call, every consumer is required to respond immediately, event contracts are ambiguous, and failures are invisible, the architecture has gained complexity without gaining useful decoupling.
