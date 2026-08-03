# Release Notes

Release version: pending final selection.

These notes prepare the repository for a later final release action. No tag or GitHub release has been created.

## Release Overview

The repository is organized as a staged Java mastery curriculum moving from beginner Java to advanced backend engineering, operations, distributed systems, system design, testing, performance, and final capstone review.

Stage 30 prepares final capstones, review material, exercises, solutions, assessment, learning index, portfolio guide, changelog updates, and a completion report.

## Curriculum Scope

- Java fundamentals, OOP, collections, generics, exceptions, files, lambdas, streams, and date/time.
- Testing, debugging, Maven, concurrency, JVM memory, design patterns, clean code, JDBC, and networking.
- Backend APIs, persistence, security, configuration, Docker, CI/CD, deployment, rollback, cloud readiness, Kubernetes, IaC, and service mesh foundations.
- Observability, reliability, distributed systems, event-driven architecture, messaging, streaming, system design, testing strategy, performance, profiling, and JVM tuning.
- Stage 30 capstone simulations and final assessment.

## Supported Java Version

Measured local environment during release preparation:

- Java: 17.0.18
- Maven: 3.9.12

Individual modules may document their own Maven compiler release settings.

## Build And Test Instructions

Run root tests:

```bash
mvn test
```

Run standalone modules from their own directories:

```bash
cd projects/capstones/commerce-platform && mvn test
cd projects/capstones/distributed-workflow-platform && mvn test
cd projects/capstones/jvm-diagnostics-workbench && mvn test
```

Stage 30 capstone branches use default tests that require no database, broker, Docker, cloud account, or network.

## Major Projects

The repository includes beginner, intermediate, advanced, backend, reliability, observability, distributed-systems, messaging, system-design, testing, and performance projects.

## Capstones

- Commerce Platform Capstone.
- Distributed Task and Workflow Platform Capstone.
- JVM Diagnostics and Reliability Workbench.

These are deterministic learning simulations. They should not be described as deployed systems or durable distributed infrastructure.

## Repository Navigation

Final navigation is prepared through Stage 30 integration work and should link the learning index, portfolio guide, capstones, release notes, changelog, and completion report after the relevant branches are merged.

## Known Limitations

- No release tag has been created.
- No GitHub release has been created.
- Stage 30 branches are intended to be merged before final release publication.
- Capstones simulate infrastructure boundaries locally.
- Local tests do not prove production capacity, security posture, or operational maturity.

## Optional Benchmark And Profiling Instructions

Benchmarking and profiling practice should be opt-in, bounded, and cleaned before commit. Do not commit profiler recordings, heap dumps, JFR files, GC logs, or benchmark outputs.

## Future Maintenance Guidance

Future work should be treated as maintenance unless a new roadmap is explicitly authorized. Examples include dependency updates, link audits, optional diagrams, test refinements, and documentation corrections.

## Release And Tag Status

- Proposed release version: pending final selection.
- Tag created: no.
- GitHub release created: no.
- Release publication requires explicit authorization after Stage 30 merge and audit completion.
