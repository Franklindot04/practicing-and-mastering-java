# Event-Driven Architecture Solutions

## 1. Classify Message Semantics

`SubmitOrder` is a command because it asks the order boundary to attempt work and can be rejected. `OrderSubmitted` is an event because it records a completed fact. `GetOrderStatus` is a query because it asks for data and should not change state. `SendReceiptEmail` is a command or delivery task because email delivery can fail or be rejected. `ReceiptEmailSent` is an event because it records the delivery result.

Assumption: transport does not decide semantics. The same broker topic could carry commands or events, but the meaning still differs.

## 2. Rename Ambiguous Messages

`OrderUpdatedEvent` could become `OrderSubmitted`, `ShippingAddressChanged`, or `OrderCancelled`, depending on the fact. `PaymentProcessedEvent` should be split into `PaymentAuthorized`, `PaymentCaptured`, or `PaymentRejected`. `InventoryEvent` should become `InventoryReserved`, `InventoryReservationFailed`, or `InventoryReleased`.

The original names are unsafe because consumers must guess what changed and may react to the wrong business meaning.

## 3. Identify Fact Versus Intent

`ReserveInventoryEvent` is intent disguised as an event. Better modeling:

```java
record ReserveInventory(String orderId) {}
record InventoryReserved(String orderId) {}
record InventoryReservationFailed(String orderId, String reason) {}
```

The command can be rejected because stock may be unavailable. The result events are immutable facts.

## 4. Model Domain And Integration Events

Internal domain events might include `LineItemQuantityChanged` and `OrderTaxRecalculated`. Candidate integration events include `OrderSubmitted` and `FraudReviewCompleted`.

The order domain owns event meaning. External contracts should be stable and useful outside the boundary, while internal events may evolve with the module.

## 5. Design An Event Envelope

```java
record EventEnvelope<T>(
        String eventId,
        String eventType,
        int eventVersion,
        String source,
        java.time.Instant occurredAt,
        String correlationId,
        String causationId,
        String tenantKey,
        String partitionKey,
        T payload,
        java.util.Map<String, String> metadata
) {}

record OrderSubmitted(String orderId, long totalCents, String currency) {}
```

Validate required envelope fields before publication. Keep business facts in `payload`; keep trace, content type, and producer version in `metadata`.

## 6. Propagate Correlation And Causation

| Event | Event ID | Correlation ID | Causation ID |
| --- | --- | --- | --- |
| `OrderSubmitted` | `evt-1` | `corr-1` | `cmd-submit-1` |
| `InventoryReserved` | `evt-2` | `corr-1` | `evt-1` |
| `PaymentAuthorizationRequested` | `evt-3` | `corr-1` | `evt-2` |

The correlation ID stays stable for the workflow. The causation ID points to the immediate cause.

## 7. Identify Schema-Breaking Changes

Renaming `totalCents` is breaking for name-based consumers. Removing `currency` is breaking if any consumer requires it. Adding `salesChannel` is safe only if optional or defaulted. Changing total from pre-tax to post-tax is a semantic breaking change even if the type stays `long`.

Production consequence: semantic changes need versioning, communication, and contract tests.

## 8. Design Backward-Compatible Evolution

Deploy tolerant consumers first. Add optional `salesChannel` with a documented default such as `UNKNOWN`. Update contract tests so consumers accept old and new events. Then deploy producers that populate the new field. Only after historical replay and all consumers are safe should the field become required.

Alternative: publish `OrderSubmittedV2` while keeping `V1` until consumers migrate.

## 9. Compare Delivery Semantics

At-most-once may lose the notification event after a crash. At-least-once may redeliver it and send duplicate email unless guarded. Scoped exactly-once claims do not make external email exactly once.

Safest protection: use an idempotency key such as `receipt:orderId` and record the send request before calling the email gateway.

## 10. Analyze Duplicate Delivery

Use the logical payment request key, such as `payment-authorization:orderId:attemptGroup`, not a random retry ID. Store it near the payment side effect. If the key already exists, return the existing result or skip the duplicate.

Trade-off: storing by event ID alone may not protect against two different events representing the same logical payment request.

## 11. Implement An Idempotent Consumer

```java
final class ProcessedEventStore {
    private final java.util.Set<String> processed = new java.util.HashSet<>();

    boolean markIfNew(String eventId) {
        return processed.add(eventId);
    }
}

void handle(EventEnvelope<OrderConfirmed> envelope, ProcessedEventStore store) {
    if (!store.markIfNew(envelope.eventId())) {
        return;
    }
    projection.markConfirmed(envelope.payload().orderId());
}
```

Simplification: the set is in memory. Production needs durable atomicity with the protected side effect.

## 12. Choose Acknowledgement Timing

Acknowledging before processing risks loss if the consumer crashes before the side effect. Acknowledging after processing risks duplicate delivery if the side effect succeeds but acknowledgement fails.

Recommendation: acknowledge after the local side effect and make the side effect idempotent. The remaining risk is duplicate processing, which is safer than silent loss for most business workflows.

## 13. Classify Failure Types

