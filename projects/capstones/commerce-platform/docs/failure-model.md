# Failure Model

Covered failures include inventory shortage, contention, duplicate requests, payment rejection, payment timeout, compensation failure, stale cache, projection lag, redelivery, poison events, incompatible schemas, dependency degradation, capacity pressure, and replay after restart.

## Failure Categories

- Business rejection: invalid cart, unavailable inventory, payment rejection, and capacity rejection.
- Uncertain outcome: payment timeout and message redelivery.
- Compensating action: reservation release after payment failure.
- Operational backlog: search projection lag and dead-letter accumulation.
- Contract failure: incompatible schema versions.
- Dependency degradation: optional downstream work unavailable while core checkout continues.

## Behavioural Expectations

Failures should return explicit reasons, update metrics, and leave enough state for reconciliation. The capstone avoids silent recovery because silent recovery makes learning and operations harder. Duplicate requests return stable results where idempotency keys are available. Poison or incompatible events are quarantined rather than retried forever.

## Trade-Offs

Rejecting work under capacity pressure can be frustrating for callers, but it protects the system from unbounded queues and misleading success. Quarantining poison messages delays completion, but it prevents repeated damage. Compensation keeps checkout available without distributed transactions, but it creates reconciliation work when compensation itself fails.

## Future Improvements

Useful future work would add richer failure classifications, retry budgets per event type, reconciliation repair plans, and persistent audit history. Those would still need to be presented as implementation improvements, not production proof, unless backed by deployed evidence.
