# Spring Boot Introduction

Spring Boot helps Java developers build backend applications with less setup code. It is commonly used for HTTP APIs, service applications, scheduled jobs, and database-backed systems.

This section is documentation only. Build the first Spring Boot project after you understand the concepts here.

## Spring Vs Spring Boot

Spring is a large ecosystem for building Java applications. It provides dependency injection, web APIs, data access, testing support, and many integrations.

Spring Boot sits on top of Spring and makes common application setup easier. It gives you starter dependencies, auto-configuration, an embedded server, and production-friendly defaults.

## What A Spring Boot API Usually Contains

- Main application class: starts the app.
- Controller: exposes HTTP endpoints.
- DTOs: describe request and response data.
- Service: contains business logic.
- Repository: talks to storage.
- Configuration: stores settings outside hard-coded Java logic.
- Tests: verify behavior at different layers.

## Maven Dependencies

Spring Boot projects commonly use starter dependencies. A starter groups related libraries so you do not choose every dependency manually.

Examples:

- `spring-boot-starter-web`: REST controllers, JSON, and embedded web server.
- `spring-boot-starter-validation`: request validation annotations.
- `spring-boot-starter-test`: testing support.

## Beginner Learning Order

1. Understand HTTP and REST basics.
2. Read the main Spring Boot concepts: [Spring Boot Core Concepts](spring-boot-core-concepts.md)
3. Learn common annotations: [Common Annotations](common-annotations.md)
4. Review backend layering: [Backend Layering](backend-layering.md)
5. Build a small in-memory REST API before adding a real database.

## What Not To Worry About Yet

- Authentication and authorization
- Docker and deployment
- Cloud configuration
- Real database migrations
- Message queues
- Microservices

Those topics matter later. First, learn how a request moves through controller, service, repository, and response DTO code.
