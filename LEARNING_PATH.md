# Learning Path

Follow this path in order unless you already know a topic well.

1. Getting started: install Java 21, set up an IDE, compile and run programs.
2. Java basics: syntax, variables, data types, operators, input, conditionals, loops, methods, arrays, strings.
3. Object-oriented programming: classes, objects, constructors, encapsulation, inheritance, polymorphism, interfaces, records, enums.
4. Collections: lists, sets, queues, maps, iterators, sorting, Big-O basics.
5. Intermediate Java: generics, exceptions, files, NIO, dates, Maven, unit testing, debugging.
6. Functional Java: lambdas, functional interfaces, streams, Optional.
7. Advanced Java: concurrency, executors, futures, locks, concurrent collections, JVM, garbage collection, performance.
8. Professional practices: clean code, SOLID, design patterns, architecture, interview preparation.
9. Backend Java: HTTP, REST APIs, Spring Boot fundamentals, layering, validation, and simple API projects.
10. Backend persistence: SQL, repositories, JPA, Hibernate, Spring Data JPA, H2, and database-backed APIs.
11. Backend security and authentication: API risks, validation, secure errors, authentication, authorization, password hashing, tokens, JWT basics, and Spring Security.
12. Production readiness foundations: configuration, profiles, logging, observability, health checks, Docker basics, CI/CD, and deployment-readiness habits.
13. Deployment and cloud readiness: deployment lifecycle, artifacts, runtime configuration, smoke tests, release/rollback, runbooks, platform concepts, and cloud readiness.
14. Kubernetes and container orchestration foundations: orchestration basics, Kubernetes core concepts, workloads, networking, configuration, probes, resources, local-only manifests, and beginner operations.
15. Helm and Kubernetes packaging foundations: Helm basics, charts, templates, values, releases, upgrades, rollbacks, Java backend values, and render-only chart review.
16. Infrastructure as Code foundations: IaC concepts, Terraform/OpenTofu basics, providers, resources, variables, outputs, state, plans, secrets safety, modules, environments, local-only examples, and plan-review operations.
17. Advanced cloud architecture foundations: availability, scalability, reliability, traffic flow, load balancing, caching, queues, managed databases, object storage, cost-aware review, and operations planning.
18. Service mesh foundations: service mesh concepts, sidecar communication, service-to-service traffic, mTLS identity, traffic splitting, retries, timeouts, observability, security limits, operations, and readiness planning.
19. Distributed systems foundations: distributed architecture concepts, network unreliability, CAP theorem, consistency models, idempotency, retries, sagas, discovery, coordination, operations, and readiness planning.
20. Stage 26 event-driven architecture foundations: events, commands, queries, notifications, asynchronous communication, producers, consumers, event contracts, delivery semantics, ordering, sagas, outbox/inbox patterns, Java examples, an order workflow simulator, exercises, solutions, quizzes, and readiness planning.
21. Messaging and streaming with Java: messaging fundamentals, queues, topics, delivery reliability, Kafka, RabbitMQ, Java messaging boundaries, testing, observability, operations, examples, exercises, quizzes, and readiness planning.
22. Advanced backend system design: requirements, capacity estimation, architecture styles, service boundaries, communication, data ownership, consistency, caching, search, partitioning, multi-region recovery, security, reliability, observability, deployment, cost, ADRs, Java examples, a scalable commerce reference architecture, exercises, solutions, quizzes, and readiness planning before testing strategy and quality engineering.
23. Testing strategy and quality engineering: testing strategy, test design, unit/component/integration/contract/end-to-end testing, test doubles, isolation, coverage, mutation and property-based testing concepts, quality gates, CI testing strategy, flaky test management, examples, exercises, quizzes, and readiness planning before performance, profiling, and JVM tuning.
24. Performance, profiling, and JVM tuning: performance engineering foundations, latency, throughput, percentiles, JVM runtime and memory, profiling, diagnostics, benchmarking, load-test design, Java performance examples, JVM tuning, capacity planning, exercises, quizzes, and readiness planning before observability and production diagnostics.
25. Observability and production diagnostics: observability versus monitoring, structured logs, metrics, service health, tracing, request correlation, Java observability examples, diagnostic snapshots, alerting, SLOs, error budgets, incident evidence, exercises, quizzes, and readiness planning before reliability engineering, resilience, and failure recovery.
26. Stage 24 reliability engineering, resilience, and failure recovery: failure models, timeouts, retries, backoff, circuit breakers, bulkheads, fallbacks, idempotency, load shedding, recovery, deterministic resilience examples, service simulation, exercises, quizzes, and readiness planning before Stage 25.
27. Stage 25 distributed systems foundations: partial failure, unreliable networks, ordering, consistency, CAP, time and clocks, membership, failure detection, leader election terms, fencing, replication, partitioning, quorums, recovery, convergence, Java examples, a deterministic cluster simulator, exercises, solutions, and quizzes before Stage 26 event-driven architecture foundations.

Before moving on from each level, you should be able to explain the concepts in your own words, write a small example without copying, complete exercises, and build a mini project.

## Stage 27 Messaging And Streaming With Java Learning Sequence

Use this sequence after Stage 26 event-driven architecture foundations and before Stage 28 advanced backend system design.

- [ ] Study [Messaging Systems And Kafka Foundations](docs/107-messaging-systems-and-kafka-foundations/README.md)
- [ ] Study [RabbitMQ JMS And Message Contracts](docs/108-rabbitmq-jms-and-message-contracts/README.md)
- [ ] Study [Streaming Retries And Messaging Operations](docs/109-streaming-retries-and-messaging-operations/README.md)
- [ ] Run and modify [Java Messaging Foundations](examples/messaging-and-streaming/java-messaging-foundations/README.md)
- [ ] Build and explain the [Messaging and Streaming Order Pipeline](projects/messaging-and-streaming/order-event-pipeline/README.md)
- [ ] Complete [messaging and streaming exercises](exercises/messaging-and-streaming/README.md)
- [ ] Review [messaging and streaming solutions](solutions/messaging-and-streaming/README.md)
- [ ] Complete [messaging and streaming quizzes](quizzes/messaging-and-streaming/README.md)
- [ ] Explain duplicate delivery, acknowledgement timing, offset commits, retry budgets, dead-letter handling, replay safety, stream windows, late events, and messaging operations in your own words.

## Ready For Stage 28 Checklist

Move to advanced backend system design when you can:

- [ ] Compare queues, topics, Kafka consumer groups, RabbitMQ routing, and JMS abstractions accurately.
- [ ] Design Java producers and consumers that handle uncertain delivery and duplicate processing.
- [ ] Explain why broker persistence, acknowledgements, and offset commits do not prove business completion.
- [ ] Evolve message contracts with tolerant readers and contract tests.
- [ ] Design bounded retries, dead-letter destinations, replay workflows, and operational runbooks.
- [ ] Reason about stream transformations, event time, processing time, windows, watermarks, and late events.

## Stage 28 Advanced Backend System Design Learning Sequence

Use this sequence after Stage 27 messaging and streaming with Java and before Stage 29 testing strategy and quality engineering.

Stage 28 synthesizes backend development, cloud architecture, Kubernetes, service mesh, observability, reliability, distributed systems, event-driven architecture, and messaging into complete architecture decisions. The objective is to translate product requirements into secure, reliable, scalable, operable, and economically sensible Java backend architectures.

- [ ] Study requirements, capacity, and architecture styles: [Advanced Backend System Design Requirements And Architecture](docs/110-advanced-backend-system-design-requirements-and-architecture/README.md)
- [ ] Study data, consistency, caching, and global design: [Advanced Backend System Design Data Consistency Caching And Global Design](docs/111-advanced-backend-system-design-data-consistency-caching-and-global-design/README.md)
- [ ] Study security, reliability, cost, and decisions: [Advanced Backend System Design Security Reliability Cost And Decisions](docs/112-advanced-backend-system-design-security-reliability-cost-and-decisions/README.md)
- [ ] Run and modify the Java system-design examples: [Java Backend System Design Patterns](examples/backend-system-design/java-architecture-patterns/README.md)
- [ ] Build and explain the scalable commerce reference architecture: [Scalable Commerce Backend Reference Architecture](projects/backend-system-design/scalable-commerce-platform/README.md)
- [ ] Complete the exercises: [Backend System Design Exercises](exercises/backend-system-design/README.md)
- [ ] Review solutions after attempting the work: [Backend System Design Solutions](solutions/backend-system-design/README.md)
- [ ] Check understanding with quizzes: [Backend System Design Quiz](quizzes/backend-system-design/README.md)
- [ ] Explain requirements, capacity, boundaries, communication, data stores, consistency, caching, partitioning, multi-region recovery, security, SLOs, deployment, cost, ADRs, and fitness functions in your own words.

Recommended learning order:

1. Requirements, capacity, and architecture styles
2. Data, consistency, caching, and global design
3. Security, reliability, cost, and decisions
4. Java system-design examples
5. Scalable commerce reference architecture
6. Exercises
7. Solutions
8. Quiz
9. Stage review and completion checks

## Ready For Stage 29 Checklist

Move to testing strategy and quality engineering when you can:

