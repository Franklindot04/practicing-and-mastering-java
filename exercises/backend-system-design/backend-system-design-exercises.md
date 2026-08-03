# Backend System Design Exercises

## 1. Clarify Ambiguous Commerce Requirements

Objective: Turn an ambiguous product idea into functional requirements, non-functional requirements, constraints, assumptions, and out-of-scope statements.

Scenario: A team says, "Build a scalable checkout system for an online store."

Requirements: Identify user workflows, administrative workflows, correctness invariants, privacy requirements, availability goals, latency goals, and operational ownership.

Constraints: Do not choose services, databases, queues, or caches until the requirements are explicit.

Expected reasoning: Separate known facts from assumptions and list questions for product, legal, security, operations, and finance stakeholders.

Acceptance criteria: The answer includes at least eight clarifying questions, five functional requirements, five non-functional requirements, and three out-of-scope statements.

Optional extension: Add success and failure criteria for launch.

## 2. Estimate Capacity Storage And Bandwidth

Objective: Estimate requests, concurrency, storage growth, and bandwidth.

Scenario: A catalogue service receives 50 million reads per day, 200,000 writes per day, 12 KiB average read response, 2 KiB average write payload, and a peak traffic multiplier of 8 over average.

Requirements: Calculate average RPS, peak RPS, read/write ratio, peak bandwidth, daily storage growth, and approximate concurrency at 250 ms and 1 second average latency.

Constraints: State uncertainty and do not present estimates as exact capacity commitments.

Expected reasoning: Show formulas and explain how cache hit rate, payload compression, and retention change the result.

Acceptance criteria: The answer includes calculations, assumptions, and a validation plan.

Optional extension: Add a low, expected, and high case.

## 3. Identify Bottlenecks And Headroom

Objective: Find bottlenecks in a request path.

Scenario: Checkout calls catalogue, pricing, inventory, payment, fraud, order, shipment, and notification synchronously.

Requirements: Identify synchronous fan-out risks, critical versus optional dependencies, connection-pool limits, retry amplification, and headroom needs.

Constraints: Do not solve every problem by adding a queue.

Expected reasoning: Explain latency budget allocation, degradation options, and which calls must remain in the critical path.

Acceptance criteria: The answer proposes a safer request path and names metrics that prove the bottleneck was reduced.

## 4. Choose Modular Monolith Or Microservices

Objective: Select an architecture style and service boundaries.

Scenario: Six engineers own a new commerce product with uncertain domain boundaries and strict inventory correctness.

Requirements: Compare layered architecture, hexagonal architecture, modular monolith, service-oriented design, microservices, and event-driven additions.

Constraints: Avoid claiming microservices are inherently better.

Expected reasoning: Discuss team ownership, deployability, local transactions, operational maturity, coupling, and future extraction.

Acceptance criteria: The answer chooses a default style, explains alternatives, and defines at least six module boundaries.

## 5. Detect Distributed Monolith Risks

Objective: Review a proposed microservice design for coupling.

Scenario: Twelve services must deploy together, share one database, call each other synchronously during checkout, and use a shared domain library for business rules.

Requirements: Identify distributed monolith symptoms and propose a migration toward clearer boundaries.

Constraints: Preserve user-facing behaviour during migration.

Expected reasoning: Discuss shared data, lockstep deploys, synchronous chains, ownership, and strangler or modularization steps.

Acceptance criteria: The answer includes a phased plan and rollback considerations.

## 6. Select Communication Style And API Contracts

Objective: Choose REST, RPC/gRPC concepts, GraphQL concepts, asynchronous messaging, API gateway, and backend-for-frontend patterns where appropriate.

Scenario: Web, mobile, internal workers, and analytics consumers need commerce data.

Requirements: Define APIs for product search, cart, checkout, order status, notification, and analytics.

Constraints: Include pagination, filtering, idempotency, timeouts, retries, rate limits, circuit breakers, bulkheads, correlation IDs, and error contracts.

