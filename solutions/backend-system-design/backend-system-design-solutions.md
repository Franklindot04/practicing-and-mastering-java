# Backend System Design Solutions

## 1. Clarify Ambiguous Commerce Requirements

Assumptions: the first release supports customer checkout, order history, catalogue administration, and basic support workflows. Clarifying questions should cover users, payment providers, inventory source of truth, currencies, tax, privacy, data residency, launch traffic, order durability, refunds, fraud, support access, and operational ownership.

Functional requirements include product discovery, cart update, checkout submission, payment authorization, inventory reservation, order confirmation, notification, administration, and customer order lookup. Non-functional requirements include latency, availability, durability, inventory consistency, auditability, privacy, cost, and recovery. Out of scope could include marketplace sellers, advanced recommendations, and warehouse optimization.

Trade-off: delaying architecture selection feels slower, but it prevents designing for imaginary scale or missing correctness requirements.

## 2. Estimate Capacity Storage And Bandwidth

50 million reads per day is about 579 average read RPS. 200,000 writes per day is about 2.3 average write RPS. With an 8x peak multiplier, reads peak near 4,632 RPS and writes near 18.5 RPS. The read/write ratio is 250:1.

Peak read bandwidth is roughly `4,632 * 12 KiB`, or about 54 MiB/s before compression and cache/CDN effects. Daily write payload growth is about `200,000 * 2 KiB`, or about 390 MiB/day before indexes, metadata, replicas, and logs. At 250 ms average latency, peak read concurrency is about 1,158 in-flight reads; at 1 second, about 4,632.

Assumptions need validation with real payloads, traffic shape, cache hit rate, and retention. A high cache hit rate may reduce backend reads while network transfer remains a cost at the edge.

## 3. Identify Bottlenecks And Headroom

Catalogue, pricing, inventory, payment, and order confirmation are candidates for the critical path. Fraud may be critical depending on risk policy. Shipment and notification are usually asynchronous after order confirmation. Calling every dependency synchronously increases latency and availability risk.

A safer path sets a checkout deadline, isolates connection pools, uses idempotency for retried writes, keeps inventory and payment decisions explicit, emits outbox events for shipment and notification, and degrades optional recommendations or notification previews. Metrics should include dependency latency, timeout rate, checkout success, pool saturation, retry volume, and outbox lag.

## 4. Choose Modular Monolith Or Microservices

Given six engineers, uncertain boundaries, and strict inventory correctness, a modular monolith with hexagonal boundaries is a strong default. Modules can include catalogue, pricing, inventory, cart, checkout, payment adapter, orders, notifications, administration, identity, and search projection.

Microservices may become useful when teams, deployment cadence, scaling, and data ownership diverge. Starting with services too early can create distributed transactions, shared data, and operational load. The modular design should still enforce public ports, hidden internals, events, and dependency checks so later extraction is possible.

## 5. Detect Distributed Monolith Risks

Symptoms include lockstep deploys, shared database writes, synchronous chains, and shared libraries carrying business rules. The migration should first define data owners, stop direct cross-service table writes, move business rules behind service or module APIs, add contract tests, and shorten the synchronous checkout path.

A phased plan can consolidate tightly coupled services back into modules where needed, introduce an outbox for independent reactions, and extract one stable boundary at a time. Rollback requires routing controls and compatibility windows.

## 6. Select Communication Style And API Contracts

REST fits customer-facing product, cart, checkout, and order-status APIs. Events fit `OrderConfirmed`, `PaymentAuthorized`, `ShipmentRequested`, and `NotificationRequested`. GraphQL or backend-for-frontend may fit mobile aggregation, but query complexity and authorization must be controlled. gRPC concepts may fit internal low-latency typed service calls when the team can operate them.

Checkout needs idempotency keys, deadlines, safe retry rules, rate limits, conflict errors, validation errors, and correlation IDs. Event contracts need IDs, type, version, timestamps, producer, correlation, causation, and tolerant-reader compatibility.

## 7. Choose Data Stores And Ownership

Orders, payments, inventory, and audit metadata usually fit relational storage because transactions, constraints, and auditability matter. Catalogue may be relational or document-oriented depending on update shape. Search belongs in a search index as derived data. Product images fit object storage. Metrics fit time-series storage. Recommendations may use graph or analytical stores depending on queries.

Each dataset needs an owner, backup and restore plan, retention policy, deletion flow, schema evolution strategy, and data-residency review. Derived stores must be rebuildable from source records or retained events.

## 8. Design Partition Keys And Hotspot Mitigation

Tenant ID can distribute ordinary tenant-scoped data, but large tenants can become hot. Product ID can preserve inventory locality but flash sales can overload one key. Order ID may distribute order history, while customer ID supports customer order queries.

Use per-partition throughput, latency, error, and queue-lag metrics. Mitigations include splitting large tenants, salting hot read keys, queueing non-critical work, request coalescing, reservation limits, and dedicated capacity. Rebalancing needs throttling and stop conditions.

