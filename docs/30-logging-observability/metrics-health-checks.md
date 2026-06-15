# Metrics And Health Checks

Metrics and health checks give a running application a basic voice. They help answer whether the app is alive, whether it is ready, and how it is behaving over time.

## Metrics Basics

Metrics are numbers measured over time.

Examples:

- Request count.
- Error count.
- Response time.
- Active database connections.
- Task creation count.
- JVM memory usage.

Metrics are useful because they can be graphed and alerted on. A single log line says what happened once. A metric can show a trend.

## Health Checks

A health check is an endpoint or command that reports application status.

Simple example response:

```json
{
  "status": "UP"
}
```

Health checks should be fast and safe. They should not modify data.

## Liveness Versus Readiness

Liveness asks: "Is the application process alive?"

Readiness asks: "Is the application ready to receive traffic?"

An app might be alive but not ready if it cannot connect to a required dependency. In small learning projects, one health endpoint is enough to introduce the concept.

## Spring Boot Actuator

Spring Boot Actuator can expose health and operational endpoints. For beginner projects, start with health only.

Do not expose every actuator endpoint publicly. Some endpoints can reveal sensitive operational details.

## Common Mistakes

- Health checks that perform expensive work.
- Health checks that always return `UP` even when required dependencies fail.
- Publicly exposing detailed internal health data.
- Confusing "the process is running" with "the app can serve real requests."
- Adding metrics without deciding what question each metric answers.
