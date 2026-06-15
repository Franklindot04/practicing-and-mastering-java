# Mesh Observability Basics

Mesh observability focuses on traffic between services. The mesh can often report request rate, latency, errors, source service, destination service, route, and policy decisions.

## Why It Helps

In distributed systems, a user-facing failure may be caused by an internal dependency. Mesh telemetry can help teams see which service called which dependency and where latency or errors appeared.

## Typical Signals

- Request count and rate.
- Error count and rate.
- Latency percentiles.
- Source and destination workload labels.
- Response codes.
- Retry and timeout behavior.
- mTLS status.

## What It Does Not Replace

Mesh telemetry does not replace application logs, domain metrics, database monitoring, or business-level alerts. A mesh can show that `task-api` called `notification-service`, but it cannot explain whether the task reminder text was correct.

## Good Practice

Use mesh telemetry to connect infrastructure behavior to application behavior. During incidents, compare service traces, application logs, dependency metrics, and deployment changes.