Timeout: transient and retry eligible. Malformed payload: permanent until producer or data is fixed. Unknown customer ID: ambiguous; retry if customer data may arrive later, permanent if the event violates the contract. Rate limit: transient with backoff. Unsupported event version: permanent until consumer code changes or upcasting is added.

## 14. Design Retry Policy

Use bounded attempts, such as three tries, exponential backoff, jitter, and a per-consumer retry budget. Stop retrying on permanent validation failures or after exhaustion. Dead-letter the original envelope with attempts, reason, consumer, correlation ID, and replay eligibility.

Avoid infinite retry because it can create retry storms during dependency outages.

## 15. Design Dead-Letter Handling

Dead-letter fields should include event ID, type, version, source, payload, metadata, consumer, attempts, first and last failure time, error class, correlation ID, causation ID, owner, and replay status.

Investigation should validate schema, identify owner, repair code or data, replay safely, and verify business outcome. The original envelope should remain auditable.

## 16. Reason About Replay Safety

Projection updates can usually run during replay if they are deterministic. Email sending should be skipped or guarded by an idempotency record. External side effects should use replay mode, deduplication keys, or explicit operator approval.

Alternative valid design: rebuild read models in an isolated store, then swap after verification.

## 17. Handle Out-Of-Order Events

Track current aggregate version. If incoming version equals current plus one, apply it. If it is less than or equal to current, reject as stale or duplicate. If it is greater than current plus one, defer and record the gap.

When missing events arrive, apply deferred events in version order and verify convergence.

## 18. Choose A Partition Key

For order workflow processing, `orderId` is usually the best partition key because the invariant is per-order ordering. `tenantId` risks hot partitions. `productId` may group unrelated orders. `customerId` may serialize too much work for active customers.

Trade-off: per-order ordering does not provide global order across all orders.

## 19. Identify Hot-Key Risk

If one tenant produces 70 percent of traffic, partitioning by `tenantId` sends most work to one partition. Mitigations include partitioning by `orderId`, adding subkeys, splitting the tenant workload by workflow type, or isolating the tenant.

The mitigation should preserve per-order ordering even if it weakens per-tenant total ordering.

## 20. Compare Orchestration And Choreography

Orchestration centralizes workflow state, timeout handling, and recovery visibility. It couples participants to a coordinator. Choreography gives participants autonomy but can hide dependencies and make recovery ownership unclear.

For checkout, orchestration is often preferable when payment timeouts and compensation need strong visibility. Choreography can work if each participant owns clear events and runbooks.

## 21. Model Saga State

Useful fields include `orderId`, current status, completed steps, expected next event, aggregate version, correlation ID, retry count, deadline, compensation status, and failure reason.

Example statuses: `SUBMITTED`, `INVENTORY_RESERVED`, `PAYMENT_PENDING`, `CONFIRMED`, `CANCELLED`, `REPAIR_REQUIRED`.

## 22. Define Compensation

If inventory was reserved and payment was rejected, compensation is `ReleaseInventory`, followed by `InventoryReleased` or `InventoryReleaseFailed`. It is not rollback because the reservation happened and remains part of history.

If compensation fails, the saga should move to a visible repair state with ownership and retry policy.

## 23. Identify Irreversible Actions

Email cannot be unsent; recovery may require a correction email. Shipment may require recall or support workflow. Captured payment may require refund rather than deletion.

History should show the original action and the corrective action. Deleting history breaks auditability.

## 24. Analyze Dual-Write Failure

Saving the order and then publishing can leave committed state without an event. A transactional outbox writes the order and an outbox row in the same local transaction. A relay publishes rows later.

Because the relay can publish twice after an unknown outcome, consumers still need idempotency.

## 25. Design Consumer Inbox

Inbox fields: consumer name, event ID, event type, processed time, status, side-effect key, and optional error. Processing flow: begin local transaction, insert inbox record if absent, apply side effect, commit, acknowledge.

Production consequence: check and side effect should be atomic. A loose in-memory check is educational only.

## 26. Define Event Metrics

Technical metrics: throughput, consumer lag, event age, processing latency, end-to-end latency, retry count, redelivery count, dead-letter volume, and schema failures.

Business metrics: submitted orders, payment pending count, payment failures, confirmed orders, cancelled orders, and notification failures. Together they show whether the workflow is moving and succeeding.

## 27. Design An Event-Flow Runbook

Runbook: detect dead-letter spike, identify event type and consumer, inspect correlation and causation IDs, classify failure, find owner, decide repair, pause unsafe replay if needed, patch code or data, replay a small batch, verify payment and order outcomes, then replay remaining events and monitor lag and dead letters.

The event owner and consumer owner should jointly decide replay eligibility.

## 28. Analyze Safe Rolling Schema Deployment

First add `currency` as optional with a default for old events. Deploy consumers that tolerate missing and present values. Add contract tests for both forms. Deploy producers that populate `currency`. Backfill or define replay defaults for historical events. Only then enforce `currency` as required in a later version.

This keeps mixed-version deployment and replay safe while avoiding a sudden breaking change.
