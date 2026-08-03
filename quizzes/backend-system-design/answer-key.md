# Backend System Design Answer Key

## MC1

B. Latency at peak load is a measurable quality attribute, not a user workflow.

## MC2

C. Microservices are useful when ownership, deployment, scaling, and operational maturity justify the extra distributed-system cost.

## MC3

B. Stale inventory can oversell or reject valid purchases. Correctness-critical decisions need source-of-truth checks or stronger controls.

## MC4

A. The outbox pattern stores publishable events with the local state change.

## MC5

B. RPO is the acceptable data loss window.

## MC6

A. Retrying an uncertain write is unsafe unless the operation is idempotent or protected by an idempotency key.

## MC7

A. Consumer lag and queue depth show backlog.

## MC8

D. Optional additive fields with tolerant readers are usually safer than removals, renames, or unit changes.

## MC9

B. ADRs capture context, decision, alternatives, consequences, risks, assumptions, and validation criteria.

## MC10

A. Labour, incident response, and engineering complexity are often hidden behind infrastructure-only estimates.

## SA1

Functional requirements describe behaviour. Non-functional requirements describe qualities such as latency, availability, durability, consistency, security, and cost. Constraints are fixed limits. Assumptions are uncertain beliefs that need validation.

## SA2

Average RPS is daily requests divided by 86,400. Peak RPS is average RPS multiplied by the peak multiplier. State uncertainty and validate with real traffic.

## SA3

Symptoms include lockstep deployments, shared database ownership, synchronous chains, shared domain libraries, unclear ownership, and services that cannot fail independently.

## SA4

A queue can buffer bursts, but consumers still need enough throughput, retry budgets, poison-message handling, lag alerts, idempotency, and downstream capacity.

## SA5

Relational databases are strong defaults when transactions, constraints, joins, indexes, auditability, and mature backup/restore practices matter.

## SA6

Local transactions protect one resource boundary. Distributed transactions coordinate multiple resources with extra failure modes. Sagas use local transactions plus compensation and reconciliation.

## SA7

Examples: stale values mitigated by expiry and invalidation; stampedes mitigated by request coalescing; penetration mitigated by negative caching; hot keys mitigated by sharding or coalescing; data leakage mitigated by tenant-safe keys.

## SA8

Derived views can lag, corrupt, or miss events. Rebuild plans need source retention, backfill controls, validation, duplicate suppression, and stop conditions.

## SA9

RPO is acceptable data loss. RTO is acceptable recovery time. Both must be tested, not merely written down.

## SA10

Useful signals include checkout success rate, latency, payment uncertainty, inventory reservation failures, dependency timeouts, outbox lag, dead-letter count, saturation, and error-budget burn.

## SC1

A modular monolith with enforced boundaries is a safer initial default. It preserves local transactions and simpler operations while boundaries are still forming. Events can support independent projections and notifications. Microservices can be extracted later when ownership and operations justify them.

## SC2

Keep catalogue as source of truth and build a search projection asynchronously. Define freshness SLO, projection lag metrics, backfill and reindex controls, cache invalidation, and a rebuild path from source records or retained events.

## SC3

Store idempotency key, request fingerprint, provider outcome, response, and expiry. On retry, return the stored result or a pending state. Do not send another payment side effect without idempotency protection.

## SC4

Failover is unsafe because five-minute lag exceeds the one-minute RPO. Active-active also needs conflict rules, capacity, data residency, and dependency locality checks.

## SC5

The refund endpoint needs strong admin authentication, authorization, least privilege, tenant isolation, approval or dual control if needed, audit logs, rate limits, idempotency, correlation IDs, alerts, and careful token and private-data handling.

## TA1

Modular monoliths reduce operational complexity and preserve local transactions, but limit independent scaling and deployment. Microservices improve independence when boundaries and teams are mature, but add network failure, contracts, observability, and data-ownership complexity.

## TA2

Synchronous calls give immediate confirmation but increase checkout latency and dependency failure impact. Asynchronous events decouple shipment and notification from checkout but require idempotency, retries, dead letters, and user-visible eventual progress.

## TA3

Cache-aside is explicit and safer for catalogue reads. Write-behind can reduce write latency but risks data loss or stale correctness, making it unsafe for inventory without strong durability and replay guarantees.

## TA4

Fan-out on write speeds reads but increases write cost and backfill complexity. Fan-out on read reduces write amplification but increases request latency and query cost.

## TA5

Managed search reduces operational labour and speeds delivery but adds provider cost, lock-in, and service-specific failure modes. Internal search increases control but requires expertise, upgrades, scaling, and on-call ownership.

## FA1

The partition key or workload has a hotspot. Mitigate with request coalescing, salting where ordering allows, dedicated capacity, splitting hot tenants/products, throttling, and product-level sale controls.

## FA2

This is a poison-message or malformed-contract failure. Use bounded retries, classify permanent failures, route to dead letter or quarantine with safe metadata, alert owners, and provide repair/replay tooling.

## FA3

Causes include missed source deletion event, stale cache, failed projection consumer, index backfill bug, missing tombstone handling, or CDN/HTTP cache lifetime. Fix ownership, invalidation, projection replay, and delete propagation.

## FA4

Notification is an optional dependency in the synchronous critical path. Move it behind an outbox/event consumer or degrade it with bounded timeout and clear metrics.

## FA5

Removing `customerId` broke backward compatibility. Safer rollout adds fields first, updates consumers, observes compatibility, and removes fields only after the old contract is retired.
