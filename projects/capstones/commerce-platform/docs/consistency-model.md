# Consistency Model

Checkout uses immediate consistency for inventory reservation and payment idempotency inside one process. Search projection and notification are eventually consistent because they depend on outbox processing. Cache-aside reads may be stale until eviction.

## Stronger Local Consistency

Inventory reservation and payment idempotency are checked synchronously during checkout. This lets tests prove that one checkout can reserve stock and a second checkout can be rejected when stock is no longer available. The model is intentionally single-process, so it does not claim database isolation, distributed locking, or cross-service transaction safety.

## Eventual Consistency

Search, shipment, and notification depend on events published to the outbox. Until those events are processed, downstream views may lag behind the order state. The capstone makes that lag visible through tests rather than hiding it behind a synchronous all-or-nothing operation.

## Cache Staleness

Product reads use cache-aside behavior. A cached product can be stale after catalogue changes until eviction. That trade-off is common in read-heavy systems: lower repeated read cost in exchange for a staleness window that must be documented, monitored, and acceptable to the business.

## Failure Behaviour

If payment fails after reservation, checkout attempts compensation. If compensation fails, the result is surfaced as a failure instead of pretending the order is clean. Reconciliation is then required to compare orders, reservations, and quarantined events.

## Production Comparison

A deployed system would need database transactions, optimistic or pessimistic concurrency control, durable idempotency storage, event relay guarantees, replay tooling, and reconciliation jobs. This model demonstrates where those mechanisms belong, not that they already exist.
