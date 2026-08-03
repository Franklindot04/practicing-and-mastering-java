# Backend System Design Multiple Choice Quiz

## MC1

Which item is a non-functional requirement?

A. Customers can add products to a cart.
B. Checkout p95 latency should stay below 500 ms at expected peak load.
C. Administrators can update catalogue descriptions.
D. Users can view order history.

## MC2

Which statement about microservices is safest?

A. They are always more scalable than monoliths.
B. They remove the need for data ownership.
C. They help when independent ownership, deployment, and scaling justify distributed operations.
D. They make transactions simpler by default.

## MC3

What is the main correctness risk of cache-aside for inventory decisions?

A. Cache-aside cannot store strings.
B. Stale cached values can violate correctness-critical invariants.
C. Cache-aside always requires a CDN.
D. Cache-aside prevents observability.

## MC4

Which pattern records events in the same local transaction as state changes?

A. Outbox
B. Circuit breaker
C. Bulkhead
D. Blue-green deployment

## MC5

What does RPO describe?

A. Maximum acceptable recovery time.
B. Maximum acceptable data loss window.
C. Number of replicas per partition.
D. Request percentile objective.

## MC6

Which API concern is most important before retrying a write after timeout?

A. Whether the write is idempotent or has an idempotency key.
B. Whether the response was compressed.
C. Whether the URL uses plural nouns.
D. Whether the client uses GraphQL.

## MC7

Which signal best indicates consumer backlog?

A. Consumer lag or queue depth.
B. Java source line count.
C. Number of dashboards.
D. API version name.

## MC8

Which schema change is usually safest?

A. Removing a required field.
B. Renaming a field in place.
C. Changing cents to dollars in the same field.
D. Adding an optional field with tolerant readers.

## MC9

Which item belongs in an ADR?

A. Only the final diagram.
B. Context, decision, alternatives, consequences, risks, assumptions, and validation criteria.
C. Only implementation code.
D. Only the deployment command.

## MC10

Which cost is easiest to forget in backend architecture reviews?

A. Operational labour and engineering complexity.
B. Product names.
C. HTTP method names.
D. Java package names.
