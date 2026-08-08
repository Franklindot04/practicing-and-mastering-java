# Learning Index

This index organizes the Java mastery repository by skill level, topic, repository artifact, operational competency, and recommended sequence. Links point to existing repository paths or Stage 30 paths prepared on focused branches.

## Getting Started

- Repository overview: [README.md](README.md)
- Learning path: [LEARNING_PATH.md](LEARNING_PATH.md)
- Progress tracker: [PROGRESS.md](PROGRESS.md)
- Contribution standards: [CONTRIBUTING.md](CONTRIBUTING.md)

## Beginner Java

- Docs: [Java basics](docs/01-java-basics/README.md), [object-oriented programming](docs/02-object-oriented-programming/README.md), [collections](docs/03-collections-framework/README.md)
- Examples: [beginner examples](examples/beginner/README.md)
- Exercises: [beginner exercises](exercises/beginner/README.md)
- Quizzes: [beginner quizzes](quizzes/beginner/README.md)

## Object-Oriented Programming

- Docs: [object-oriented programming](docs/02-object-oriented-programming/README.md)
- Professional examples: [clean code and SOLID](examples/professional/clean-code-solid/README.md), [design patterns](examples/professional/design-patterns/README.md)
- Quizzes: [professional quizzes](quizzes/professional/README.md)

## Intermediate Java

- Docs: [generics](docs/04-generics/README.md), [lambdas and streams](docs/07-lambdas-streams/README.md), [testing and debugging](docs/09-testing-debugging/README.md)
- Exercises: [intermediate exercises](exercises/intermediate/README.md)
- Quizzes: [intermediate quizzes](quizzes/intermediate/README.md)

## Advanced Java

- Examples: [advanced concurrency](examples/advanced/concurrency/README.md)
- Projects: [advanced projects](projects/advanced/README.md)
- Topics: concurrency, networking, JDBC, scheduling, and file processing.

## Backend Development

- Docs: [backend Java foundations](docs/19-backend-java-foundations/README.md), [HTTP and REST](docs/20-http-rest-api-basics/README.md), [Spring Boot introduction](docs/21-spring-boot-introduction/README.md)
- Projects: [simple REST API](projects/backend/simple-rest-api/README.md), [persistent task API](projects/backend/persistent-task-api/README.md), [secured task API](projects/backend/secured-task-api/README.md)
- Quizzes: [backend quizzes](quizzes/backend/README.md)

## Persistence

- Docs: [persistence foundations](docs/23-backend-persistence-foundations/README.md), [JPA and Spring Data](docs/24-jpa-hibernate-spring-data/README.md)
- Projects: [persistent task API](projects/backend/persistent-task-api/README.md)
- Competency: transactions, repositories, entity boundaries, and persistence trade-offs.

## Security

- Docs: [backend security foundations](docs/25-backend-security-foundations/README.md), [authentication and authorization](docs/26-authentication-authorization/README.md)
- Project: [secured task API](projects/backend/secured-task-api/README.md)
- Competency: validation, authn/authz distinction, token handling, and safe error contracts.

## Production Readiness

- Docs: [production readiness](docs/28-production-readiness-foundations/README.md), [Docker Java basics](docs/31-docker-java-basics/README.md), [CI/CD foundations](docs/32-ci-cd-foundations/README.md)
- Projects: [deployment-ready task API](projects/backend/deployment-ready-task-api/README.md)
- Competency: configuration, health checks, CI quality gates, release discipline, and rollback thinking.

## Cloud and Deployment

- Docs: [cloud readiness](docs/35-cloud-readiness-foundations/README.md), [cloud architecture](docs/51-cloud-architecture-foundations/README.md), [deployment foundations](docs/33-deployment-foundations/README.md), [release and rollback basics](docs/34-release-rollback-basics/README.md)
- Competency: managed services, networking, cost, runbooks, and deployment assumptions.

## Kubernetes

- Docs: [Kubernetes workloads](docs/40-kubernetes-workloads-networking-config/README.md), [Kubernetes operations](docs/41-kubernetes-operations-basics/README.md)
- Quizzes: [Kubernetes quizzes](quizzes/backend/kubernetes/answer-key.md)
- Competency: pods, deployments, services, probes, config, rollout investigation, and cleanup.

## Infrastructure as Code

- Docs: [Terraform and OpenTofu foundations](docs/47-terraform-opentofu-foundations/README.md), [IaC state and safety](docs/48-iac-state-secrets-safety/README.md)
- Quizzes: [IaC quizzes](quizzes/backend/iac/answer-key.md)
- Competency: state safety, modules, secrets, local validation, and operational change review.

## Observability and Reliability

