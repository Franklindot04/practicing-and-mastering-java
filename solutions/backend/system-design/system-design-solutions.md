# System Design Solutions

## Exercise 1: Requirements Clarification For Order Search

A strong answer starts by separating customer search from support search. Customer search needs strict authorization and may use simple filters. Support search may need broader access, audit logging, and safer result shaping.

Useful non-functional questions include latency target, freshness, result size, privacy, expected query fields, and support workflow frequency. A first design could query existing relational data with indexes. A later design could add a read model or search index if query complexity and traffic justify it.

Tradeoff: adding a search system early may improve query flexibility but adds synchronization, privacy review, and operational cost.

## Exercise 2: Capacity Estimate For A Task API

50,000 users creating 4 tasks per day is 200,000 writes per day. Dividing by 86,400 seconds gives roughly 2.3 writes per second as a daily average.

50,000 users reading 20 times per day is 1,000,000 reads per day, or roughly 11.6 reads per second as a daily average.

These averages are not enough. A learner should choose and justify a peak multiplier, such as 5x or 10x, then monitor real request rate, latency, database load, slow queries, and cache hit rate if a cache is introduced.

Caching is not automatically required from the average numbers. It may become useful if reads are expensive, repeated, and tolerant of staleness.

## Exercise 3: Modular Monolith Or Microservices

Given one team, unproven traffic, and limited operations capacity, a modular monolith is a reasonable starting point. Suggested modules: orders, inventory integration, notification integration, and customer-facing API.

The design should prevent casual sharing of persistence entities and domain internals. Inventory and notification can be represented as interfaces even before they become separate services.

Signals that may justify extraction include separate teams, independent scaling pressure, different deployment cadence, or a legacy integration that needs isolation.

Tradeoff: starting modular keeps deployment simple, but the team must enforce boundaries intentionally.

## Exercise 4: Consistency And Idempotency

Idempotency is required at order submission and at the payment boundary. The backend should store an idempotency key with the request fingerprint and result. Duplicate submissions should return the original result when safe.

Retries are safe only when the operation is idempotent or the downstream system supports idempotency. A payment timeout is ambiguous: the design may need a pending state and reconciliation rather than blind retry.

A circuit breaker can open when payment failures or timeouts exceed a threshold. Backpressure may be appropriate when payment latency causes request threads or queues to saturate.

Tradeoff: pending states improve correctness but make user experience and support tooling more complex.

## Exercise 5: Data Partitioning And Hot Keys

Partitioning only by course can create a hot partition during exam week. Partitioning by learner, attempt ID, or a composite key may spread writes better. Instructor aggregates can be built asynchronously as read models because slight staleness is acceptable.

Learners may need read-your-writes behavior for their own attempts, so their recent attempt view should not depend only on a lagging aggregate.

Caching can help instructor dashboards, but it may mislead if freshness expectations are unclear. Hot-key mitigation could include sharding aggregate updates, batching, or splitting extremely active courses.

## Exercise 6: Multi-Region Migration Strategy

Before choosing multi-region, clarify business drivers: regional latency, disaster recovery, data residency, availability targets, and customer contracts. Also clarify which data needs strong consistency and which can be eventually consistent.

An active-passive design may be enough for disaster recovery readiness. Active-active can improve regional latency and availability but increases conflict resolution, routing, observability, testing, and cost.

A reasonable migration path is to improve observability, define recovery objectives, make configuration region-aware, test restore procedures, then add replication and controlled failover before considering active-active writes.

An ADR should record context, decision, alternatives, consequences, cost, and revisit triggers.
