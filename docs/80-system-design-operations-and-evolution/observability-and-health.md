# Observability And Health

Observability is the ability to understand what a system is doing from its external signals. Design should include those signals from the beginning.

## Metrics

Metrics are numeric signals over time.

Examples:

- Request count.
- Error count.
- Latency percentiles.
- Queue depth.
- Consumer lag.
- Saturation.
- Retry count.
- Circuit breaker state.

Metrics answer "how much" and "how often."

## Logs

Logs describe discrete events.

Useful logs include:

- Correlation identifier.
- Safe actor or tenant reference.
- Operation name.
- Outcome.
- Failure category.

Avoid logging secrets, payment details, raw tokens, or sensitive payloads.

## Traces

Traces connect work across boundaries.

```text
HTTP request -> Order service -> Inventory boundary -> Payment boundary
```

Traces help teams see where latency and failure occur in distributed workflows.

## Health Checks

Health checks report whether a component can serve traffic.

Liveness concept:

- Is the process alive enough that restarting may help?

Readiness concept:

- Is the instance ready to receive traffic?

Health checks should avoid expensive work that creates its own outage.

## Capacity Monitoring

Capacity monitoring watches the resources that saturate first.

Examples:

- CPU.
- Memory.
- Database connections.
- Worker threads.
- Queue backlog.
- Disk space.
- External dependency latency.

Saturation usually appears before total failure. Good design makes saturation visible.