- Docs: [observability foundations](docs/91-observability-foundations/README.md), [alerting and SLOs](docs/95-alerting-slos-and-incident-diagnostics/README.md), [reliability foundations](docs/96-reliability-engineering-foundations/README.md)
- Projects: [production diagnostics lab](projects/observability/production-diagnostics-lab/README.md), [reliable service simulator](projects/reliability/reliable-service-simulator/README.md)
- Exercises: [observability exercises](exercises/observability/README.md), [reliability exercises](exercises/reliability/README.md)

## Distributed Systems

- Docs: [distributed systems foundations](docs/101-distributed-systems-foundations/README.md), [distributed systems operations](docs/65-distributed-systems-operations/README.md)
- Examples: [distributed systems examples](examples/distributed-systems/java-distributed-systems/README.md)
- Project: [distributed cluster simulator](projects/distributed-systems/distributed-cluster-simulator/README.md)

## Event-Driven Architecture

- Docs: [event patterns](docs/68-event-patterns/README.md), [sagas and outbox recovery](docs/106-sagas-outbox-and-event-recovery/README.md)
- Examples: [event-driven foundations](examples/event-driven-architecture/java-event-driven-foundations/README.md)
- Project: [event-driven order workflow](projects/event-driven-architecture/event-driven-order-workflow/README.md)

## Messaging and Streaming

- Docs: [Kafka foundations](docs/73-kafka-foundations/README.md), [messaging systems](docs/107-messaging-systems-and-kafka-foundations/README.md), [RabbitMQ and JMS](docs/108-rabbitmq-jms-and-message-contracts/README.md), [streaming retries](docs/109-streaming-retries-and-messaging-operations/README.md)
- Examples: [messaging foundations](examples/messaging-and-streaming/java-messaging-foundations/README.md)
- Project: [order event pipeline](projects/messaging-and-streaming/order-event-pipeline/README.md)

## Backend System Design

- Docs: [system design foundations](docs/76-system-design-foundations/README.md), [scalability and reliability](docs/77-scalability-availability-reliability/README.md), [advanced requirements and architecture](docs/110-advanced-backend-system-design-requirements-and-architecture/README.md), [advanced data consistency and caching](docs/111-advanced-backend-system-design-data-consistency-caching-and-global-design/README.md), [advanced decisions](docs/112-advanced-backend-system-design-security-reliability-cost-and-decisions/README.md)
- Projects: [scalable commerce platform](projects/backend-system-design/scalable-commerce-platform/README.md), [scalable order platform design](projects/backend/scalable-order-platform-design/README.md)

## Testing and Performance

- Docs: [testing strategy](docs/113-testing-strategy-quality-engineering/README.md), [performance engineering](docs/86-performance-engineering-foundations/README.md), [JVM runtime](docs/87-jvm-runtime-and-memory/README.md), [profiling](docs/88-profiling-and-diagnostics/README.md), [JVM tuning](docs/90-jvm-tuning-and-capacity-planning/README.md), [benchmarking and optimization](docs/115-profiling-diagnostics-performance-optimization/README.md)
- Projects: [Java quality performance lab](projects/testing-performance/java-quality-performance-lab/README.md), [JVM performance lab](projects/performance/jvm-performance-lab/README.md)

## Capstone Projects

- Commerce capstone: [projects/capstones/commerce-platform](projects/capstones/commerce-platform/README.md)
- Distributed workflow capstone: [projects/capstones/distributed-workflow-platform](projects/capstones/distributed-workflow-platform/README.md)
- JVM diagnostics workbench: [projects/capstones/jvm-diagnostics-workbench](projects/capstones/jvm-diagnostics-workbench/README.md)
- Review guides: [docs/116-capstone-architecture-and-review-guides](docs/116-capstone-architecture-and-review-guides/README.md)

## Review and Assessment

- Capstone exercises: [exercises/capstone-system-design](exercises/capstone-system-design/README.md)
- Capstone solutions: [solutions/capstone-system-design](solutions/capstone-system-design/README.md)
- Final Java mastery assessment: [quizzes/final-java-mastery-assessment](quizzes/final-java-mastery-assessment/README.md)

## Portfolio Preparation

- Portfolio guide: [PORTFOLIO_GUIDE.md](PORTFOLIO_GUIDE.md)
- Changelog: [CHANGELOG.md](CHANGELOG.md)

## Recommended Sequence

1. Complete beginner, OOP, intermediate, and advanced Java sections.
2. Move through backend, persistence, security, production readiness, cloud, Kubernetes, and IaC.
3. Study observability, reliability, distributed systems, event-driven architecture, messaging, and system design.
4. Review testing and performance before attempting capstones.
5. Work through the three capstones, then the review guides, exercises, solutions, final assessment, portfolio guide, release notes, and completion report.
