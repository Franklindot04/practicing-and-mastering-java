# Cost Capacity And Build Versus Buy

Cost is part of architecture. A technically elegant design that a team cannot afford, operate, or staff may be the wrong design.

## Cost Dimensions

Include:

- compute
- storage
- network transfer
- cross-region replication
- managed-service premiums
- logs, metrics, and traces
- backups and archives
- test and staging environments
- operational labour
- incident response
- engineering complexity
- migration and deprecation work

Overprovisioning buys safety at a price. Autoscaling can reduce waste but depends on good signals, warm-up time, scaling limits, and dependency capacity. Reserved-capacity concepts can reduce predictable spend while reducing flexibility.

## Cost Observability

Cost allocation should map spending to products, tenants, teams, or workflows where practical. Track unit economics such as cost per order, cost per search, cost per event, or cost per GiB retained.

Operational telemetry has a cost too. Logs, high-cardinality metrics, and traces are valuable, but unlimited collection can become expensive and noisy.

## Build Versus Buy

Buying a managed service can reduce undifferentiated operations, accelerate delivery, and improve reliability through provider expertise. It can also introduce vendor lock-in, pricing surprises, portability limits, regional constraints, and provider-specific failure modes.

Building can improve control, domain fit, and portability. It also creates staffing, maintenance, security, on-call, upgrade, and incident obligations.

Evaluate:

- strategic importance
- required customization
- operational maturity
- compliance needs
- reversibility
- data export
- migration cost
- lock-in tolerance
- support model

## Reversibility

Some decisions are easy to reverse; others harden through data, contracts, client adoption, and operational tooling. ADRs should state reversibility honestly. A queue provider choice may be reversible behind a narrow port. A globally distributed active-active data model may be expensive to unwind.
