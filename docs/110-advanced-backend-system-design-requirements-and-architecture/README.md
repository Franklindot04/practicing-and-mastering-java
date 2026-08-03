# Advanced Backend System Design Requirements And Architecture

Advanced backend system design starts with requirements, constraints, and evidence. Architecture diagrams are useful only when they make decisions reviewable: what the system must do, what it must not do, which trade-offs were accepted, and how the team will know when an assumption was wrong.

This section is Java-oriented and architecture-first. The examples refer to backend services, APIs, workers, databases, caches, queues, and observability systems, but the default learning work does not require Docker, cloud accounts, Kubernetes, databases, or external APIs.

## Recommended Reading Order

1. [Requirements Constraints And System Goals](requirements-constraints-and-system-goals.md)
2. [Capacity Estimation And Workload Modelling](capacity-estimation-and-workload-modelling.md)
3. [Architecture Styles And Service Boundaries](architecture-styles-and-service-boundaries.md)
4. [API Communication And Evolution](api-communication-and-evolution.md)
5. [Architecture Diagrams And Decision Records](architecture-diagrams-and-decision-records.md)

## Learning Goals

After this section, you should be able to:

- separate functional requirements, non-functional requirements, constraints, assumptions, and out-of-scope work
- estimate capacity with explicit uncertainty instead of pretending early numbers are exact
- choose architecture styles and service boundaries from product, team, data, and operational needs
- compare synchronous and asynchronous communication patterns without treating either as universally better
- document decisions with diagrams, ADRs, risks, alternatives, validation plans, and measurable success criteria
- explain why simulated Java checks can validate design rules but cannot prove production readiness