## 9. Define Consistency Transactions And Sagas

Inventory cannot go negative, payment side effects must be idempotent, and confirmed orders must be durable. A local transaction should protect inventory reservation if inventory is local. Payment authorization is external and uncertain, so checkout needs idempotency and a pending or compensated state.

Saga states can include started, inventory reserved, payment authorized, order confirmed, shipment requested, completed, compensated, and needs reconciliation. Payment decline releases inventory. Shipment failure should not erase the order; it should create an operational repair path. Outbox and inbox records protect event publication and duplicate consumption.

## 10. Design Cache Search Projection And Feed Strategy

Catalogue detail reads can use cache-aside or HTTP/CDN caching with tenant-safe keys, expiry, invalidation, negative caching, and stampede prevention. Inventory availability should not rely on stale cache for correctness.

Search is a derived projection with a freshness SLO, lag metrics, reindexing, backfill controls, and rebuild procedure. Personalized feeds may use fan-out on write for active users and fan-out on read for long-tail users. Cursor pagination and duplicate suppression protect changing result sets.

## 11. Plan Multi-Region Failover And Disaster Recovery

Active-active checkout is risky when inventory and payment correctness require strongly coordinated writes. A safer first design may be single-primary active-passive with tested backups, warm standby capacity, and explicit failover criteria.

Failover should be rejected when replica lag exceeds RPO, secondary capacity cannot absorb traffic, data residency is violated, identity or payment dependencies are unavailable, or split-brain risk is unresolved. Tests should include restore drills, traffic-routing exercises, regional dependency review, and failback validation.

## 12. Define Security Trust Boundaries And Tenant Isolation

Actors include customers, administrators, support agents, partners, services, workers, and operators. Trust boundaries exist at clients, gateway, services, queues, databases, caches, search, logs, exports, and admin tools.

Controls include authentication, authorization, service identity, least privilege, tenant-scoped queries and cache keys, secret storage, TLS, encryption at rest, key rotation, input validation, audit logs, token redaction, network segmentation, dependency review, abuse prevention, fraud signals, and incident response. Sensitive data should be minimized in logs, traces, events, and dead letters.

## 13. Define SLOs Observability And Operational Readiness

Example SLOs: checkout availability, checkout latency, order durability, payment uncertainty rate, search freshness, notification start delay, and dead-letter response time. SLIs should map to dashboards and alerts with owners.

Readiness checks should protect traffic from unready instances. Liveness should detect stuck processes without restarting for dependency blips. Runbooks should cover triage, mitigation, rollback, replay, and post-incident learning. Canaries and feature flags reduce rollout risk but require cleanup and monitoring.

## 14. Plan Deployment Rollout And Architectural Evolution

Roll out checkout v2 through compatible event additions, consumers that tolerate old and new fields, producer changes after consumers are ready, and expand-contract database changes. Extract payment only after a stable port, idempotency records, contract tests, dashboards, and rollback plan exist.

Stop conditions include rising payment uncertainty, contract test failures, migration mismatch, increased checkout latency, or reconciliation drift. Rollback may be unsafe after new external side effects, so roll-forward plans are needed.

## 15. Estimate Cost And Build Versus Buy

Managed database, search, broker, and payment provider can reduce operations but add premiums, lock-in, and provider-specific failure modes. Building internally increases control but requires staffing, upgrades, security response, on-call, and incident expertise.

Cost drivers include compute, storage, indexes, replicas, cross-region transfer, logs, metrics, traces, backups, staging, overprovisioning, and engineering labour. Decisions should state reversibility, migration cost, export options, and review dates.

## 16. Write An ADR And Fitness Tests

ADR: Accepted. Context: checkout correctness matters, boundaries are still evolving, and one team owns the product. Decision: use a modular monolith for checkout with event-driven projections for search and notifications. Alternatives: early microservices, shared database services, fully asynchronous checkout. Consequences: simpler transactions and deployment, but less independent scaling. Review after traffic, team, or ownership changes.

Fitness tests: reject imports from module internals, require public ports for cross-module calls, verify event contract compatibility, reject unbounded retries for non-idempotent writes, require authorization metadata on admin commands, require SLO and runbook metadata for checkout, and flag unowned dead-letter destinations.

## 17. Perform Operational Readiness Review

A readiness decision might be conditional launch. Blockers include missing payment idempotency evidence, no restore test, no checkout SLO dashboard, no dead-letter owner, or unresolved tenant isolation. Accepted risks might include search freshness tuning or recommendation degradation.

Owners should be assigned for checkout, payment, inventory, orders, notifications, search, security, data retention, cost, and incident command. Follow-up work should include performance testing, profiling, load tests, and JVM tuning in the later quality and performance stage rather than hiding those tasks inside architecture notes.
