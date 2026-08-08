# Distributed Task and Workflow Platform Capstone

This capstone models deterministic task scheduling, workflow execution, leases, retries, dependency graphs, compensation, partition routing, admission control, dead-letter handling, replay, and operational visibility.

It is a local Java simulation, not a real distributed scheduler. Default tests do not require a queue, database, Docker, cluster, cloud account, or network.

```bash
mvn test
```

## Documentation

- [Requirements](docs/requirements.md)
- [Workflow state model](docs/workflow-state-model.md)
- [Scheduling model](docs/scheduling-model.md)
- [Lease and heartbeat model](docs/lease-and-heartbeat-model.md)
- [Consistency decisions](docs/consistency-decisions.md)
- [Idempotency design](docs/idempotency-design.md)
- [Partition strategy](docs/partition-strategy.md)
- [Retry policy](docs/retry-policy.md)
- [Dead-letter policy](docs/dead-letter-policy.md)
- [Failure scenarios](docs/failure-scenarios.md)
- [Security assumptions](docs/security-assumptions.md)
- [Testing strategy](docs/testing-strategy.md)
- [Performance considerations](docs/performance-considerations.md)
- [Observability](docs/observability.md)
- [Runbooks](docs/runbooks/README.md)
- [Limitations and production comparison](docs/limitations-and-production-comparison.md)
