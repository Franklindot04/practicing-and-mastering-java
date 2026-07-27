# Service Architecture Patterns

Service architecture patterns help teams decide where code, data, APIs, and operational ownership belong. The right pattern depends on requirements, team shape, data ownership, failure tolerance, and cost.

This section does not treat microservices as an automatic upgrade. A well-structured modular monolith can be a better design than a distributed system that the team cannot operate.

## Topics

- [Layered Hexagonal And Clean Architecture](layered-hexagonal-clean.md)
- [Modular Monoliths And Microservices](modular-monoliths-and-microservices.md)
- [Boundaries Communication And Discovery](boundaries-communication-discovery.md)
- [Distributed Workflows And Migration Patterns](distributed-workflows-and-migrations.md)
- [Comparisons Failure Modes And Reviews](comparisons-failure-modes-reviews.md)

## Learning Goals

After this section, you should be able to:

- Compare layered, hexagonal, clean architecture, modular monoliths, and microservices.
- Explain service boundaries, bounded contexts, and domain ownership.
- Discuss shared libraries, shared databases, and database-per-service tradeoffs.
- Choose synchronous or asynchronous communication based on workflow needs.
- Explain API gateways, backend-for-frontend, and service discovery concepts.
- Describe orchestration, choreography, sagas, anti-corruption layers, and strangler migration.
- Identify when microservices are unnecessary.