- [ ] Clarify functional and non-functional requirements before choosing architecture.
- [ ] Estimate capacity, growth, bottlenecks, headroom, and uncertainty.
- [ ] Define module or service boundaries with data ownership and operational ownership.
- [ ] Choose synchronous and asynchronous communication patterns with failure handling.
- [ ] Choose data stores, transactions, consistency models, caches, search indexes, projections, and partitions from workload needs.
- [ ] Evaluate multi-region and disaster recovery strategies with RPO, RTO, failover, failback, and data-residency assumptions.
- [ ] Incorporate security, SLOs, observability, deployment, recovery, cost, ADRs, and fitness functions into the design.
- [ ] Run the Stage 28 Java example and scalable commerce project tests without external infrastructure.


## Stage 29 Testing And Performance Engineering Learning Sequence

Use this sequence after Stage 28 advanced backend system design and before Stage 30 capstone projects, repository polish, learning index, and release.

Stage 29 turns architecture assumptions into evidence. The objective is to design risk-based testing strategies, build deterministic and reliable tests, use quality gates thoughtfully, define performance requirements, measure latency and throughput, write valid JMH benchmarks, understand JVM runtime behavior, interpret profiling and diagnostics, and prevent quality and performance regressions.

- [ ] Study testing strategy and quality engineering: [Testing Strategy And Quality Engineering](docs/113-testing-strategy-quality-engineering/README.md)
- [ ] Study performance, benchmarking, and JVM runtime: [Performance Benchmarking And JVM Runtime](docs/114-performance-benchmarking-jvm-runtime/README.md)
- [ ] Study profiling, diagnostics, and optimization: [Profiling Diagnostics And Performance Optimization](docs/115-profiling-diagnostics-performance-optimization/README.md)
- [ ] Run and modify the Java testing and performance examples: [Java Quality And Performance Examples](examples/testing-performance/java-quality-and-performance/README.md)
- [ ] Build and explain the Java quality and performance engineering lab: [Java Quality Performance Lab](projects/testing-performance/java-quality-performance-lab/README.md)
- [ ] Complete the exercises: [Testing And Performance Exercises](exercises/testing-performance/README.md)
- [ ] Review solutions after attempting the work: [Testing And Performance Solutions](solutions/testing-performance/README.md)
- [ ] Check understanding with quizzes: [Testing And Performance Quiz](quizzes/testing-performance/README.md)
- [ ] Explain why tests, coverage, benchmarks, profiler output, and GC evidence each reduce uncertainty without proving production readiness.

Recommended learning order:

1. Testing strategy and quality engineering
2. Performance, benchmarking, and JVM runtime
3. Profiling, diagnostics, and optimization
4. Java testing and performance examples
5. Java quality and performance engineering lab
6. Exercises
7. Solutions
8. Quiz
9. Stage review and completion checks

## Ready For Stage 30 Checklist

Move to Stage 30 capstone projects, repository polish, learning index, and release when you can:

- [ ] Design a risk-based testing strategy and choose appropriate test levels.
- [ ] Build deterministic Java tests with injected clocks, identifiers, seeded randomness, and isolated fixtures.
- [ ] Distinguish mocks, stubs, fakes, spies, simulators, and real integrations.
- [ ] Explain quality gates without claiming coverage proves quality.
- [ ] Define performance requirements, workloads, budgets, and regression gates.
- [ ] Write or review a valid JMH benchmark and identify common benchmark mistakes.
- [ ] Explain JVM memory areas, JIT warm-up, garbage collection evidence, and profiler limitations.
- [ ] Diagnose CPU, allocation, locking, memory, GC, and concurrency bottlenecks from bounded evidence.
- [ ] Document optimization trade-offs, uncertainty, rollback criteria, and regression protection.

Next stage: Stage 30 — Capstone Projects, Repository Polish, Learning Index, and Release. Status: not started.

## Beginner Learning Sequence

Use this sequence before moving into object-oriented programming or intermediate topics.

- [ ] Set up Java and confirm `java -version` and `javac -version` work: [Getting Started](docs/00-getting-started/README.md)
- [ ] Read the Java basics overview: [Java Basics](docs/01-java-basics/README.md)
- [ ] Study the basics notes in order:
  - [Hello World and Syntax](docs/01-java-basics/01-hello-world-and-syntax.md)
  - [Variables, Data Types, and Operators](docs/01-java-basics/02-variables-data-types-operators.md)
  - [Input, Output, Conditionals, and Loops](docs/01-java-basics/03-input-output-conditionals-loops.md)
  - [Methods, Arrays, and Strings](docs/01-java-basics/04-methods-arrays-strings.md)
  - [Debugging and Problem Solving](docs/01-java-basics/05-debugging-problem-solving.md)
- [ ] Run and modify the beginner examples: [Beginner Examples](examples/beginner/README.md)
- [ ] Complete the beginner exercises: [Beginner Exercises](exercises/beginner/README.md)
- [ ] Build one beginner project, then build another without copying: [Beginner Projects](projects/beginner/README.md)
- [ ] Check your understanding with quizzes: [Beginner Quizzes](quizzes/beginner/README.md)
- [ ] Review available explanations after attempting the work yourself: [Beginner Solutions](solutions/beginner/README.md)

## Ready For OOP Checklist

Move to object-oriented programming when you can:

- [ ] Compile and run a Java file from the terminal.
- [ ] Use variables, operators, conditionals, and loops without copying.
- [ ] Write small methods that accept parameters and return values.
- [ ] Use arrays and strings in simple programs.
- [ ] Read beginner compiler errors and make a reasonable fix.
- [ ] Build a small CLI project with input, decisions, and repeated actions.

## Intermediate Learning Sequence

Use this sequence after the beginner path and core OOP topics.

- [ ] Review collections: [Collections Framework](docs/03-collections-framework/README.md)
- [ ] Study generics: [Generics](docs/04-generics/README.md)
- [ ] Practice error handling: [Exception Handling](docs/05-exception-handling/README.md)
- [ ] Read and write files: [Files, IO, And NIO](docs/06-files-io-nio/README.md)
- [ ] Learn functional Java: [Lambdas, Streams, And Optional](docs/07-lambdas-streams/README.md)
- [ ] Work with modern dates and times: [Date And Time API](docs/08-date-time-api/README.md)
- [ ] Add test coverage: [Testing And Debugging](docs/09-testing-debugging/README.md)
- [ ] Run builds with Maven: [Maven First](docs/10-maven-gradle/README.md)
- [ ] Run and modify intermediate examples: [Intermediate Examples](examples/intermediate/java/README.md)
- [ ] Complete intermediate exercises: [Intermediate Exercises](exercises/intermediate/)
- [ ] Build intermediate projects: [Intermediate Projects](projects/intermediate/README.md)
- [ ] Check understanding with intermediate quizzes: [Intermediate Quizzes](quizzes/intermediate/README.md)
- [ ] Review intermediate solutions after attempting the work: [Intermediate Solutions](solutions/intermediate/)

## Ready For Advanced Java Checklist

Move to advanced Java when you can:

- [ ] Use generics in classes and methods without raw types.
- [ ] Explain checked and unchecked exceptions.
- [ ] Read from and write to files with `Path` and `Files`.
- [ ] Use `LocalDate`, `LocalDateTime`, and `DateTimeFormatter` for common tasks.
- [ ] Write JUnit tests for service logic and edge cases.
- [ ] Use streams for clear collection transformations.
- [ ] Use `Optional` to represent missing results without returning `null`.
- [ ] Build an intermediate CLI project with separated model and service logic.

## Advanced Learning Sequence

Use this sequence after the intermediate path and before backend frameworks.

- [ ] Study concurrency fundamentals: [Concurrency And Multithreading](docs/11-concurrency-multithreading/README.md)
- [ ] Study JVM memory and performance basics: [JVM, Memory, Garbage Collection, And Performance](docs/12-jvm-memory-performance/README.md)
- [ ] Learn design pattern tradeoffs: [Design Patterns](docs/13-design-patterns/README.md)
- [ ] Explore advanced language features: [Advanced Java Language Features](docs/16-advanced-java/README.md)
- [ ] Practice maintainable design: [Clean Code, SOLID, And Basic Architecture](docs/17-clean-code-architecture/README.md)
- [ ] Run and modify advanced examples: [Advanced Examples](examples/advanced/)
- [ ] Complete advanced exercises: [Advanced Exercises](exercises/advanced/)
- [ ] Build an advanced project: [Advanced Projects](projects/advanced/README.md)
- [ ] Check understanding with advanced quizzes: [Advanced Quizzes](quizzes/advanced/README.md)
- [ ] Review advanced solutions after attempting the work: [Advanced Solutions](solutions/advanced/)

## Ready For Professional Java Checklist

Move toward backend, Spring, databases, and production-style topics when you can:

- [ ] Explain race conditions and protect shared state.
- [ ] Use executors and futures without leaking threads.
- [ ] Describe stack, heap, reachability, and garbage collection basics.
- [ ] Measure before making performance changes.
- [ ] Recognize when a design pattern helps and when it adds clutter.
- [ ] Apply SOLID principles to keep project logic testable.
- [ ] Build a CLI project that separates parsing, service logic, and output.

## Professional Java Learning Sequence

Use this sequence before Spring Boot, backend APIs, and production database applications.

