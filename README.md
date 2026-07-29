# Practicing and Mastering Java

A progressive Java learning, practicing, and mastery repository for learners who want a structured path from first programs to professional Java development.

This repository is built progressively. Each branch should add a focused, reviewable part of the learning system instead of one large drop of content.

## Who This Is For

- Beginners learning Java for the first time.
- Developers revisiting Java fundamentals.
- Students preparing for interviews.
- Builders who want portfolio-quality Java projects.
- Anyone who wants notes, examples, exercises, quizzes, and projects in one place.

## Java Version

The primary target is Java 21 LTS. Java 17 is also acceptable for many examples because it is still widely used in companies.

## How To Use This Repository

1. Read `LEARNING_PATH.md` to understand the recommended order.
2. Use `ROADMAP.md` to see the big picture.
3. Study a topic note in `docs/`.
4. Run matching examples from `examples/`.
5. Solve exercises from `exercises/`.
6. Compare your thinking with `solutions/` when available.
7. Build projects from `projects/`.
8. Track progress in `PROGRESS.md`.

New learners should start with the [beginner learning sequence](LEARNING_PATH.md#beginner-learning-sequence), which connects setup, notes, examples, exercises, projects, quizzes, and solutions in order.

After the beginner path, continue with the [intermediate learning sequence](LEARNING_PATH.md#intermediate-learning-sequence) for generics, exceptions, files, dates, Maven, testing, lambdas, streams, and Optional.

When intermediate topics feel comfortable, use the [advanced learning sequence](LEARNING_PATH.md#advanced-learning-sequence) for concurrency, JVM memory, performance, design patterns, and clean architecture.

After advanced Java, follow the [professional Java learning sequence](LEARNING_PATH.md#professional-java-learning-sequence) before moving into backend frameworks.

After professional Java, use the [backend Java learning sequence](LEARNING_PATH.md#backend-java-learning-sequence) for HTTP, REST, Spring Boot basics, and a simple API skeleton.

After the first backend API, follow the [backend persistence learning sequence](LEARNING_PATH.md#backend-persistence-learning-sequence) for SQL, JPA, Spring Data, and database-backed APIs.

After persistence, continue with the [backend security and authentication learning sequence](LEARNING_PATH.md#backend-security-and-authentication-learning-sequence) for API security risks, authentication, authorization, Spring Security basics, and a secured API skeleton.

After security foundations, use the [production readiness learning sequence](LEARNING_PATH.md#production-readiness-learning-sequence) for configuration, profiles, logging, observability, Docker basics, CI/CD, and deployment-readiness habits.

After production readiness, follow the [deployment and cloud readiness learning sequence](LEARNING_PATH.md#deployment-and-cloud-readiness-learning-sequence) for deployment basics, release/rollback planning, runbooks, platform concepts, and cloud-readiness foundations.

After deployment/cloud readiness, use the [Kubernetes and container orchestration learning sequence](LEARNING_PATH.md#kubernetes-and-container-orchestration-learning-sequence) for orchestration basics, Kubernetes objects, local-only manifests, probes, resources, and beginner operations.

After Kubernetes foundations, follow the [Helm and Kubernetes packaging learning sequence](LEARNING_PATH.md#helm-and-kubernetes-packaging-learning-sequence) for chart basics, values, render-only chart examples, upgrades, rollbacks, and packaging practice.

After Helm packaging, use the [Infrastructure as Code learning sequence](LEARNING_PATH.md#infrastructure-as-code-learning-sequence) for IaC concepts, Terraform/OpenTofu basics, state and secrets safety, modules, local-only examples, and plan-review habits.

After IaC foundations, follow the [cloud architecture learning sequence](LEARNING_PATH.md#cloud-architecture-learning-sequence) for availability, scalability, reliability, traffic/data patterns, cost-aware review, and operations planning.

After cloud architecture, use the [service mesh learning sequence](LEARNING_PATH.md#service-mesh-learning-sequence) for service mesh concepts, sidecar communication, traffic policy, observability, security boundaries, operations, and readiness planning.

After service mesh foundations, use the [distributed systems learning sequence](LEARNING_PATH.md#distributed-systems-learning-sequence) for distributed system concepts, CAP, consistency, idempotency, retries, sagas, discovery, coordination, operations, and readiness planning.

After distributed systems foundations, use the [event-driven architecture learning sequence](LEARNING_PATH.md#event-driven-architecture-learning-sequence) for events, commands, asynchronous communication, event design, event patterns, outbox, CQRS, operations, exercises, quizzes, examples, and readiness planning.

After event-driven architecture foundations, use the [messaging and streaming with Java learning sequence](LEARNING_PATH.md#messaging-and-streaming-with-java-learning-sequence) for queues, topics, delivery guarantees, Kafka, RabbitMQ, Java messaging boundaries, testing, observability, operations, exercises, quizzes, examples, and readiness planning before advanced backend system design.

After advanced backend system design, use the [testing strategy and quality engineering learning sequence](LEARNING_PATH.md#testing-strategy-and-quality-engineering-learning-sequence) to turn design assumptions into practical verification, observability, quality gates, exercises, quizzes, examples, and release-confidence thinking.

After testing strategy and quality engineering, use the [performance, profiling, and JVM tuning learning sequence](LEARNING_PATH.md#performance-profiling-and-jvm-tuning-learning-sequence) to separate functional correctness evidence from performance evidence, measurement, profiling, benchmarking, capacity planning, and safe JVM tuning.

## Folder Structure

```text
docs/           Concept notes from beginner to advanced Java
examples/       Runnable Java examples
exercises/      Practice prompts and drills
solutions/      Suggested solutions and explanations
projects/       Portfolio-oriented Java projects
quizzes/        Topic quizzes and review questions
interview-prep/ Interview notes and coding challenges
templates/      Reusable content templates
```

## Running Examples

Compile and run a simple Java file:

```bash
javac examples/beginner/HelloWorld.java
java -cp examples/beginner HelloWorld
```

For Maven-based code:

```bash
mvn test
mvn package
```

## Suggested Study Routine

- Study one concept note.
- Type the example code yourself.
- Change the example and predict the output.
- Complete two or three exercises.
- Write a short note explaining what was confusing.
- Revisit the topic after a few days.

## Project Roadmap

The repository grows in focused branches:

- Foundation and contribution files.
- Beginner notes, examples, and exercises.
- Object-oriented programming.
- Collections and generics.
- Exceptions, files, dates, Maven, and testing.
- Lambdas, streams, and Optional.
- Concurrency, JVM, performance, and advanced Java.
- Interview preparation and portfolio projects.
- Backend Java, HTTP APIs, and Spring Boot foundations.
- Persistence, SQL, JPA, and database-backed backend projects.
- Security, authentication, authorization, and protected backend APIs.
- Production readiness, configuration, observability, Docker basics, and CI/CD foundations.
- Deployment, release/rollback, platform, and cloud-readiness foundations.
- Kubernetes, container orchestration, local-only manifests, and beginner operations foundations.
- Helm packaging, chart values, render-only chart examples, and Kubernetes packaging foundations.
- Infrastructure as Code, Terraform/OpenTofu concepts, state and secrets safety, local-only examples, modules, environments, and operations foundations.
- Advanced cloud architecture foundations, availability, scalability, reliability, traffic/data patterns, cost-aware review, and operations planning.
- Service mesh foundations, sidecar communication, traffic policy, observability, mTLS identity, operations, and readiness planning.
- Distributed systems foundations, CAP theorem, consistency models, idempotency, retries, sagas, service discovery, coordination, operations, and readiness planning.
- Event-driven architecture foundations, event design, event patterns, outbox, CQRS, operations, examples, exercises, quizzes, and readiness planning.
- Messaging and streaming with Java foundations, delivery reliability, Kafka, RabbitMQ, Java messaging patterns, messaging-ready service design, operations, exercises, quizzes, and readiness planning before advanced backend system design and advanced production infrastructure topics.
- Advanced backend system design foundations, scalability, reliability, data design, architecture patterns, Java design pattern examples, scalable order platform case study, operations, evolution, exercises, quizzes, and readiness planning before testing strategy and quality engineering.
- Testing strategy and quality engineering foundations, test design, isolation, integration, contract and end-to-end testing, quality gates, Java testing examples, a testing strategy lab, exercises, quizzes, and readiness planning before performance, profiling, and JVM tuning.
- Performance engineering foundations, JVM runtime and memory, profiling and diagnostics, benchmarking, Java performance patterns, a JVM performance lab, tuning, capacity planning, exercises, quizzes, and readiness planning before observability and production diagnostics.

## Disclaimer

This is a learning and mastery repository. Some examples may intentionally simplify real-world concerns. Security-sensitive or intentionally vulnerable examples must be clearly marked as educational.
