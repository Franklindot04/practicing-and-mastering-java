# Transactions Consistency And Workflows

Consistency decisions should protect business invariants. Eventual consistency is useful for many workflows, but it is not acceptable for every invariant.

## Transaction Options

ACID transactions provide atomicity, consistency, isolation, and durability within a database boundary. Isolation levels affect anomalies such as dirty reads, non-repeatable reads, phantom reads, and write skew.

Local transactions are easier to reason about than distributed transactions. If inventory reservation and order creation must be atomic, keeping them in one transactional boundary may be simpler than splitting them prematurely.

Optimistic concurrency uses versions or compare-and-swap rules. It fits low-conflict updates and clear retry behaviour.

Pessimistic locking reserves access before update. It can protect hot records but may reduce throughput and increase deadlock risk.

Distributed transactions and two-phase commit concepts coordinate multiple resources, but they add blocking, coordinator failure modes, and operational complexity. Many modern service designs avoid them by changing workflow boundaries.

## Sagas And Compensation

A saga coordinates a multi-step workflow using local transactions and compensating actions. Example checkout saga:

1. reserve inventory
2. authorize payment
3. create order
4. request shipment
5. emit notification

If payment fails after inventory is reserved, compensation releases inventory. Compensation is not magic undo. A refund is not the same as never charging. A cancellation email is not the same as never notifying.

## Outbox Inbox And Idempotency

The outbox pattern records events in the same local transaction as state changes. A relay later publishes the events. This avoids the classic failure where a database commit succeeds but event publishing fails.

The inbox or processed-message store records consumed message IDs so duplicate delivery does not repeat side effects. Acknowledgements should happen after durable side effects and idempotency records are committed.

Idempotency keys should store the original request identity, result, expiry, and conflict behaviour. Retrying a payment or order submission after an uncertain timeout must not duplicate side effects.

## Invariants And Pending States

Identify invariants explicitly:

- inventory available count cannot become negative
- payment capture cannot exceed authorized amount
- an order cannot be shipped before confirmation
- a tenant cannot read another tenant's private records
- audit logs cannot be modified by normal users

User-visible pending states are often honest. "Payment pending" or "inventory reservation expiring" may be safer than pretending all distributed work completes instantly.

## Reconciliation And Audit

Eventually consistent systems need reconciliation. Jobs can compare source-of-truth records, projections, outbox status, processed messages, and provider settlement reports. Repairs should be auditable and repeatable.

State machines make valid transitions reviewable. They prevent accidental jumps such as `CART` to `SHIPPED` or `PAYMENT_FAILED` to `DELIVERED` without required intermediate decisions.