- [ ] Review design patterns as tradeoffs: [Design Patterns](docs/13-design-patterns/README.md)
- [ ] Learn JDBC boundaries and safety rules: [Databases And JDBC](docs/14-databases-jdbc/README.md)
- [ ] Learn networking failure modes: [Networking](docs/15-networking/README.md)
- [ ] Revisit reflection and annotations: [Advanced Java Language Features](docs/16-advanced-java/README.md)
- [ ] Practice clean code and SOLID: [Clean Code, SOLID, And Basic Architecture](docs/17-clean-code-architecture/README.md)
- [ ] Run professional examples: [Professional Examples](examples/professional/)
- [ ] Complete professional exercises: [Professional Exercises](exercises/professional/)
- [ ] Check understanding with professional quizzes: [Professional Quizzes](quizzes/professional/)
- [ ] Review professional solutions after attempting the work: [Professional Solutions](solutions/professional/)

## Ready For Backend Frameworks Checklist

Move into Spring Boot and backend/API development when you can:

- [ ] Refactor long methods into clear, testable behavior.
- [ ] Explain SRP, OCP, LSP, ISP, and DIP with small examples.
- [ ] Choose simple design patterns only when they solve a real design problem.
- [ ] Explain reflection and annotation tradeoffs.
- [ ] Handle networking timeouts and failures deliberately.
- [ ] Use prepared statements and keep database credentials out of source code.
- [ ] Describe a basic project structure with domain, service, and infrastructure boundaries.

## Backend Java Learning Sequence

Use this sequence after professional Java and before full database, security, and deployment projects.

- [ ] Understand backend responsibilities: [Backend Java Foundations](docs/19-backend-java-foundations/README.md)
- [ ] Learn HTTP and REST API basics: [HTTP REST API Basics](docs/20-http-rest-api-basics/README.md)
- [ ] Review method and status choices: [HTTP Methods And Status Codes](docs/20-http-rest-api-basics/http-methods-status-codes.md)
- [ ] Study request and response anatomy: [Request And Response Anatomy](docs/20-http-rest-api-basics/request-response-anatomy.md)
- [ ] Practice REST resource design: [REST API Design Basics](docs/20-http-rest-api-basics/rest-api-design-basics.md)
- [ ] Learn Spring Boot fundamentals: [Spring Boot Introduction](docs/21-spring-boot-introduction/README.md)
- [ ] Review annotations and layering: [Spring Boot Common Annotations](docs/21-spring-boot-introduction/common-annotations.md) and [Backend Layering](docs/21-spring-boot-introduction/backend-layering.md)
- [ ] Run framework-free backend examples: [Backend API Design Basics](examples/backend/api-design-basics/README.md)
- [ ] Complete backend exercises: [Backend Exercises](exercises/backend/README.md)
- [ ] Check understanding with backend quizzes: [Backend Quizzes](quizzes/backend/README.md)
- [ ] Review backend solutions after attempting the work: [Backend Solutions](solutions/backend/README.md)
- [ ] Build the simple Spring Boot REST API skeleton: [Simple REST API](projects/backend/simple-rest-api/README.md)

## Ready For Full Backend Projects Checklist

Move into persistence and database-backed backend topics when you can:

- [ ] Explain the request-response lifecycle for a simple API.
- [ ] Choose suitable HTTP methods and status codes.
- [ ] Design resource-based endpoints without action-heavy paths.
- [ ] Separate controller, DTO, service, repository, and model responsibilities.
- [ ] Validate request data and return consistent error responses.
- [ ] Use constructor injection in Spring Boot classes.
- [ ] Test service logic without starting a full web server.
- [ ] Build and run a small in-memory REST API.

## Backend Persistence Learning Sequence

Use this sequence after the first Spring Boot REST API and before security, authentication, Docker, deployment, or production database topics.

- [ ] Study SQL and relational database foundations: [Database And SQL Foundations](docs/22-database-sql-foundations/README.md)
- [ ] Practice basic SQL statements: [SQL Basics](docs/22-database-sql-foundations/sql-basics.md)
- [ ] Understand keys, relationships, and transactions: [Keys, Relationships, And Transactions](docs/22-database-sql-foundations/keys-relationships-transactions.md)
- [ ] Learn backend persistence boundaries: [Backend Persistence Foundations](docs/23-backend-persistence-foundations/README.md)
- [ ] Review entity, DTO, and repository design: [Entity, DTO, And Repository](docs/23-backend-persistence-foundations/entity-dto-repository.md)
- [ ] Connect JDBC, ORM, JPA, Hibernate, and Spring Data: [JDBC, ORM, And JPA Overview](docs/23-backend-persistence-foundations/jdbc-orm-jpa-overview.md)
- [ ] Study JPA, Hibernate, and Spring Data JPA: [JPA, Hibernate, And Spring Data JPA](docs/24-jpa-hibernate-spring-data/README.md)
- [ ] Run framework-light persistence examples: [Persistence Design Basics](examples/backend/persistence-design-basics/README.md)
- [ ] Complete persistence exercises: [Backend Persistence Exercises](exercises/backend/persistence/sql-basics-exercises.md)
- [ ] Check understanding with persistence quizzes: [Backend Persistence Quizzes](quizzes/backend/persistence/sql-database-quiz.md)
- [ ] Review persistence solutions after attempting the work: [Backend Persistence Solutions](solutions/backend/persistence/sql-basics-solutions.md)
- [ ] Build the database-backed API: [Persistent Spring Boot Task API](projects/backend/persistent-task-api/README.md)

## Ready For Security And Deployment Checklist

Move beyond persistence when you can:

- [ ] Write simple `SELECT`, `INSERT`, `UPDATE`, and `DELETE` statements safely.
- [ ] Explain primary keys, foreign keys, and basic relationships.
- [ ] Describe why transactions matter.
- [ ] Separate entity classes from request and response DTOs.
- [ ] Use a Spring Data JPA repository from a service.
- [ ] Validate input before persistence.
- [ ] Return clear not-found and validation errors.
- [ ] Run a Spring Boot API against H2 without committing real credentials.

## Backend Security And Authentication Learning Sequence

Use this sequence after persistence and before Docker, deployment, production database configuration, CI/CD, observability, or cloud topics.

- [ ] Study common API security risks: [Backend Security Foundations](docs/25-backend-security-foundations/README.md)
- [ ] Review validation and safe error responses: [Secure Validation And Error Handling](docs/25-backend-security-foundations/secure-validation-error-handling.md)
- [ ] Separate identity from permissions: [Authentication And Authorization](docs/26-authentication-authorization/README.md)
- [ ] Learn credential and token concepts: [Password Hashing, Sessions, And Tokens](docs/26-authentication-authorization/password-hashing-sessions-tokens.md)
- [ ] Understand JWT tradeoffs before using them: [JWT Basics](docs/26-authentication-authorization/jwt-basics.md)
- [ ] Study Spring Security's core model: [Spring Security Introduction](docs/27-spring-security-introduction/README.md)
- [ ] Review roles, permissions, password encoding, and common mistakes: [Spring Security Common Mistakes](docs/27-spring-security-introduction/common-security-mistakes.md)
- [ ] Run framework-light security examples: [Security Auth Design Basics](examples/backend/security-auth-design-basics/README.md)
- [ ] Complete security exercises: [Backend Security Exercises](exercises/backend/security/auth-basics-exercises.md)
- [ ] Check understanding with security quizzes: [Backend Security Quizzes](quizzes/backend/security/auth-security-basics-quiz.md)
- [ ] Review security solutions after attempting the work: [Backend Security Solutions](solutions/backend/security/auth-basics-solutions.md)
- [ ] Build the protected API skeleton: [Secured Spring Boot Task API](projects/backend/secured-task-api/README.md)

## Ready For Production Backend Topics Checklist

Move into production-readiness topics when you can:

- [ ] Explain common API security risks without relying only on framework defaults.
- [ ] Validate untrusted input before authentication, authorization, persistence, or business decisions.
- [ ] Return useful errors without leaking stack traces, secrets, password hashes, or token contents.
- [ ] Explain authentication separately from authorization.
- [ ] Store passwords with a dedicated adaptive password encoder instead of reversible encryption or plain text.
- [ ] Describe the tradeoffs between sessions, opaque tokens, and JWTs.
- [ ] Configure public and protected routes with Spring Security.
- [ ] Use roles or permissions deliberately instead of hard-coding one-off access checks.
- [ ] Keep demo credentials, signing keys, and real secrets out of committed source code.
- [ ] Build a small secured API and explain how a request moves through authentication, authorization, controller, service, and repository layers.

## Production Readiness Learning Sequence

Use this sequence after backend security foundations and before cloud, Kubernetes, distributed systems, or real deployment topics.

- [ ] Study production readiness basics: [Production Readiness Foundations](docs/28-production-readiness-foundations/README.md)
- [ ] Review the readiness checklist: [Production Readiness Checklist](docs/28-production-readiness-foundations/production-readiness-checklist.md)
- [ ] Harden validation and error responses: [Error Handling And Validation Hardening](docs/28-production-readiness-foundations/error-handling-validation-hardening.md)
- [ ] Learn external configuration and profiles: [Configuration And Profiles](docs/29-configuration-profiles/README.md)
- [ ] Review safe secret handling: [Secrets And Environment Variables](docs/29-configuration-profiles/secrets-and-environment-variables.md)
- [ ] Study logging and observability basics: [Logging And Observability](docs/30-logging-observability/README.md)
- [ ] Learn metrics and health checks: [Metrics And Health Checks](docs/30-logging-observability/metrics-health-checks.md)
- [ ] Study local Docker concepts: [Docker For Java Basics](docs/31-docker-java-basics/README.md)
- [ ] Learn CI/CD foundations: [CI/CD Foundations](docs/32-ci-cd-foundations/README.md)
- [ ] Run production-readiness examples: [Production Readiness Examples](examples/backend/production-readiness/README.md)
- [ ] Review Docker and CI/CD learning assets: [Docker And CI/CD Learning Assets](examples/backend/docker-ci-cd/README.md)
- [ ] Complete production-readiness exercises: [Production Readiness Exercises](exercises/backend/production-readiness/config-profiles-exercises.md)
- [ ] Check understanding with production-readiness quizzes: [Production Readiness Quizzes](quizzes/backend/production-readiness/config-profiles-quiz.md)
- [ ] Review production-readiness solutions after attempting the work: [Production Readiness Solutions](solutions/backend/production-readiness/config-profiles-solutions.md)
- [ ] Build the production-readiness skeleton: [Production-Ready Task API Skeleton](projects/backend/production-ready-task-api/README.md)

