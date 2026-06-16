# Canary Traffic Splitting Flow

This flow shows a conceptual canary for `notification-service`.

```text
task-api sidecar
  -> 95 percent notification-service v1
  -> 5 percent notification-service v2
```

## Review Signals

- Error rate for v1 and v2.
- Latency percentiles by version.
- Retry volume by version.
- Application logs for formatting or business behavior.
- Rollback trigger if v2 behaves poorly.

## Safe Practice

Canary traffic should be gradual, observable, and reversible. A successful canary needs both technical signals and business correctness checks.

