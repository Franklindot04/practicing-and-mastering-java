# Cloud Architecture Quiz Answer Key

## Cloud Architecture Basics Quiz

1. B
2. B
3. A
4. Application architecture focuses on code boundaries and behavior. Infrastructure architecture focuses on runtime, networking, data services, monitoring, scaling, and recovery.
5. Vendor-neutral examples help learners understand concepts before choosing provider-specific products, credentials, limits, and pricing.
6. One concern is that a single backend service instance or database may be a single point of failure.

## Traffic And Data Patterns Quiz

1. A
2. A
3. A
4. Cache invalidation is deciding when cached data should be removed or refreshed so stale data does not mislead users.
5. More backend instances may open more database connections or send more queries than the database can handle.
6. Benefit: slow work can happen outside the request path. Responsibility: retries, idempotency, backlog monitoring, and dead-letter handling.

## Reliability, Cost, And Operations Quiz

1. A
2. A
3. A
4. An SLO is an internal reliability target, an SLA is an external promise, and an error budget is the allowed unreliability before reliability work should take priority.
5. A runbook should include symptoms, dashboards/signals, mitigation steps, escalation path, recovery verification, and follow-up notes.
6. Ask what problem each component solves and whether the team can operate it.