## Ready For Later Deployment Topics Checklist

Move into deployment and cloud-readiness topics when you can:

- [ ] Explain which configuration values are safe to commit and which must stay external.
- [ ] Use local and test Spring profiles without committing real credentials.
- [ ] Return safe validation and error responses.
- [ ] Choose log levels and avoid logging secrets or sensitive personal data.
- [ ] Explain health checks, readiness, liveness, metrics, and basic tracing concepts.
- [ ] Describe what a Docker image contains and why secrets should not be baked into it.
- [ ] Explain a basic CI workflow that runs Maven tests on pull requests.
- [ ] Build and test a Spring Boot API with safe local/test configuration.
- [ ] Describe what is still missing for real production: secret management, TLS, managed database credentials, monitoring, backups, rollout, rollback, and incident response.

## Deployment And Cloud Readiness Learning Sequence

Use this sequence after production readiness and before Kubernetes, distributed systems, infrastructure as code, or advanced cloud topics.

- [ ] Study deployment basics: [Deployment Foundations](docs/33-deployment-foundations/README.md)
- [ ] Review deployment lifecycle and artifacts: [Deployment Lifecycle](docs/33-deployment-foundations/deployment-lifecycle.md)
- [ ] Learn release and rollback basics: [Release And Rollback Basics](docs/34-release-rollback-basics/README.md)
- [ ] Study cloud-readiness foundations: [Cloud Readiness Foundations](docs/35-cloud-readiness-foundations/README.md)
- [ ] Review managed services and deployed database planning: [Managed Services And Databases](docs/35-cloud-readiness-foundations/managed-services-and-databases.md)
- [ ] Practice deployment runbooks: [Deployment Checklists And Runbooks](docs/36-deployment-checklists-runbooks/README.md)
- [ ] Study vendor-neutral platform notes: [Platform Deployment Notes](docs/37-platform-deployment-notes/README.md)
- [ ] Run deployment-readiness examples: [Deployment Readiness Examples](examples/backend/deployment-readiness/README.md)
- [ ] Build the deployment-ready API skeleton: [Deployment-Ready Task API Skeleton](projects/backend/deployment-ready-task-api/README.md)
- [ ] Complete deployment/cloud exercises: [Deployment Readiness Exercises](exercises/backend/deployment-readiness/deployment-basics-exercises.md)
- [ ] Check understanding with deployment/cloud quizzes: [Deployment Readiness Quizzes](quizzes/backend/deployment-readiness/deployment-basics-quiz.md)
- [ ] Review deployment/cloud solutions after attempting the work: [Deployment Readiness Solutions](solutions/backend/deployment-readiness/deployment-basics-solutions.md)

## Kubernetes And Container Orchestration Learning Sequence

Use this sequence after deployment/cloud readiness and before Helm, infrastructure as code, service mesh, distributed systems, or production Kubernetes topics.

- [ ] Study orchestration basics: [Container Orchestration Foundations](docs/38-container-orchestration-foundations/README.md)
- [ ] Learn desired state, scaling, and self-healing: [Desired State, Scaling, And Self-Healing](docs/38-container-orchestration-foundations/desired-state-scaling-self-healing.md)
- [ ] Study Kubernetes cluster basics: [Kubernetes Foundations](docs/39-kubernetes-foundations/README.md)
- [ ] Review manifests and kubectl concepts: [kubectl And Manifests](docs/39-kubernetes-foundations/kubectl-and-manifests.md)
- [ ] Learn local-only cluster options conceptually: [Local Learning Clusters](docs/39-kubernetes-foundations/local-learning-clusters.md)
- [ ] Study workloads, networking, and configuration: [Kubernetes Workloads, Networking, And Configuration](docs/40-kubernetes-workloads-networking-config/README.md)
- [ ] Review Pods, Deployments, and Services: [Pods, Deployments, And Services](docs/40-kubernetes-workloads-networking-config/pods-deployments-services.md)
- [ ] Review ConfigMaps, Secrets, labels, selectors, probes, resources, and rolling updates: [Health Probes And Resources](docs/40-kubernetes-workloads-networking-config/health-probes-resources.md)
- [ ] Inspect local-only manifest examples: [Kubernetes Manifest Basics](examples/backend/kubernetes-manifests/README.md)
- [ ] Review Kubernetes-readiness notes for the backend API: [Kubernetes-Ready Task API Notes](projects/backend/kubernetes-ready-task-api/README.md)
- [ ] Study beginner operations: [Kubernetes Operations Basics](docs/41-kubernetes-operations-basics/README.md)
- [ ] Complete Kubernetes exercises: [Kubernetes Foundation Exercises](exercises/backend/kubernetes/orchestration-basics-exercises.md)
- [ ] Check understanding with Kubernetes quizzes: [Kubernetes Foundation Quizzes](quizzes/backend/kubernetes/orchestration-kubernetes-basics-quiz.md)
- [ ] Review Kubernetes solutions after attempting the work: [Kubernetes Foundation Solutions](solutions/backend/kubernetes/orchestration-basics-solutions.md)

## Ready For Advanced Cloud Topics Checklist

Move into Helm and Kubernetes packaging when you can:

- [ ] Explain the difference between local, test, staging, and production deployment environments.
- [ ] Describe JARs, container images, and release packages.
- [ ] Supply runtime configuration without committing secrets.
- [ ] Run health checks and smoke tests after deployment.
- [ ] Explain release versioning and rollback triggers.
- [ ] Write a simple deployment runbook and release note.
- [ ] Explain managed database, object storage, DNS, domains, HTTPS, and firewall/security group concepts.
- [ ] Describe cloud cost and quota risks.
- [ ] Explain Kubernetes desired state, Pods, Deployments, Services, ConfigMaps, Secrets, labels, selectors, probes, resources, rollouts, and common failure states.
- [ ] Read local-only Kubernetes manifests without treating them as production templates.
- [ ] Render and inspect local Kubernetes manifests without applying them to a real cluster.

## Helm And Kubernetes Packaging Learning Sequence

Use this sequence after Kubernetes foundations and before infrastructure as code, service mesh, distributed systems, advanced cloud, or production Kubernetes topics.

- [ ] Study Helm packaging basics: [Helm Packaging Foundations](docs/42-helm-packaging-foundations/README.md)
- [ ] Compare raw YAML and charts: [Kubernetes YAML Vs Helm](docs/42-helm-packaging-foundations/kubernetes-yaml-vs-helm.md)
- [ ] Learn chart structure: [Helm Chart Basics](docs/43-helm-chart-basics/README.md)
- [ ] Review templates and values: [Templates And Values](docs/43-helm-chart-basics/templates-and-values.md)
- [ ] Study Java backend values: [Helm Values For Java Backends](docs/44-helm-values-java-backend/README.md)
- [ ] Review config, Secret placeholders, probes, resources, ports, and overrides: [Environment Overrides](docs/44-helm-values-java-backend/environment-overrides.md)
- [ ] Render the local-only chart example: [Helm Chart Basics Example](examples/backend/helm-chart-basics/README.md)
- [ ] Review Helm-ready backend API notes: [Helm-Ready Task API Notes](projects/backend/helm-ready-task-api/README.md)
- [ ] Study Helm operations basics: [Helm Operations Basics](docs/45-helm-operations-basics/README.md)
- [ ] Complete Helm exercises: [Helm Foundation Exercises](exercises/backend/helm/helm-basics-exercises.md)
- [ ] Check understanding with Helm quizzes: [Helm Foundation Quizzes](quizzes/backend/helm/helm-packaging-basics-quiz.md)
- [ ] Review Helm solutions after attempting the work: [Helm Foundation Solutions](solutions/backend/helm/helm-basics-solutions.md)

## Ready For Advanced Cloud Topics Checklist

Move into infrastructure as code first, then service mesh, distributed systems, advanced cloud, or production Kubernetes topics later when you can:

- [ ] Explain what Helm adds on top of raw Kubernetes YAML.
- [ ] Identify `Chart.yaml`, `values.yaml`, `templates/`, helper templates, and rendered manifests.
- [ ] Use safe placeholder values for local-only charts.
- [ ] Explain image repository/tag, service ports, container ports, environment values, probes, and resources in a Java backend chart.
- [ ] Run `helm lint` and `helm template` without installing into a cluster.
- [ ] Explain Helm releases, upgrades, rollbacks, history, and uninstall concepts.
- [ ] Explain what still requires real production ownership: secret management, TLS, monitoring, backups, incident response, and provider-specific operations.

