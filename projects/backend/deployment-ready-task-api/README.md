# Deployment-Ready Task API Skeleton

This project is an educational Spring Boot API that combines persistence, security, configuration profiles, validation, safer errors, logging, health checks, runtime metadata, and smoke-test-friendly endpoints.

It is a deployment-readiness skeleton, not a real deployed application.

## Concepts Practiced

- Layered backend structure: controller, DTO, service, repository, entity
- Spring Data JPA with H2 for local/test learning
- Request validation and safer error responses
- Basic Spring Security configuration with demo-only users
- Local and test Spring profiles
- Externalized safe configuration values
- Spring Boot Actuator health endpoint
- Runtime profile and release metadata
- Smoke-test-friendly public endpoint
- Service/controller logging without secrets
- JUnit service tests

## How This Differs From Earlier Projects

The production-ready task API focuses on production-readiness habits. This project takes the next step by adding deployment-demo profile behavior, safe version metadata, and smoke-test-oriented endpoints.

## Run Locally

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

The local profile uses an in-memory H2 database and demo-only users.

## Test

```bash
mvn test
```

Tests use the `test` profile and an in-memory H2 database.

## Demo Users

These credentials are for local learning only:

| Username | Password | Role |
| --- | --- | --- |
| `user@example.com` | `demo-user-password` | `USER` |
| `admin@example.com` | `demo-admin-password` | `ADMIN` |

Do not use these credentials in real systems.

## Useful Endpoints

- `GET /actuator/health`: Spring Boot health check
- `GET /api/info`: safe public application information
- `GET /api/version`: safe release/build metadata
- `GET /api/smoke`: smoke-test-friendly deployment check
- `GET /api/tasks`: list tasks, requires authentication
- `POST /api/tasks`: create a task, requires authentication
- `PATCH /api/tasks/{id}`: update a task, requires authentication
- `GET /api/admin/readiness-notes`: demo admin-only route

Example:

```bash
curl -i http://localhost:8080/actuator/health
curl -i -u user@example.com:demo-user-password http://localhost:8080/api/tasks
```

## Profiles

- `local`: local learning profile with H2 console enabled and safe demo values.
- `test`: test profile with isolated in-memory H2 database and quieter SQL logging.
- `deployment-demo`: local-only profile that demonstrates runtime values a platform might provide.

Configuration files in this project contain only safe learning values. Real deployment values should come from environment variables, secret managers, and platform configuration outside source control.

## Runtime Configuration

Safe runtime values can be supplied with environment variables:

```bash
APP_RELEASE_VERSION=1.0.0-demo APP_COMMIT_SHA=abc1234 mvn spring-boot:run -Dspring-boot.run.profiles=deployment-demo
```

Do not put real secrets, cloud keys, database passwords, or JWT secrets in this repository.

## Smoke Tests

A smoke test can call:

- `/actuator/health`
- `/api/info`
- `/api/version`
- `/api/smoke`

## Health Checks

This project uses Spring Boot Actuator for a basic health endpoint. It exposes only health information in the learning configuration.

Real systems usually separate liveness and readiness, check required dependencies carefully, and avoid exposing sensitive operational details publicly.

## Intentionally Simplified

- HTTP Basic is used for a small local learning API.
- Demo users are configured in memory.
- H2 is used for local/test only.
- There is no Docker in this branch.
- There is no cloud deployment or Kubernetes.
- There are no real cloud resources.
- There is no real secret management.
- There are no ownership checks between users and tasks.

## Needed For Real Production

- Real user/account storage and ownership rules.
- External database with managed credentials.
- Secret management and key rotation.
- TLS, hardened headers, and environment-specific security review.
- Centralized logging, metrics, dashboards, and alerts.
- CI/CD pipeline and deployment rollback strategy.
- Real release tracking and rollback automation.
- Backup, recovery, and incident response practices.
