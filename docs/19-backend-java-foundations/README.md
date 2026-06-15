# Backend Java Foundations

Backend Java connects core Java skills to server-side applications. A backend program accepts requests, applies business rules, talks to data sources when needed, and returns useful responses.

You are ready for this section when you can separate model, service, and input/output code in a small Java project.

## What Backend Developers Build

- HTTP APIs used by web, mobile, and desktop clients.
- Services that validate input and apply business rules.
- Integrations with databases, queues, files, or other services.
- Error responses that help clients recover.
- Tests that protect important behavior.

## Client And Server

A client sends a request. A server receives it, decides what should happen, and sends a response.

Examples of clients:

- Browser pages
- Mobile apps
- Command-line tools
- Other backend services

Examples of backend responsibilities:

- Create a task
- Find a product
- Update a user profile
- Reject invalid input
- Return a helpful error when something cannot be found

## Backend Layers

Most beginner backend projects become easier to understand when separated into layers:

- Controller: handles HTTP details.
- DTO: describes request and response shapes.
- Service: owns business rules.
- Repository: hides storage details.
- Model/entity: represents domain data inside the application.

Do not worry about making the layers perfect yet. The first goal is to avoid putting every decision in one class.

## Java Skills That Transfer

- Classes and records describe models and DTOs.
- Collections support in-memory stores.
- Exceptions and validation handle invalid states.
- Interfaces make storage and service boundaries easier to test.
- Maven manages dependencies and test runs.
- JUnit checks service behavior before a real server is involved.

## Learning Path

Use this section before building a full Spring Boot project:

- [ ] Understand client/server flow.
- [ ] Review HTTP and REST basics: [HTTP REST API Basics](../20-http-rest-api-basics/README.md)
- [ ] Learn Spring Boot concepts: [Spring Boot Introduction](../21-spring-boot-introduction/README.md)
- [ ] Run backend design examples: [Backend API Design Basics](../../examples/backend/api-design-basics/README.md)
- [ ] Build the simple REST API skeleton: [Simple REST API Project](../../projects/backend/simple-rest-api/README.md)
- [ ] Add persistence after the first API works: [Database And SQL Foundations](../22-database-sql-foundations/README.md)
- [ ] Add security after persistence is clear: [Backend Security Foundations](../25-backend-security-foundations/README.md)
- [ ] Add production-readiness habits after security is clear: [Production Readiness Foundations](../28-production-readiness-foundations/README.md)
- [ ] Add deployment/cloud readiness after production-readiness habits are clear: [Deployment Foundations](../33-deployment-foundations/README.md)
- [ ] Add Kubernetes/container orchestration after deployment/cloud readiness is clear: [Container Orchestration Foundations](../38-container-orchestration-foundations/README.md)
- [ ] Add Helm packaging after raw Kubernetes manifests are clear: [Helm Packaging Foundations](../42-helm-packaging-foundations/README.md)
- [ ] Add infrastructure as code after Helm packaging and local-only render/review habits are clear: [Infrastructure As Code Foundations](../46-infrastructure-as-code-foundations/README.md)
- [ ] Add cloud architecture foundations after IaC state, review, and local-only safety are clear: [Cloud Architecture Foundations](../51-cloud-architecture-foundations/README.md)

Before moving on, you should be able to describe what happens between an HTTP request and an HTTP response. Before adding authentication, make sure persistence, validation, DTO mapping, and error handling are already understandable. Before real deployment work, make sure configuration, logging, health checks, CI basics, release versioning, and rollback basics are also clear. Before Kubernetes, make sure containers, runtime configuration, health checks, smoke tests, and local-only safety boundaries are clear. Before Helm, make sure raw Kubernetes objects, labels, selectors, probes, resources, and rendered manifests are understandable. Before IaC, make sure Helm values, local-only examples, state/secrets safety, and review-before-apply habits are clear. Before cloud architecture, make sure IaC review, state safety, provider-neutral thinking, and cost/reliability tradeoffs are understandable.
