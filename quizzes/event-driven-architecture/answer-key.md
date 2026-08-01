# Event-Driven Architecture Answer Key

## Multiple Choice

1. B. An event is an immutable fact. A is a command, C is a query, and D is retry control.
2. B. `PaymentAuthorized` is a precise past-tense fact. The others are commands or ambiguous names.
3. A. Temporal decoupling means producer and consumer do not have to be available at the same instant.
4. B. Correlation ID groups related workflow activity. Partition key is for ordering/routing.
5. A. Causation ID points to the immediate cause of the event.
6. C. Optional additive fields with defaults are usually safest. Removals, renames, and semantic changes are risky or breaking.
7. A. At-least-once delivery permits duplicates, so consumers must be idempotent.
8. A. The side effect may succeed and acknowledgement may fail, causing redelivery.
9. B. Order ID preserves per-order ordering without serializing unrelated orders.
10. A. A hot key concentrates traffic and limits throughput.
11. B. Compensation is a new business action, not history deletion.
12. A. Outbox records bridge local state changes and later publication.
13. A. Consumer inbox records processed event IDs near the protected side effect.
14. A. Dead-letter count shows events that exhausted normal processing.
15. C. Stage 26 examples are educational simulations and must not claim production guarantees.

## Short Answer

1. `ReserveInventory` asks a component to attempt work and can fail. `InventoryReserved` records that reservation already happened.
2. A domain event is internal to a bounded model and can evolve with it. An integration event is an external contract and needs stronger compatibility rules.
3. A consumer can choose its own storage, retry policy, projection, and release cadence. It must still respect event meaning and contract compatibility.
4. Benefit: fewer follow-up queries and more autonomous consumers. Risk: larger contracts and stale or overexposed data if the payload is poorly governed.
5. Consumers may continue compiling while making wrong business decisions. Semantic compatibility matters as much as field shape.
6. A crash after side effect but before acknowledgement can cause redelivery. Defense: idempotency keys or processed-event records.
7. Projection rebuilding is deterministic local state repair. Email is an external side effect that users may see twice unless guarded.
8. Event time is when the fact occurred. Processing time is when a consumer handles it.
9. Useful fields include order ID, status, completed steps, expected next event, version, retry count, deadline, compensation state, failure reason, and correlation ID.
10. Technical metrics show pipeline movement. Business metrics show whether the workflow produced the intended outcome.

## Design And Failure Analysis

1. A strong envelope includes event ID, event type, event version, source, occurred timestamp, correlation ID, causation ID, tenant key, partition key, typed payload, and metadata. `OrderSubmitted` payload might include order ID, customer ID, total cents, and currency. Required fields should be validated.
2. Add `currency` as optional with a default for old events, deploy tolerant consumers, add contract tests for old and new payloads, deploy producers that populate the field, verify replay, then enforce it in a later version if needed.
3. Use a logical idempotency key for the payment request, store it near the payment side effect, and return or skip when the key already exists. Event ID alone may be insufficient if two events represent the same payment intent.
4. Defer `OrderConfirmed(version=5)` because version 4 is missing. Applying it would violate workflow order; rejecting forever may prevent convergence if the missing event arrives later.
5. Retry the timeouts with bounded attempts and backoff. Stop when the unsupported version appears because it is permanent until code or upcasting changes. Dead-letter the original envelope with attempts, reason, consumer, and correlation data.
6. Saga state should show inventory reserved, payment rejected, compensation required, inventory release failed, and repair required. Recovery may retry release, escalate to an owner, or apply a manual business correction.
7. Orchestration gives clearer workflow state, timeout handling, and recovery ownership, which is useful for checkout. Choreography gives participant autonomy but can hide dependencies. A reasonable recommendation is orchestration for the core checkout saga with event notifications for independent observers.
8. The outbox stores the event with the order update in one local transaction. A relay later publishes unpublished rows. If publication succeeds but marking published fails, the relay can publish again, so consumers still need idempotency.
9. Inbox record fields can include consumer name, event ID, event type, side-effect key, status, processed time, and error. Processing inserts the inbox record if absent, applies the side effect atomically, commits, then acknowledges.
10. Inspect consumer lag, event age, retry count, dead-letter count, processing latency, schema failures, and redelivery count. Business metrics include submitted orders, payment pending count, payment rejections, confirmations, and cancellations. Trace fields include event ID, correlation ID, causation ID, order ID partition key, consumer name, and attempt.
