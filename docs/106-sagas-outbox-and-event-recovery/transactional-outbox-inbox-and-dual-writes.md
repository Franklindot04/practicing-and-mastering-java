# Transactional Outbox Inbox And Dual Writes

Event-driven systems often need to update local state and publish an event. Doing those as two unrelated writes creates the dual-write problem.

## Dual-Write Problem

This code has a failure gap:

```java
orderRepository.save(order);
eventPublisher.publish(new OrderSubmitted(order.id()));
```

If the save succeeds and publication fails, the order exists but consumers never hear about it. If publication succeeds and the process crashes before recording success, the event may be published again.

## Transactional Outbox

A transactional outbox stores the domain update and an outbox record in the same local transaction.

```java
record OutboxEntry(
        String id,
        String aggregateId,
        String eventType,
        String payload,
        int attempts,
        boolean published
) {}
```

A relay later reads unpublished outbox records and publishes them. Publication retry may create duplicate publication, so consumers still need idempotency.

## Relay Polling And Change Data Capture

A polling publisher periodically reads outbox records. Change-data-capture observes database changes and streams outbox rows to a publisher. Stage 26 introduces both concepts without implementing production infrastructure.

## Failure Between Publication And Acknowledgement

If the relay publishes an event and crashes before marking the outbox row published, the same event may be sent again. That is why event IDs and idempotent consumers remain necessary.

## Consumer Inbox

A consumer inbox records processed event IDs near the consumer side effect. It prevents duplicate delivery from repeating business work.

```java
record InboxRecord(String consumerName, String eventId, String processedAt) {}
```

The inbox check and side effect should be atomic when practical. A loose check-then-act sequence can still race under concurrency.

## Retention Cleanup And Observability

Outbox and inbox records need retention policies. Cleanup must not remove records needed for deduplication, audit, or replay. Observability should track unpublished outbox count, relay failures, duplicate publications, inbox rejects, and cleanup lag.
