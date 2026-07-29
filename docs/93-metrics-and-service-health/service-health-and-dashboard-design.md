# Service Health And Dashboard Design

Health checks summarize whether a service should receive work, keep running, or report degraded behavior. They should be fast, bounded, and safe.

## Health States

| State | Meaning |
| --- | --- |
| UP | The component can serve its intended role. |
| DEGRADED | The component can serve partially or with reduced quality. |
| DOWN | The component cannot serve its intended role. |

## Liveness, Readiness, And Startup

Liveness answers "should this process be restarted?" Readiness answers "should this instance receive traffic?" Startup health answers "has initialization completed?"

Shallow checks verify the process and critical local state. Deep checks may call dependencies, but they need timeouts and should avoid side effects. A health endpoint that writes to a database just to prove it is alive can create load and incidents of its own.

## Fictional Java Service Health Model

```text
CheckoutService health
  process: UP
  databasePool: UP
  paymentGateway: DEGRADED
  workerQueue: DEGRADED
  overall: DEGRADED
```

The model says the service should not be called fully healthy, but it may still accept limited traffic or serve non-payment paths. Partial availability is more honest than a single always-green status.

## Dashboard Design

Start from operational questions:

- Are users affected?
- Which operations are affected?
- Is the service saturated?
- Did dependency behavior change?
- Did a deployment or configuration change happen nearby?
- What evidence should guide mitigation?

Avoid dashboards that show every metric without a story. Useful dashboards compare traffic, errors, duration, saturation, dependency behavior, and version or deployment markers.

## Health Anti-Patterns

- Health checks with no timeout.
- Health checks that mutate data.
- Returning UP while every dependency path is failing.
- Exposing sensitive version, build, host, or environment details publicly.
- Using health checks as complete observability.
- Alerting on a deep dependency check before user-visible symptoms are understood.