Expected reasoning: Explain protocol selection and synchronous fan-out risks.

Acceptance criteria: The answer includes at least one synchronous API, one asynchronous event, and one compatibility rule.

## 7. Choose Data Stores And Ownership

Objective: Select data stores based on workload requirements.

Scenario: The platform needs catalogue management, search, inventory, orders, payments, audit logs, metrics, product images, and recommendations.

Requirements: Consider relational, document, key-value, wide-column, graph, search, time-series, and object storage options.

Constraints: Avoid simplistic SQL-versus-NoSQL rules.

Expected reasoning: Map each workload to source-of-truth or derived data ownership, schema evolution, backup, restore, retention, archival, deletion, and data sovereignty.

Acceptance criteria: The answer includes a data ownership table and migration notes.

## 8. Design Partition Keys And Hotspot Mitigation

Objective: Design partitioning and replication strategy.

Scenario: Tenants vary widely in size, and one flash sale can overload a single product's inventory record.

Requirements: Choose partition keys, detect hot partitions, estimate cross-partition query cost, and propose rebalancing.

Constraints: Preserve required ordering and avoid hiding query costs.

Expected reasoning: Compare hash partitioning, range partitioning, consistent hashing, secondary indexes, replication, and locality.

Acceptance criteria: The answer names metrics and an operational mitigation plan.

## 9. Define Consistency Transactions And Sagas

Objective: Decide transaction and workflow consistency.

Scenario: Checkout must reserve inventory, authorize payment, create an order, request shipment, and notify the customer.

Requirements: Identify invariants, local transactions, isolation needs, optimistic or pessimistic locking, distributed transaction trade-offs, saga steps, compensation, outbox, inbox, idempotency, pending states, reconciliation, audit trails, and state machines.

Constraints: Eventual consistency is not acceptable for every invariant.

Expected reasoning: Explain which decisions need strong consistency and which can be eventually consistent.

Acceptance criteria: The answer includes a state transition model and failure handling for payment decline and shipment failure.

## 10. Design Cache Search Projection And Feed Strategy

Objective: Design caches, search indexes, materialized views, feeds, and timelines.

Scenario: Product search must be fast, catalogue updates must appear soon, and customer home feeds should include personalized recommendations.

Requirements: Cover cache-aside, read-through, write-through, write-behind, local cache, distributed cache, HTTP caching, CDN concepts, cache keys, expiry, invalidation, stale values, stampede prevention, negative caching, warming, eviction, hot keys, search indexing, projections, fan-out on write, fan-out on read, backfills, reindexing, cursor pagination, duplicate suppression, and rebuilding derived state.

Constraints: Do not use cache freshness for correctness-critical inventory decisions.

Expected reasoning: Distinguish source-of-truth state from derived views and define freshness SLOs.

Acceptance criteria: The answer includes cache metrics, search lag handling, and rebuild procedure.

## 11. Plan Multi-Region Failover And Disaster Recovery

Objective: Evaluate regional architecture.

Scenario: Leadership asks for active-active multi-region checkout.

Requirements: Define regions, availability zones, active-passive, active-active, traffic routing, cross-region replication, replication lag, regional isolation, conflict resolution, global identifiers, data residency, RPO, RTO, failover, failback, regional evacuation, dependency locality, split brain, control plane versus data plane, and regional failure testing.

Constraints: Do not claim multi-region automatically improves reliability.

Expected reasoning: Reject unsafe failover when RPO, RTO, consistency, capacity, or data-residency assumptions fail.

Acceptance criteria: The answer includes a decision matrix and test plan.

## 12. Define Security Trust Boundaries And Tenant Isolation

Objective: Incorporate security from the start.

Scenario: The commerce backend supports customers, administrators, support agents, partner integrations, and internal services.