## Infrastructure As Code Learning Sequence

Use this sequence after Helm packaging and before service mesh, distributed systems, advanced cloud, production Kubernetes, or real provider automation.

- [ ] Study IaC foundations: [Infrastructure As Code Foundations](docs/46-infrastructure-as-code-foundations/README.md)
- [ ] Compare manual infrastructure, declarative desired state, drift, and reviewable changes: [IaC Overview](docs/46-infrastructure-as-code-foundations/iac-overview.md)
- [ ] Study Terraform/OpenTofu basics: [Terraform And OpenTofu Foundations](docs/47-terraform-opentofu-foundations/README.md)
- [ ] Review providers, resources, variables, outputs, state, plans, apply, and destroy concepts: [State, Plan, Apply, And Destroy](docs/47-terraform-opentofu-foundations/state-plan-apply-destroy.md)
- [ ] Study state and secrets safety: [IaC State, Secrets, And Safety](docs/48-iac-state-secrets-safety/README.md)
- [ ] Review safe local-only validation habits: [Local-Only Validation](docs/48-iac-state-secrets-safety/local-only-validation.md)
- [ ] Inspect the safe local-only IaC example: [Local-Only IaC Basics](examples/backend/iac-local-only/README.md)
- [ ] Review IaC-readiness notes for the backend API: [IaC-Ready Task API Notes](projects/backend/iac-ready-task-api/README.md)
- [ ] Study modules and environment structure: [IaC Modules And Environments](docs/49-iac-modules-environments/README.md)
- [ ] Study operations basics: [IaC Operations Basics](docs/50-iac-operations-basics/README.md)
- [ ] Complete IaC exercises: [IaC Foundation Exercises](exercises/backend/iac/iac-basics-exercises.md)
- [ ] Check understanding with IaC quizzes: [IaC Foundation Quizzes](quizzes/backend/iac/iac-basics-quiz.md)
- [ ] Review IaC solutions after attempting the work: [IaC Foundation Solutions](solutions/backend/iac/iac-basics-solutions.md)

## Ready For Later Infrastructure Topics Checklist

Move into advanced cloud architecture first, then service mesh, distributed systems, event-driven architecture implementation, production Kubernetes, or real provider automation later when you can:

- [ ] Explain Infrastructure as Code and why reviewable infrastructure changes matter.
- [ ] Compare manual changes with declarative desired state.
- [ ] Explain drift and why plans must be reviewed before real applies.
- [ ] Identify providers, resources, data sources, variables, outputs, state, and modules.
- [ ] Explain why state files, plan files, provider credentials, real secrets, kubeconfig files, and generated provider directories do not belong in Git.
- [ ] Inspect local-only Terraform/OpenTofu examples without treating them as production templates.
- [ ] Design a simple Java backend runtime module with safe inputs and non-secret outputs.
- [ ] Describe environment folders for dev, test, staging, and prod without copying real secrets.
- [ ] Explain why real production IaC requires least privilege, remote state controls, approvals, backup/recovery thinking, and deeper provider-specific review.

## Cloud Architecture Learning Sequence

Use this sequence after IaC foundations and before service mesh, distributed systems, event-driven architecture implementation, or advanced production infrastructure.

- [ ] Study cloud architecture foundations: [Cloud Architecture Foundations](docs/51-cloud-architecture-foundations/README.md)
- [ ] Compare application and infrastructure architecture: [Application Vs Infrastructure Architecture](docs/51-cloud-architecture-foundations/application-vs-infrastructure-architecture.md)
- [ ] Study availability, scalability, and reliability: [Availability, Scalability, And Reliability](docs/52-availability-scalability-reliability/README.md)
- [ ] Review stateless/stateful services, fault tolerance, and graceful degradation: [Stateless And Stateful Services](docs/52-availability-scalability-reliability/stateless-stateful-services.md)
- [ ] Study traffic and data patterns: [Cloud Traffic And Data Patterns](docs/53-cloud-traffic-data-patterns/README.md)
- [ ] Review load balancing, caching, queues, managed databases, object storage, and complexity tradeoffs: [Complexity Tradeoffs](docs/53-cloud-traffic-data-patterns/complexity-tradeoffs.md)
- [ ] Study cost-aware review: [Cloud Cost And Architecture Review](docs/54-cloud-cost-architecture-review/README.md)
- [ ] Inspect vendor-neutral design examples: [Cloud Architecture Design Examples](examples/backend/cloud-architecture-designs/README.md)
- [ ] Review cloud architecture readiness notes for the backend API: [Cloud Architecture-Ready Task API Notes](projects/backend/cloud-architecture-ready-task-api/README.md)
- [ ] Study operational architecture basics: [Cloud Architecture Operations Basics](docs/55-cloud-architecture-operations-basics/README.md)
- [ ] Complete cloud architecture exercises: [Cloud Architecture Exercises](exercises/backend/cloud-architecture/architecture-basics-exercises.md)
- [ ] Check understanding with cloud architecture quizzes: [Cloud Architecture Quizzes](quizzes/backend/cloud-architecture/cloud-architecture-basics-quiz.md)
- [ ] Review cloud architecture solutions after attempting the work: [Cloud Architecture Solutions](solutions/backend/cloud-architecture/architecture-basics-solutions.md)

## Ready For Later Architecture Topics Checklist

Move into service mesh, distributed systems, event-driven architecture implementation, or advanced production infrastructure later when you can:

- [ ] Explain cloud architecture as application, infrastructure, data, operations, reliability, and cost working together.
- [ ] Identify single points of failure in a simple backend design.
- [ ] Compare horizontal and vertical scaling.
- [ ] Explain why stateless backend services scale more easily.
- [ ] Trace traffic through DNS, load balancer, backend service, and managed database.
- [ ] Explain when caches, queues, object storage, and CDNs help and when they add premature complexity.
- [ ] Review a design for reliability, security, operations, and cost.
- [ ] Describe basic SLO, SLA, error budget, capacity, retry, timeout, backpressure, and incident runbook concepts.
- [ ] Explain what remains out of scope: service mesh, distributed systems implementation, event-driven architecture implementation, provider-specific automation, and production infrastructure controls.

## Service Mesh Learning Sequence

Use this sequence after cloud architecture foundations and before distributed systems implementation, event-driven architecture implementation, production Kubernetes, or real mesh operations.

- [ ] Study service mesh foundations: [Service Mesh Foundations](docs/56-service-mesh-foundations/README.md)
- [ ] Compare mesh responsibilities with gateways and application code: [Service Mesh Vs API Gateway](docs/56-service-mesh-foundations/service-mesh-vs-api-gateway.md)
- [ ] Study sidecar service communication: [Sidecar Service Communication](docs/57-sidecar-service-communication/README.md)
- [ ] Review mTLS and service identity: [mTLS And Service Identity](docs/57-sidecar-service-communication/mtls-and-service-identity.md)
- [ ] Study traffic and reliability policy: [Service Mesh Traffic And Reliability](docs/58-service-mesh-traffic-reliability/README.md)
- [ ] Study observability and security boundaries: [Service Mesh Observability And Security](docs/59-service-mesh-observability-security/README.md)
- [ ] Inspect service mesh design examples: [Service Mesh Design Examples](examples/backend/service-mesh-designs/README.md)
- [ ] Review service mesh readiness notes for the backend API: [Service Mesh-Ready Task API Notes](projects/backend/service-mesh-ready-task-api/README.md)
- [ ] Study operations basics: [Service Mesh Operations Basics](docs/60-service-mesh-operations-basics/README.md)
- [ ] Complete service mesh exercises: [Service Mesh Exercises](exercises/backend/service-mesh/service-mesh-basics-exercises.md)
- [ ] Check understanding with service mesh quizzes: [Service Mesh Quizzes](quizzes/backend/service-mesh/service-mesh-basics-quiz.md)
- [ ] Review service mesh solutions after attempting the work: [Service Mesh Solutions](solutions/backend/service-mesh/service-mesh-basics-solutions.md)

## Ready For Later Distributed Systems Topics Checklist

Move into distributed systems foundations first, then event-driven architecture implementation, production Kubernetes, or real provider-specific mesh operations later when you can:

- [ ] Explain service mesh as infrastructure for internal service-to-service communication.
- [ ] Separate service mesh, API gateway, Kubernetes, and application responsibilities.
- [ ] Trace a request through sidecars or equivalent data-plane components.
- [ ] Explain control plane and data plane responsibilities.
- [ ] Describe mTLS and workload identity without confusing them with user authentication.
- [ ] Design safe timeout, retry, and circuit-breaker policies for idempotent and non-idempotent operations.
- [ ] Plan a canary with metrics, logs, traces, business checks, and rollback criteria.
- [ ] Explain observability signals from traces, metrics, and access logs.
- [ ] Identify when a service mesh is premature.
- [ ] Explain what remains out of scope: real mesh installation, cluster operations, production certificates, provider-specific automation, and distributed systems implementation.

## Distributed Systems Learning Sequence

Use this sequence after service mesh foundations and before event-driven architecture, messaging systems, streaming systems, advanced production infrastructure, or real distributed system implementation.

