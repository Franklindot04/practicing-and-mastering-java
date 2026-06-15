# Backend Projects

Backend projects turn Java design skills into HTTP APIs and service applications.

## Projects

- [Simple Spring Boot REST API](simple-rest-api/README.md): an in-memory task API with controller, service, repository, DTOs, validation, and error responses.
- [Persistent Spring Boot Task API](persistent-task-api/README.md): a task API backed by Spring Data JPA and an H2 in-memory database.
- [Secured Spring Boot Task API](secured-task-api/README.md): an educational protected task API skeleton with Spring Security, demo users, roles, validation, and layered service logic.
- [Production-Ready Task API Skeleton](production-ready-task-api/README.md): an educational production-readiness skeleton with profiles, logging, Actuator health, safe errors, security, and persistence.
- [Deployment-Ready Task API Skeleton](deployment-ready-task-api/README.md): an educational deployment-readiness skeleton with runtime metadata, deployment-demo profile, smoke-test endpoint, health, security, and persistence.

## Suggested Order

1. Review [Backend Java Foundations](../../docs/19-backend-java-foundations/README.md).
2. Study [HTTP REST API Basics](../../docs/20-http-rest-api-basics/README.md).
3. Study [Spring Boot Introduction](../../docs/21-spring-boot-introduction/README.md).
4. Run backend examples before starting the project.
5. Build the simple REST API and explain each layer.
6. Study SQL and persistence foundations.
7. Build the persistent task API and explain entity, repository, service, and DTO boundaries.
8. Study backend security, authentication, authorization, and Spring Security fundamentals.
9. Build the secured task API and explain which routes are public, which routes are protected, and which checks belong in configuration or service logic.
10. Study production readiness, external configuration, profiles, logging, health checks, Docker basics, and CI/CD fundamentals.
11. Build the production-readiness skeleton and explain what is still simplified for learning.
12. Study deployment lifecycle, release/rollback basics, runbooks, platform concepts, and cloud readiness.
13. Build the deployment-ready skeleton and explain how health, version, runtime config, and smoke-test endpoints support deployment checks.

Do not add Kubernetes, real cloud resources, production database configuration, or real secrets until the deployment/cloud-readiness flow is clear. Demo credentials in learning projects are not production credentials.
