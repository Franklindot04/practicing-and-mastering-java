# Deployment Assumptions

The module is not deployed. A production design would replace in-memory maps with durable databases, brokered messaging, managed secrets, authenticated APIs, observability pipelines, rollback plans, and tested operational runbooks.

## Assumptions

- The capstone is executed locally with Maven.
- Runtime configuration is code-level test fixture configuration.
- All storage, messaging, payment, and search boundaries are in-memory simulations.
- Diagnostic and operational reports are local objects, not exported telemetry.

## Deployment Gaps

Before deployment, the system would need API transport, environment configuration, secrets, database migrations, durable outbox storage, broker provisioning, payment-provider integration, search indexing, service identity, dashboards, alert rules, backup and restore plans, rollback strategy, and load testing.

## Operational Implications

Because none of those deployment controls exist here, release preparation should describe the capstone as a portfolio simulation. The useful evidence is design clarity and deterministic scenario coverage, not production operation.