- [ ] Study distributed systems foundations: [Distributed Systems Foundations](docs/61-distributed-systems-foundations/README.md)
- [ ] Review network latency, partial failures, and common misconceptions: [Network Latency And Failures](docs/61-distributed-systems-foundations/network-latency-and-failures.md)
- [ ] Study consistency and CAP theorem: [Consistency And CAP](docs/62-consistency-and-cap/README.md)
- [ ] Compare consistency models and availability tradeoffs: [Consistency Models](docs/62-consistency-and-cap/consistency-models.md)
- [ ] Study idempotency, retries, and saga concepts: [Idempotency, Retries, And Sagas](docs/63-idempotency-retries-sagas/README.md)
- [ ] Review safe retry and distributed transaction tradeoffs: [Retry Patterns](docs/63-idempotency-retries-sagas/retry-patterns.md)
- [ ] Study discovery and coordination concepts: [Distributed Discovery And Coordination](docs/64-distributed-discovery-coordination/README.md)
- [ ] Review service discovery, leader election, consensus, and distributed locks: [Consensus Overview](docs/64-distributed-discovery-coordination/consensus-overview.md)
- [ ] Inspect distributed systems design examples: [Distributed Systems Design Examples](examples/backend/distributed-systems-designs/request-flow-example.md)
- [ ] Review distributed systems readiness notes for the backend API: [Distributed Systems Ready Task API Notes](projects/backend/distributed-systems-ready-task-api/README.md)
- [ ] Study distributed systems operations: [Distributed Systems Operations](docs/65-distributed-systems-operations/README.md)
- [ ] Complete distributed systems exercises: [Distributed Systems Exercises](exercises/backend/distributed-systems/cap-exercises.md)
- [ ] Check understanding with distributed systems quizzes: [Distributed Systems Quizzes](quizzes/backend/distributed-systems/multiple-choice-quiz.md)
- [ ] Review distributed systems solutions after attempting the work: [Distributed Systems Solutions](solutions/backend/distributed-systems/cap-solutions.md)

## Ready For Event-Driven And Messaging Topics Checklist

Move into event-driven architecture first, then Stage 19 messaging and streaming with Java, or advanced production infrastructure later when you can:

- [ ] Explain distributed systems without treating microservices as automatically better.
- [ ] Describe latency, timeouts, partial failures, and duplicate requests.
- [ ] Explain CAP theorem as an operation-level tradeoff during partitions.
- [ ] Choose between strong consistency, eventual consistency, and read-after-write consistency for simple workflows.
- [ ] Design idempotency keys for retried create operations.
- [ ] Describe retry limits, backoff, jitter, and retry-storm risk.
- [ ] Explain saga steps, compensation actions, and workflow status.
- [ ] Describe service discovery, leader election, consensus, and distributed locks at a conceptual level.
- [ ] Use logs, metrics, traces, and workflow state to investigate distributed failures.
- [ ] Explain what remains out of scope: real distributed system deployment, production messaging infrastructure, streaming infrastructure, provider-specific automation, and advanced production controls.

## Event-Driven Architecture Learning Sequence

Use this sequence after Stage 25 distributed systems foundations and before Stage 27 messaging and streaming with Java, advanced production infrastructure, or real broker deployment.

- [ ] Study event-driven architecture foundations: [Event-Driven Architecture Foundations](docs/104-event-driven-architecture-foundations/README.md)
- [ ] Study event contracts, delivery, and ordering: [Event Contracts Delivery And Ordering](docs/105-event-contracts-delivery-and-ordering/README.md)
- [ ] Study sagas, outbox, and event recovery: [Sagas Outbox And Event Recovery](docs/106-sagas-outbox-and-event-recovery/README.md)
- [ ] Run and modify Java event-driven examples: [Java Event-Driven Foundations](examples/event-driven-architecture/java-event-driven-foundations/README.md)
- [ ] Build and explain the order workflow project: [Event-Driven Order Workflow Simulator](projects/event-driven-architecture/event-driven-order-workflow/README.md)
- [ ] Complete event-driven architecture exercises: [Event-Driven Architecture Exercises](exercises/event-driven-architecture/README.md)
- [ ] Review event-driven architecture solutions after attempting the work: [Event-Driven Architecture Solutions](solutions/event-driven-architecture/README.md)
- [ ] Check understanding with the event-driven architecture quiz: [Event-Driven Architecture Quiz](quizzes/event-driven-architecture/README.md)
- [ ] Explain event contracts, duplicate delivery, idempotent consumers, ordering, retries, dead letters, replay, sagas, compensation, outbox/inbox patterns, and operational readiness before Stage 27 messaging and streaming with Java.

## Ready For Messaging And Streaming With Java Checklist

Move into Stage 27 messaging and streaming with Java when you can:

- [ ] Explain the difference between events and commands.
- [ ] Decide when synchronous communication is clearer than asynchronous communication.
- [ ] Identify producers, consumers, event contracts, and event ownership.
- [ ] Design event names and payloads with compatibility in mind.
- [ ] Compare publish/subscribe, fan-out, event notification, event-carried state transfer, choreography, and orchestration.
- [ ] Explain the transactional messaging problem and how an outbox changes the failure model.
- [ ] Explain CQRS, read models, and write models without over-applying the pattern.
- [ ] Plan duplicate handling, poison event review, correlation identifiers, tracing, replay, and failure investigation.
- [ ] Explain what remains out of scope until Stage 27: concrete messaging products, streaming products, broker setup, clusters, cloud messaging resources, credentials, and production infrastructure.

## Messaging And Streaming With Java Learning Sequence

Use this sequence after event-driven architecture foundations and before Stage 20 advanced backend system design or real production messaging infrastructure.

- [ ] Study messaging and streaming foundations: [Messaging And Streaming Foundations](docs/71-messaging-and-streaming-foundations/README.md)
- [ ] Compare messaging, streaming, direct request/response, queues, topics, producers, consumers, and brokers: [Messaging Basics](docs/71-messaging-and-streaming-foundations/messaging-basics.md)
- [ ] Study delivery reliability: [Message Delivery And Reliability](docs/72-message-delivery-and-reliability/README.md)
- [ ] Review acknowledgements, retries, dead letters, duplicates, idempotency, and ordering: [Delivery Guarantees](docs/72-message-delivery-and-reliability/delivery-guarantees.md)
- [ ] Study Kafka foundations: [Kafka Foundations](docs/73-kafka-foundations/README.md)
- [ ] Review topics, partitions, offsets, records, producers, consumers, consumer groups, keys, retention, and replay: [Kafka Architecture](docs/73-kafka-foundations/kafka-architecture.md)
- [ ] Study RabbitMQ foundations: [RabbitMQ Foundations](docs/74-rabbitmq-foundations/README.md)
- [ ] Review exchanges, queues, bindings, routing keys, exchange types, acknowledgements, dead letters, and prefetch: [RabbitMQ Architecture](docs/74-rabbitmq-foundations/rabbitmq-architecture.md)
- [ ] Run and modify pure Java messaging examples: [Java Messaging Patterns](examples/backend/java-messaging-patterns/README.md)
- [ ] Review messaging-ready backend service design: [Messaging-Ready Service Design](projects/backend/messaging-ready-service/README.md)
- [ ] Study messaging operations and observability: [Messaging Operations And Observability](docs/75-messaging-operations-and-observability/README.md)
- [ ] Complete messaging and streaming exercises: [Messaging And Streaming Exercises](exercises/backend/messaging-streaming/messaging-streaming-exercises.md)
- [ ] Check understanding with messaging and streaming quizzes: [Messaging And Streaming Quizzes](quizzes/backend/messaging-streaming/README.md)
- [ ] Review messaging and streaming solutions after attempting the work: [Messaging And Streaming Solutions](solutions/backend/messaging-streaming/messaging-streaming-solutions.md)

## Ready For Advanced Backend System Design Checklist

Move into Stage 20 advanced backend system design when you can:

- [ ] Explain queues, topics, producers, consumers, brokers, and message lifecycles.
- [ ] Compare messaging, streaming, and direct request/response.
- [ ] Explain at-most-once, at-least-once, exactly-once concepts, acknowledgements, retries, backoff, dead letters, duplicates, idempotent consumers, and ordering limits.
- [ ] Describe Kafka topics, partitions, offsets, records, producers, consumers, consumer groups, keys, retention, replay, and delivery semantics.
- [ ] Describe RabbitMQ exchanges, queues, bindings, routing keys, exchange types, acknowledgements, durability concepts, dead letters, and prefetch.
- [ ] Keep Java domain code behind producer, consumer, serialization, retry, dead-letter, idempotency, and observability boundaries.
- [ ] Test messaging behavior without requiring a broker when the behavior is pure application logic.
- [ ] Investigate lag, queue depth, retries, dead letters, duplicates, correlation identifiers, traces, and slow consumers.
- [ ] Explain what remains out of scope: production broker provisioning, cloud-managed messaging services, real credentials, production tuning, and advanced backend system design tradeoff synthesis.

## Advanced Backend System Design Learning Sequence

Use this sequence after Stage 19 messaging and streaming with Java and before Stage 21 testing strategy and quality engineering.

