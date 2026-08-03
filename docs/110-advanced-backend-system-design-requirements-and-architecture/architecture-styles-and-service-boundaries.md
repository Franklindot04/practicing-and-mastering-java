# Architecture Styles And Service Boundaries

Architecture style is a response to requirements and constraints. Microservices, modular monoliths, event-driven systems, batch jobs, streaming pipelines, and serverless functions are tools, not maturity levels.

## Common Styles

Layered architecture separates presentation, application, domain, and infrastructure concerns. It is understandable and common in Java web applications, but weak boundaries can allow business rules to leak into controllers or persistence code.

Hexagonal architecture puts domain logic behind ports and adapters. It is useful when a Java application must isolate business rules from databases, brokers, HTTP clients, and frameworks.

Clean architecture also emphasizes dependency direction and use-case boundaries. It can improve testability, but excessive ceremony can slow small teams.

Modular monoliths keep one deployable unit while enforcing internal boundaries. This can be a strong default when one team owns a product, transactions are local, and operational simplicity matters.

Service-oriented architecture and microservices split capabilities into independently deployable services. They can help when teams, release cadence, scaling needs, and ownership boundaries differ. They also add network failures, data duplication, observability needs, compatibility windows, and incident complexity.

Event-driven architecture uses events to decouple producers and consumers. It works well for independent reactions, projection building, audit streams, and asynchronous workflows. It does not remove the need for idempotency, ordering analysis, retries, dead-letter handling, replay controls, and consistency decisions.

Batch and streaming systems process data outside the request path. Batch fits bounded periodic work. Streaming fits continuous event processing with ordering, windowing, and state concerns.

Serverless concepts can reduce infrastructure management for event-triggered work, but cold starts, execution limits, observability, vendor lock-in, and local testing constraints matter.

Hybrid designs are common. A product may use a modular monolith for core checkout, events for notifications and projections, a search index for catalogue queries, and batch jobs for reconciliation.

## Boundary Design

Good boundaries follow domain ownership and change patterns:

- product catalogue owns product facts and catalogue administration
- pricing owns price rules, promotions, and currency decisions
- inventory owns stock availability and reservation invariants
- checkout coordinates user intent, but should not directly own payment settlement details
- payment integration owns provider idempotency and uncertain outcomes
- order lifecycle owns confirmed order state
- notification owns delivery attempts and templates

Bounded contexts are not just package names. They include language, data ownership, APIs, events, operational metrics, access control, and team responsibility.

## Granularity Trade-Offs

Too few boundaries create large blast radius and unclear ownership. Too many boundaries create synchronous fan-out, distributed transactions, duplicate models, and operational overhead.

Ask:

- Does this capability change independently?
- Does it require independent scaling?
- Does it own data with distinct invariants?
- Is there a team ready to operate it?
- Can failures be isolated?
- Is the communication contract stable enough?
- Would splitting create a distributed monolith?

## Shared Data And Libraries

A shared database across services creates hidden coupling. It may be acceptable during migration, but it needs ownership rules, compatibility windows, and a plan to remove direct writes by non-owners.

Shared libraries can be useful for stable primitives, such as IDs, validation helpers, or observability helpers. They become risky when they smuggle domain logic across service boundaries or force lockstep deployments.

## Migration From Monoliths

A safe extraction path usually starts with clearer modules inside the monolith:

1. define module APIs
2. hide internals
3. separate tables or schemas by owner where practical
4. publish domain events through an outbox
5. add contract and dependency checks
6. extract only when ownership, deployment, data, and operations justify it

Strangler migrations route selected workflows to new components while old and new systems coexist. They need rollback plans, data synchronization rules, observability, and user-visible consistency decisions.

## When Not To Use Microservices

Avoid microservices when the team cannot operate distributed systems, business boundaries are unclear, local transactions are central to correctness, traffic does not require independent scaling, or deployment independence is not valuable. A well-structured Java modular monolith can be more reliable, cheaper, and easier to evolve than a collection of small services that must always deploy together.
