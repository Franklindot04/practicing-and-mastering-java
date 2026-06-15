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

Before moving on from each level, you should be able to explain the concepts in your own words, write a small example without copying, complete exercises, and build a mini project.

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

Move into infrastructure as code, service mesh, distributed systems, advanced cloud, or production Kubernetes topics later when you can:

- [ ] Explain what Helm adds on top of raw Kubernetes YAML.
- [ ] Identify `Chart.yaml`, `values.yaml`, `templates/`, helper templates, and rendered manifests.
- [ ] Use safe placeholder values for local-only charts.
- [ ] Explain image repository/tag, service ports, container ports, environment values, probes, and resources in a Java backend chart.
- [ ] Run `helm lint` and `helm template` without installing into a cluster.
- [ ] Explain Helm releases, upgrades, rollbacks, history, and uninstall concepts.
- [ ] Explain what still requires real production ownership: secret management, TLS, monitoring, backups, incident response, and provider-specific operations.
