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

Before moving on, you should be able to describe what happens between an HTTP request and an HTTP response.
