# Commerce Platform Capstone

This capstone is a deterministic Java commerce backend reference system. It combines catalogue, pricing, inventory, cart, checkout, payment, order lifecycle, shipment, notification, identity boundaries, search projection, messaging, observability, and operational review in a single standalone Maven module.

The project is intentionally an in-memory simulation. It is useful for learning architecture and failure handling, but it is not a deployed commerce platform and does not provide durable infrastructure evidence.

## Run

```bash
mvn test
```

Default tests use no database, broker, Docker, cloud service, or network.

## Package Boundaries

- `dev.franklindot04.learnjava.capstone.commerce` contains the public simulation API, typed contracts, event contracts, reports, and deterministic tests.
- Persistence, messaging, payment, inventory, search, and observability are represented as explicit in-memory boundaries.
- Shared-kernel concepts are limited to small records such as `Product`, `CartLine`, `Event`, and report records.

## Scenario Coverage

The tests cover successful checkout, unavailable inventory, contention, duplicate checkout and payment requests, payment rejection and timeout, compensation, compensation failure, stale cache, projection lag, redelivery, poison messages, reconciliation, degraded optional dependency, load shedding, schema compatibility, incompatible schema rejection, and restart-style replay.

## Documentation

- [Functional requirements](docs/functional-requirements.md)
- [Non-functional requirements](docs/non-functional-requirements.md)
- [Architecture overview](docs/architecture-overview.md)
- [Module boundaries](docs/module-boundaries.md)
- [Data ownership](docs/data-ownership.md)
- [API contracts](docs/api-contracts.md)
- [Event contracts](docs/event-contracts.md)
- [Consistency model](docs/consistency-model.md)
- [Security boundaries](docs/security-boundaries.md)
- [Failure model](docs/failure-model.md)
- [Testing strategy](docs/testing-strategy.md)
- [Performance budgets](docs/performance-budgets.md)
- [Deployment assumptions](docs/deployment-assumptions.md)
- [Observability plan](docs/observability-plan.md)
- [Limitations and production comparison](docs/limitations-and-production-comparison.md)
- [Architecture decision records](docs/adr/README.md)
- [Runbooks](docs/runbooks/README.md)
