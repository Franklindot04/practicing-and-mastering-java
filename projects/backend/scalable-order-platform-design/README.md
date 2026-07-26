# Scalable Order Platform Design

This project is a planning-focused case study for designing a scalable order platform. It is not a full production application.

The goal is to practice requirements, boundaries, read/write paths, failure handling, capacity thinking, migration strategy, and architecture decision records using lightweight Java skeletons.

## Contents

- [Architecture Overview](docs/architecture-overview.md)
- [Requirements And Assumptions](docs/requirements-and-assumptions.md)
- [Capacity Scaling And Cost Notes](docs/capacity-scaling-cost.md)
- [Failure Security And Observability](docs/failure-security-observability.md)
- [Migration Path And ADRs](docs/migration-path-and-adrs.md)
- [ASCII System Diagram](diagrams/order-platform-flow.md)

## Build And Test

```bash
mvn test
```

## Explicit Limitations

- No real payment integration.
- No database configuration.
- No broker connection.
- No credentials.
- No Docker Compose.
- No Kubernetes manifests.
- No Terraform or cloud-provider resources.
- No production deployment files.

The Java code models boundaries and infrastructure-free behavior only. Future adapters would live behind the provided interfaces.
