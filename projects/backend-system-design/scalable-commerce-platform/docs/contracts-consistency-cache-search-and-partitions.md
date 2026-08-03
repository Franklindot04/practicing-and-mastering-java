# Contracts Consistency Cache Search And Partitions

API contracts and event contracts are versioned and checked for required-field compatibility. Checkout uses a saga with inventory reservation, payment idempotency, order confirmation, outbox publishing, processed-message tracking, dead-letter handling, and reconciliation.

Catalogue reads use cache-aside with expiry and stampede prevention. Search is a derived projection with measured lag and rebuild assumptions. Partition routing uses deterministic hashing, hot-partition detection, and rebalance planning.
