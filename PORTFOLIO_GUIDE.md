# Portfolio Guide

Use this guide to present the repository as a staged Java learning and engineering portfolio. The strongest presentation is honest: it shows progression, design reasoning, tests, operational awareness, and clear limitations.

## How To Present The Repository

Start with the incremental stage history: beginner Java, OOP, intermediate Java, advanced Java, backend APIs, persistence, security, deployment, observability, reliability, distributed systems, messaging, system design, testing, performance, and final capstones.

Emphasize that the repository is a learning resource and portfolio, not a deployed service collection.

## Projects To Highlight

- [secured task API](projects/backend/secured-task-api/README.md)
- [deployment-ready task API](projects/backend/deployment-ready-task-api/README.md)
- [reliable service simulator](projects/reliability/reliable-service-simulator/README.md)
- [distributed cluster simulator](projects/distributed-systems/distributed-cluster-simulator/README.md)
- [order event pipeline](projects/messaging-and-streaming/order-event-pipeline/README.md)
- [scalable commerce platform](projects/backend-system-design/scalable-commerce-platform/README.md)
- [Java quality performance lab](projects/testing-performance/java-quality-performance-lab/README.md)

## Capstones To Highlight

- [Commerce Platform Capstone](projects/capstones/commerce-platform/README.md): checkout saga, idempotency, inventory reservation, outbox simulation, projection lag, reconciliation, and observability.
- [Distributed Task and Workflow Platform Capstone](projects/capstones/distributed-workflow-platform/README.md): leases, retries, dependency graphs, compensation, partition pressure, admission control, and replay.
- [JVM Diagnostics and Reliability Workbench](projects/capstones/jvm-diagnostics-workbench/README.md): bounded diagnostic workloads, latency distributions, regression budgets, and safe profiling practice.

## Explaining Architecture Decisions

For each project, explain:

1. Problem and constraints.
2. Boundary choices.
3. Data ownership.
4. API or event contract.
5. Consistency decision.
6. Failure mode.
7. Test evidence.
8. Operational limitation.

## Describing Simulations Honestly

Good wording:

- "This project simulates inventory reservation and checkout compensation with deterministic tests."
- "The workflow capstone models lease expiration and replay behavior locally."
- "The diagnostics workbench provides bounded workloads for practicing evidence collection."

Avoid wording that implies real deployment, real durable distributed infrastructure, real traffic evidence, or completed release publication when those have not happened.

## Discussing Tests And Performance Evidence

Say what tests prove: local behavior under modeled assumptions. Say what they do not prove: production capacity, security posture, or long-running reliability.

For performance, describe environment, input size, metric, threshold, and limitation. Avoid comparing numbers without methodology.

## Interview Walkthrough Order

1. Repository progression in one minute.
2. One backend API project.
3. One reliability or distributed-systems project.
4. One capstone success path.
5. One capstone failure path.
6. Test and operational evidence.
7. Known limitations and next maintenance ideas.

## Suggested Diagrams Or Screenshots To Prepare Later

- Capstone boundary diagram.
- Checkout saga flow.
- Workflow lease and retry state diagram.
- Diagnostics evidence table.
- Repository learning path overview.

## Suggested CV Wording

- "Built a staged Java learning repository covering core Java, backend APIs, persistence, security, deployment readiness, observability, distributed systems, messaging, system design, testing, performance, and capstone simulations."
- "Implemented deterministic Java capstones for commerce checkout, workflow leasing, and JVM diagnostics with unit tests and review documentation."

## Suggested GitHub Profile Wording

"This repository documents my progression through Java fundamentals, backend engineering, reliability, distributed systems, testing, performance, and final capstone simulations. The projects emphasize clear trade-offs, deterministic tests, and honest documentation of limitations."

## Claims To Avoid

- Do not say simulations are deployed services.
- Do not say tests prove deployment readiness.
- Do not say release preparation means a tag or GitHub release exists.
- Do not claim real durable distributed infrastructure unless it is actually implemented and evidenced.