- [ ] Study system design foundations: [System Design Foundations](docs/76-system-design-foundations/README.md)
- [ ] Practice requirements clarification, assumptions, workflows, boundaries, diagrams, and review questions: [System Design Process](docs/76-system-design-foundations/system-design-process.md)
- [ ] Study scalability, availability, and reliability: [Scalability Availability And Reliability](docs/77-scalability-availability-reliability/README.md)
- [ ] Compare scaling, fault tolerance, redundancy, resilience, backpressure, recovery objectives, and SLO thinking: [SLOs Recovery And Tradeoffs](docs/77-scalability-availability-reliability/slos-recovery-and-tradeoffs.md)
- [ ] Study data system design: [Data System Design](docs/78-data-system-design/README.md)
- [ ] Review data ownership, read/write models, consistency, replication, partitioning, migrations, and privacy boundaries: [Data Design Review Questions](docs/78-data-system-design/mistakes-and-review-questions.md)
- [ ] Study service architecture patterns: [Service Architecture Patterns](docs/79-service-architecture-patterns/README.md)
- [ ] Compare layered, hexagonal, clean architecture, modular monoliths, microservices, orchestration, choreography, sagas, and strangler migrations: [Architecture Comparisons](docs/79-service-architecture-patterns/comparisons-failure-modes-reviews.md)
- [ ] Run and modify pure Java system design pattern examples: [Backend System Design Patterns](examples/backend/system-design-patterns/README.md)
- [ ] Review the scalable order platform case study: [Scalable Order Platform Design](projects/backend/scalable-order-platform-design/README.md)
- [ ] Study operations and evolutionary architecture: [System Design Operations And Evolution](docs/80-system-design-operations-and-evolution/README.md)
- [ ] Complete advanced backend system design exercises: [Advanced Backend System Design Exercises](exercises/backend/system-design/system-design-exercises.md)
- [ ] Check understanding with advanced backend system design quizzes: [Advanced Backend System Design Quizzes](quizzes/backend/system-design/README.md)
- [ ] Review advanced backend system design solutions after attempting the work: [Advanced Backend System Design Solutions](solutions/backend/system-design/system-design-solutions.md)

## Ready For Testing Strategy And Quality Engineering Checklist

Move into Stage 21 testing strategy and quality engineering when you can:

- [ ] Clarify functional and non-functional requirements before proposing architecture.
- [ ] Estimate capacity with explicit assumptions and revisit measurements.
- [ ] Compare modular monoliths and microservices without architecture absolutism.
- [ ] Explain read paths, write paths, data ownership, consistency choices, replication, partitioning, and migration tradeoffs.
- [ ] Design boundaries for synchronous and asynchronous communication with idempotency, retries, circuit breakers, and backpressure in mind.
- [ ] Discuss observability, failure scenarios, security boundaries, cost awareness, and ADRs as part of the design.
- [ ] Explain what remains out of scope until Stage 21: full testing strategy, quality gates, test pyramid/deployment test strategy synthesis, and quality engineering practice.

## Testing Strategy And Quality Engineering Learning Sequence

Use this sequence after Stage 20 advanced backend system design and before Stage 22 performance, profiling, and JVM tuning.

- [ ] Study testing strategy foundations: [Testing Strategy Foundations](docs/81-testing-strategy-foundations/README.md)
- [ ] Review why testing exists and how it reduces risk: [Why Testing Exists](docs/81-testing-strategy-foundations/why-testing-exists.md)
- [ ] Compare test levels and strategy tradeoffs: [Testing Levels And Tradeoffs](docs/81-testing-strategy-foundations/testing-levels-and-tradeoffs.md)
- [ ] Study test design and isolation: [Test Design And Isolation](docs/82-test-design-and-isolation/README.md)
- [ ] Practice readable test structure: [Readable Test Structure](docs/82-test-design-and-isolation/readable-test-structure.md)
- [ ] Review deterministic test boundaries: [Isolation And Determinism](docs/82-test-design-and-isolation/isolation-and-determinism.md)
- [ ] Study integration, contract, and end-to-end testing: [Integration, Contract, And End-To-End Testing](docs/83-integration-contract-and-e2e-testing/README.md)
- [ ] Study quality engineering practices: [Quality Engineering Practices](docs/84-quality-engineering-practices/README.md)
- [ ] Review coverage, mutation, and generative testing signals: [Coverage, Mutation, And Generative Testing](docs/84-quality-engineering-practices/coverage-mutation-and-generative-testing.md)
- [ ] Run and modify Java testing pattern examples: [Java Testing Patterns](examples/testing/java-testing-patterns/README.md)
- [ ] Build and explain the testing strategy lab: [Testing Strategy Lab](projects/testing/testing-strategy-lab/README.md)
- [ ] Study CI testing and quality gates: [Testing In CI And Quality Gates](docs/85-testing-ci-quality-gates/README.md)
- [ ] Complete testing and quality engineering exercises: [Testing And Quality Engineering Exercises](exercises/testing/testing-and-quality-engineering-exercises.md)
- [ ] Check understanding with testing and quality engineering quizzes: [Testing And Quality Engineering Quizzes](quizzes/testing/README.md)
- [ ] Review testing and quality engineering solutions after attempting the work: [Testing And Quality Engineering Solutions](solutions/testing/testing-and-quality-engineering-solutions.md)

## Ready For Performance, Profiling, And JVM Tuning Checklist

Move into Stage 22 performance, profiling, and JVM tuning when you can:

- [ ] Explain how tests support system design assumptions without proving complete correctness.
- [ ] Choose between unit, component, integration, contract, and end-to-end tests by risk and boundary.
- [ ] Keep tests deterministic with controlled time, IDs, randomness, data, and file-system state.
- [ ] Use test doubles deliberately without mocking every dependency.
- [ ] Interpret coverage, mutation, and static-analysis results as quality signals rather than guarantees.
- [ ] Design CI quality gates that separate required checks from advisory evidence.
- [ ] Diagnose flaky tests with logs, timing data, seeds, environment metadata, and ownership.
- [ ] Explain release confidence as evidence-based risk reduction, not production certification.
- [ ] Explain what remains out of scope until Stage 22: measurement-driven performance analysis, profiling tools, JVM tuning, garbage-collection tuning, benchmarking methodology, and performance-regression strategy.

## Performance, Profiling, And JVM Tuning Learning Sequence

Use this sequence after Stage 21 testing strategy and quality engineering and before Stage 23 observability and production diagnostics.

Functional correctness and performance confidence require different evidence. Tests can show that code returns the right result for selected cases. Performance work asks whether the system still returns the right result under representative demand, resource limits, JVM behavior, queueing, contention, dependency latency, and measurement noise.

- [ ] Study performance engineering foundations: [Performance Engineering Foundations](docs/86-performance-engineering-foundations/README.md)
- [ ] Review latency, throughput, response time, capacity, saturation, tail latency, percentiles, baselines, performance budgets, and safe optimization workflow: [Core Performance Vocabulary](docs/86-performance-engineering-foundations/core-performance-vocabulary.md)
- [ ] Study JVM runtime behavior and memory: [JVM Runtime And Memory](docs/87-jvm-runtime-and-memory/README.md)
- [ ] Review bytecode execution, interpreter, JIT, tiered compilation, warm-up, dead-code elimination, object allocation, heap, stacks, metaspace, direct memory, reachability, and GC concepts: [Execution, JIT, And Warm-Up](docs/87-jvm-runtime-and-memory/execution-jit-warm-up.md)
- [ ] Study profiling and diagnostics: [Profiling And Diagnostics](docs/88-profiling-and-diagnostics/README.md)
- [ ] Compare sampling, instrumentation, CPU, wall-clock, allocation, memory, lock, thread, heap dump, thread dump, JFR, JMC, `jcmd`, `jstack`, `jmap`, and `jstat` concepts: [Java Diagnostic Evidence](docs/88-profiling-and-diagnostics/java-diagnostic-evidence.md)
- [ ] Study benchmarking and performance testing: [Benchmarking And Performance Testing](docs/89-benchmarking-and-performance-testing/README.md)
- [ ] Review microbenchmark, JMH concepts, load, stress, spike, soak, capacity, coordinated omission, workload models, warm-up, measurement, and result analysis: [Benchmark Types And JMH Concepts](docs/89-benchmarking-and-performance-testing/benchmark-types-and-jmh-concepts.md)
- [ ] Run and modify Java performance examples: [Java Performance Patterns](examples/performance/java-performance-patterns/README.md)
- [ ] Build and explain the JVM performance lab: [JVM Performance Lab](projects/performance/jvm-performance-lab/README.md)
- [ ] Study JVM tuning and capacity planning: [JVM Tuning And Capacity Planning](docs/90-jvm-tuning-and-capacity-planning/README.md)
- [ ] Review collector tradeoffs, heap sizing, native-memory headroom, thread-count impact, capacity models, backpressure, performance budgets, and regression gates: [Capacity Planning And Regression Gates](docs/90-jvm-tuning-and-capacity-planning/capacity-planning-and-regression-gates.md)
- [ ] Complete performance exercises: [Performance Profiling And JVM Tuning Exercises](exercises/performance/performance-profiling-and-jvm-tuning-exercises.md)
- [ ] Check understanding with performance quizzes: [Performance Profiling And JVM Tuning Quizzes](quizzes/performance/README.md)
- [ ] Review performance solutions after attempting the work: [Performance Profiling And JVM Tuning Solutions](solutions/performance/performance-profiling-and-jvm-tuning-solutions.md)

## Ready For Observability And Production Diagnostics Checklist

Move into Stage 23 observability and production diagnostics when you can:

