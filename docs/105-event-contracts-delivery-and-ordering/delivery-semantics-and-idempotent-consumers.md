# Delivery Semantics And Idempotent Consumers

Delivery semantics describe what a channel and consumer protocol attempt to guarantee. They are often misunderstood, especially when retries and acknowledgements are involved.

## At Most Once

At-most-once delivery means an event is delivered zero or one time. If a consumer crashes after receiving but before processing, the event may be lost. This can be acceptable for low-value telemetry but dangerous for business workflows.

## At Least Once

At-least-once delivery means an event should not be lost if the channel can retry, but it may be delivered more than once. Consumers must be idempotent.

```java
final class ProcessedEventStore {
    private final java.util.Set<String> processed = new java.util.HashSet<>();

    boolean markProcessingIfNew(String eventId) {
        return processed.add(eventId);
    }
}
```

This simplified store demonstrates the idea. Production systems need durable, atomic tracking near the side effect they protect.

## Exactly Once Claims

Exactly-once claims are always scoped. A platform may provide exactly-once processing within a narrow transaction boundary, but that does not automatically make external side effects, email delivery, HTTP calls, or database updates exactly once.

Stage 26 does not claim exactly-once guarantees.

## Duplicate Delivery And Redelivery

Duplicate delivery can happen after timeouts, crashes, replay, publication retries, or unknown outcomes. A duplicate event should not create duplicate business side effects.

Use event IDs, idempotency keys, natural business keys, or processed-event records. The key must match the side effect. For example, payment authorization should deduplicate by logical payment request, not by a randomly regenerated retry ID.

## Acknowledgement Timing

Acknowledging before processing reduces duplicate work but can lose the event if the consumer crashes.

Acknowledging after processing reduces loss but can cause duplicate processing if the acknowledgement fails after the side effect succeeds.

Visibility-timeout systems temporarily hide a message while a consumer works. If the consumer does not acknowledge in time, the event becomes visible again. That protects against stuck consumers but can create concurrent duplicates.

## Replay Safety

Replay means processing old events again to rebuild state, repair a bug, or recover from a failure. Replay-safe consumers avoid non-repeatable side effects or guard them with idempotency.

Unsafe replay:

```java
emailGateway.sendReceipt(event.orderId());
```

Safer replay:

```java
if (receiptStore.markReceiptRequested(event.orderId())) {
    emailGateway.sendReceipt(event.orderId());
}
```

## Poison Events And Consumer Crashes

A poison event repeatedly fails because its data is invalid, unsupported, or triggers a deterministic bug. Retrying forever can block progress. Bounded retries and dead-letter handling preserve the event for investigation while allowing other events to continue.

Consumer crashes create unknown outcomes. The event may have been processed, partially processed, or not processed at all. Idempotency and operational logs make recovery possible.