Requirements: Cover threat modelling, trust boundaries, authentication, authorization, service identities, least privilege, secrets, encryption in transit, encryption at rest, key rotation, tenant isolation, input validation, audit logs, data classification, token handling, network segmentation, zero-trust concepts, supply-chain security, abuse prevention, fraud signals, incident response, and privacy by design.

Constraints: Do not put secrets, tokens, or private data into logs or dead-letter payloads.

Expected reasoning: Map each actor and data class to controls and observability.

Acceptance criteria: The answer includes a trust-boundary diagram description and security review checklist.

## 13. Define SLOs Observability And Operational Readiness

Objective: Design reliability and operability requirements.

Scenario: The system is about to launch checkout and order history.

Requirements: Define SLIs, SLOs, error budgets, readiness, liveness, metrics, logs, traces, dashboards, alerts, dependency monitoring, saturation, queue depth, consumer lag, load shedding, graceful degradation, fallbacks, runbooks, feature flags, canaries, blue-green deployment, rollback, roll-forward, chaos tests, capacity tests, incident command, post-incident learning, and ownership.

Constraints: Alerts must be actionable and tied to user impact or operator action.

Expected reasoning: Explain what each owner sees during an incident and how recovery is validated.

Acceptance criteria: The answer includes at least five SLOs and a launch readiness checklist.

## 14. Plan Deployment Rollout And Architectural Evolution

Objective: Safely evolve APIs, events, schemas, and module boundaries.

Scenario: Checkout v2 changes order events and extracts payment into a separate service.

Requirements: Cover compatibility windows, backward-compatible rollouts, data migrations, expand-contract migrations, deprecation, strangler migrations, modular-monolith extraction, release sequencing, schema rollout, feature flags, rollback constraints, and architectural evolution.

Constraints: Existing customers and consumers must continue working during rollout.

Expected reasoning: Explain order of deploys, backfills, contract tests, and rollback limits.

Acceptance criteria: The answer includes a phased migration plan with stop conditions.

## 15. Estimate Cost And Build Versus Buy

Objective: Evaluate economic and operational trade-offs.

Scenario: The team can use managed search, managed message broker, managed database, and third-party payment provider, or build parts internally.

Requirements: Estimate compute, storage, network transfer, managed-service premiums, operational labour, engineering complexity, overprovisioning, autoscaling, reserved-capacity concepts, cost allocation, cost observability, build versus buy, vendor lock-in, portability, migration costs, technical debt, and reversibility.

Constraints: Include people and operational cost, not only infrastructure bills.

Expected reasoning: Compare at least three managed-service choices against internal build options.

Acceptance criteria: The answer includes cost drivers, risks, and review dates.

## 16. Write An ADR And Fitness Tests

Objective: Document and validate an architecture decision.

Scenario: The team chooses a modular monolith for checkout and event-driven projections for search and notifications.

Requirements: Write an ADR with status, context, decision, alternatives, consequences, risks, assumptions, reversibility, review date, and validation criteria. Define fitness tests for dependency rules, contract compatibility, reliability policy, retry safety, and security boundaries.

Constraints: Do not claim tests prove production readiness.

Expected reasoning: Connect architecture rules to Java package checks, contract examples, and operational policies.

Acceptance criteria: The answer includes an ADR and at least five deterministic fitness checks.

## 17. Perform Operational Readiness Review

Objective: Review the architecture before launch.

Scenario: Checkout, payment, order, notification, and search are feature-complete in code.

Requirements: Review requirements, capacity, bottlenecks, architecture style, boundaries, APIs, data stores, consistency, caching, partitioning, multi-region, security, SLOs, observability, deployment, cost, ADRs, monolith extraction path, and fitness tests.

Constraints: The review must identify missing evidence and should not block on perfection.

Expected reasoning: Separate launch blockers, accepted risks, follow-up work, and Stage 29-style validation that belongs later.

Acceptance criteria: The answer includes a readiness decision, risk register, and owner list.
