# Requirements Constraints And System Goals

Backend system design begins by making ambiguity visible. A design that jumps straight to services, databases, queues, and caches often hides the most important questions: who uses the system, what must be correct, what can degrade, what constraints are fixed, and what evidence will decide whether the architecture is working.

## Requirement Categories

Functional requirements describe user-visible behavior. For a commerce backend, examples include searching products, adding items to a cart, reserving inventory, authorizing payment, creating orders, sending notifications, and letting administrators update catalogue data.

Non-functional requirements describe quality attributes. Common categories include latency, throughput, availability, durability, consistency, retention, privacy, compliance, operability, security, cost, and recoverability. These requirements should be measurable where possible.

Constraints are limits the design must respect. Examples include a mandated Java version, existing identity provider, data residency rule, team size, budget, migration deadline, vendor contract, legacy schema, or legal retention policy.

Assumptions are beliefs that may be wrong. Examples include expected launch traffic, read/write ratio, acceptable stale-search window, payment provider reliability, cache hit rate, region availability, and customer growth. Good assumptions have owners and validation plans.

Scope states what the design covers. Out-of-scope statements are equally important because they prevent accidental commitments. A learning simulation can model checkout idempotency, but it is not a production payment integration.

## Success And Failure Criteria

Success criteria should include product and operational evidence:

- users can complete core workflows at the target latency under expected peak load
- important invariants are preserved, such as never selling more inventory than is available
- critical data has documented durability, backup, and restore expectations
- service owners have dashboards, alerts, runbooks, and rollback plans
- costs stay inside an agreed range for expected traffic
- security boundaries are reviewed before sensitive data crosses them

Failure criteria help teams reject a design before damage spreads:

- a required invariant depends on best-effort asynchronous processing only
- an outage of one optional dependency blocks the entire user journey
- a regional failover plan has no RPO, RTO, data-residency, or split-brain analysis
- scaling depends on a shared database with no ownership, migration, or query-isolation rules
- queues are added without retry budgets, poison-message handling, lag alerts, or replay controls
- a cache can return stale values for correctness-critical decisions without safeguards

## SLOs And Trade-Offs

Service level objectives turn goals into reviewable targets. Example SLOs:

- 99.9 percent of checkout attempts that reach payment decision complete within 2 seconds, excluding payment-provider redirect time
- 99.99 percent of accepted orders remain durable after acknowledgement
- inventory reservation oversell count is zero for committed stock
- search index freshness is less than 60 seconds for 99 percent of catalogue updates
- notification delivery starts within 5 minutes for 99 percent of confirmed orders

Every SLO implies trade-offs. A lower latency target may require caching, precomputed views, smaller synchronous fan-out, or more expensive capacity. A stronger consistency target may reject designs that rely only on eventual reconciliation. A lower cost target may accept slower back-office analytics while protecting the customer checkout path.

## Security And Privacy From The Start

Security requirements are not an appendix. Identify trust boundaries early:

- browser, mobile app, API gateway, backend service, worker, database, cache, queue, search index, third-party provider, and operator console
- authentication and authorization requirements at each boundary
- tenant isolation needs
- data classification for secrets, credentials, payment tokens, addresses, audit logs, and behavioural data
- encryption in transit and at rest
- audit log retention and access rules
- privacy by design, including minimization, deletion, and data residency

## Requirement Review Checklist

- What are the primary user workflows?
- Which invariants must never be violated?
- Which dependencies are critical and which can degrade?
- What latency, throughput, availability, durability, and consistency goals matter?
- What retention, compliance, privacy, and data-sovereignty rules apply?
- What cost range is acceptable at average and peak usage?
- Who owns each service, dataset, alert, runbook, and decision?
- What assumptions require measurement after launch?
- What is explicitly out of scope?
- What evidence would make the team redesign?
