# Architecture Decisions Risks And Fitness Functions

Architecture decisions should survive code review, incidents, growth, and team changes. ADRs and fitness functions keep the reasoning visible.

## ADR Fields

A practical ADR includes:

- title
- status
- context
- decision
- alternatives
- consequences
- risks
- assumptions
- validation criteria
- reversibility
- owner
- review date

Decision status can be proposed, accepted, superseded, deprecated, or rejected. Review dates keep assumptions from becoming folklore.

## Alternatives And Consequences

Compare alternatives without claiming one universal architecture. For example:

- modular monolith preserves local transactions and simple deployment, but may limit independent scaling
- microservices improve independent ownership when boundaries are stable, but add distributed failure modes
- synchronous checkout gives immediate feedback, but fan-out can reduce availability
- asynchronous projection improves read performance, but users may see stale data
- multi-region failover can reduce regional outage impact, but replication lag and split brain may threaten correctness

## Fitness Functions

Fitness functions are repeatable checks that protect architecture rules:

- dependency checks reject imports from hidden module internals
- interface-bypass checks ensure callers use public ports
- contract checks reject incompatible API or event changes
- retry-policy checks reject unbounded retries and unsafe retries on non-idempotent writes
- security-boundary checks require authorization before sensitive commands
- reliability checks require SLOs, dashboards, and runbook links for critical workflows
- cost checks flag unowned high-cardinality telemetry or unbounded retention

Java projects can express some checks as unit tests against packages, typed models, or configuration objects. These tests validate design constraints in the repository. They do not prove that production traffic, infrastructure, credentials, networks, and operators will behave correctly.

## Risk Register

Architecture risk entries should include description, likelihood, impact, owner, mitigation, detection, and review date. Open questions are not failures; they are honest markers for future evidence.

## Review Questions

- What decision is being made?
- What alternatives were seriously considered?
- Which assumptions can be measured soon?
- Which risks are accepted?
- Which risks need mitigation before release?
- What would make this decision wrong?
- How will the team detect drift?