- [ ] Define performance requirements with workload, target, statistic, and environment.
- [ ] Explain latency, throughput, utilization, saturation, capacity, and tail latency without treating one metric as universally best.
- [ ] Interpret p50, p95, p99, maximum, and error rate together.
- [ ] Distinguish profiling from monitoring and CPU time from wall-clock time.
- [ ] Explain JVM warm-up, JIT optimization effects, dead-code elimination, and constant-folding benchmark risks.
- [ ] Distinguish allocation rate from retained memory and heap memory from non-heap memory.
- [ ] Choose diagnostic evidence for CPU spikes, lock contention, slow requests, memory leaks, and long GC pauses.
- [ ] Design benchmarks and load tests without fixed wall-clock unit-test thresholds.
- [ ] Explain JVM tuning as measured experimentation with rollback planning.
- [ ] Explain what remains out of scope until Stage 23: deeper observability signal design, production diagnostic workflows, incident evidence correlation, dashboards, alert review, and production troubleshooting practice.

## Observability And Production Diagnostics Learning Sequence

Use this sequence after Stage 22 performance, profiling, and JVM tuning and before Stage 24 reliability engineering, resilience, and failure recovery.

Stage 21 established testing and quality engineering. Stage 22 established performance, profiling, and JVM tuning. Stage 23 establishes observability and production diagnostics. Testing provides controlled evidence before release. Performance engineering measures efficiency and capacity. Observability provides runtime evidence about system behavior. Production diagnostics uses that evidence to investigate and mitigate failures.

- [ ] Study observability foundations: [Observability Foundations](docs/91-observability-foundations/README.md)
- [ ] Review vocabulary, telemetry models, diagnostic context, cardinality, golden signals, RED, USE, and evidence-first investigation: [Observability Vocabulary And Models](docs/91-observability-foundations/observability-vocabulary-and-models.md)
- [ ] Study structured logging and event design: [Logging And Structured Events](docs/92-logging-and-structured-events/README.md)
- [ ] Review safe fields, severity, exception logging, MDC-style context, redaction, sampling, and duplicate logging: [Safe Logging And Operational Practices](docs/92-logging-and-structured-events/safe-logging-and-operational-practices.md)
- [ ] Study metrics and service health: [Metrics And Service Health](docs/93-metrics-and-service-health/README.md)
- [ ] Review counters, gauges, histograms, cardinality, JVM metrics, dependency metrics, liveness, readiness, startup health, and dashboards: [Service Health And Dashboard Design](docs/93-metrics-and-service-health/service-health-and-dashboard-design.md)
- [ ] Study tracing and request correlation: [Tracing And Request Correlation](docs/94-tracing-and-request-correlation/README.md)
- [ ] Review spans, parent-child relationships, asynchronous context propagation, sampling, trace-log correlation, and context-loss diagnosis: [Context Propagation And Asynchronous Work](docs/94-tracing-and-request-correlation/context-propagation-and-asynchronous-work.md)
- [ ] Run and modify Java observability examples: [Java Observability Patterns](examples/observability/java-observability-patterns/README.md)
- [ ] Build and explain the production diagnostics lab: [Production Diagnostics Lab](projects/observability/production-diagnostics-lab/README.md)
- [ ] Study alerting, SLOs, and incident diagnostics: [Alerting, SLOs, And Incident Diagnostics](docs/95-alerting-slos-and-incident-diagnostics/README.md)
- [ ] Review actionable alerts, alert fatigue, SLIs, SLOs, error budgets, burn rates, evidence preservation, dumps, JFR, rollback decisions, and post-incident learning: [Incident Diagnostics And Learning](docs/95-alerting-slos-and-incident-diagnostics/incident-diagnostics-and-learning.md)
- [ ] Complete observability exercises: [Observability And Production Diagnostics Exercises](exercises/observability/observability-and-production-diagnostics-exercises.md)
- [ ] Check understanding with observability quizzes: [Observability Quizzes](quizzes/observability/README.md)
- [ ] Review observability solutions after attempting the work: [Observability And Production Diagnostics Solutions](solutions/observability/observability-and-production-diagnostics-solutions.md)

## Ready For Reliability Engineering, Resilience, And Failure Recovery Checklist

Move into Stage 24 reliability engineering, resilience, and failure recovery when you can:

- [ ] Distinguish observability from monitoring without treating either as automatic explanation.
- [ ] Design safe structured log events with stable fields, proportional severity, and redaction.
- [ ] Choose counters, gauges, histograms, health checks, and dashboards from operational questions.
- [ ] Explain high-cardinality risks in metrics and sensitive-data risks in logs, traces, dumps, and snapshots.
- [ ] Propagate request context across Java executor and asynchronous boundaries without leaking context between tasks.
- [ ] Reason from partial logs, metrics, traces, health reports, and bounded diagnostic snapshots while naming uncertainty.
- [ ] Review alert actionability, SLI choice, SLO windows, error-budget burn, and incident evidence preservation.
- [ ] Explain what remains out of scope until Stage 24: resilience patterns, failure recovery design, graceful degradation, chaos-style validation, and reliability engineering practice.

## Reliability Engineering, Resilience, And Failure Recovery Learning Sequence

Use this Stage 24 sequence after observability and production diagnostics. Prerequisites: you should be comfortable reading logs, metrics, health signals, traces, SLO notes, and incident evidence.

Learning objectives: classify failures, choose timeouts and deadlines, budget retries, use jittered backoff, reason about circuit breakers and bulkheads, design idempotent operations, shed load safely, plan recovery, and test failure behavior deterministically.

- [ ] Study reliability foundations: [Reliability Engineering Foundations](docs/96-reliability-engineering-foundations/README.md)
- [ ] Study deadlines, cancellation, retry, and backoff: [Timeouts Retries And Backoff](docs/97-timeouts-retries-and-backoff/README.md)
- [ ] Study circuit breakers, bulkheads, and fallbacks: [Circuit Breakers Bulkheads And Fallbacks](docs/98-circuit-breakers-bulkheads-and-fallbacks/README.md)
- [ ] Study idempotency, load shedding, and recovery: [Idempotency Load Shedding And Recovery](docs/99-idempotency-load-shedding-and-recovery/README.md)
- [ ] Run the Java resilience examples: [Java Resilience Patterns](examples/reliability/java-resilience-patterns/README.md)
- [ ] Build and test the reliable service simulator: [Reliable Service Simulator](projects/reliability/reliable-service-simulator/README.md)
- [ ] Study disaster recovery and chaos engineering: [Disaster Recovery And Chaos Engineering](docs/100-disaster-recovery-and-chaos-engineering/README.md)
- [ ] Complete the exercises and review solutions: [Reliability Exercises](exercises/reliability/README.md) and [Reliability Solutions](solutions/reliability/README.md)
- [ ] Check understanding with quizzes: [Reliability Quizzes](quizzes/reliability/README.md)

Completion criteria: explain failure classes, implement deterministic retry and breaker tests, design idempotency for duplicate requests, justify load shedding, write a recovery runbook, and describe evidence needed for a reliability claim.

Practical validation tasks: run both Maven project test suites, trace a failure scenario through diagnostics, review a retry budget, rehearse a recovery decision, and explain which failures should be retried, rejected, degraded, or recovered.

Next stage: Stage 25 — Distributed Systems Foundations.

## Stage 25 Distributed Systems Foundations Learning Sequence

Use this Stage 25 sequence after reliability engineering, resilience, and failure recovery. Stage 24 teaches how one service plans for failure and recovery; Stage 25 explains why multiple Java processes behave differently from one JVM once networks, independent clocks, stale state, and partial failure enter the design.

Learning objective: understand and demonstrate partial failure, unreliable networks, latency, message ordering, consistency models, CAP, clock problems, membership, failure detection, leader election terms, stale leaders, fencing, replication lag, partitioning, consistent hashing, split-brain risks, quorums, operational readiness, recovery, and convergence.

- [ ] Study distributed systems foundations: [Distributed Systems Foundations](docs/101-distributed-systems-foundations/README.md)
- [ ] Study consistency, time, and ordering: [Consistency Time And Ordering](docs/102-consistency-time-and-ordering/README.md)
- [ ] Study coordination, replication, and partitioning: [Coordination Replication And Partitioning](docs/103-coordination-replication-and-partitioning/README.md)
- [ ] Run and modify the Java examples: [Java Distributed Systems Examples](examples/distributed-systems/java-distributed-systems/README.md)
- [ ] Build and explain the deterministic simulator: [Distributed Cluster Simulator](projects/distributed-systems/distributed-cluster-simulator/README.md)
- [ ] Complete the exercises: [Distributed Systems Exercises](exercises/distributed-systems/README.md)
- [ ] Review solutions after attempting the work: [Distributed Systems Solutions](solutions/distributed-systems/README.md)
- [ ] Check understanding with quizzes: [Distributed Systems Quiz](quizzes/distributed-systems/README.md)

Completion criteria: explain why timeout is an unknown outcome, classify stale-read risk by business invariant, reason about simple quorum overlap, demonstrate heartbeat suspicion and recovery, show Lamport ordering limits, reject stale leadership terms, explain fencing-token checks, demonstrate replication lag and catch-up, compare modulo hashing with consistent hashing, and describe operational signals for partitions, leader changes, quorum health, and recovery.

Recommended learning order:

1. Distributed systems foundations
2. Consistency, time, and ordering
3. Coordination, replication, and partitioning
4. Java examples
5. Distributed cluster simulator
6. Exercises
7. Solutions
8. Quiz
9. Stage review and completion checks

Next planned stage: Stage 26 — Event-Driven Architecture Foundations.
