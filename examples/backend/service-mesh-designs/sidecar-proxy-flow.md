# Sidecar Proxy Flow

This flow adds sidecar proxies around the internal services.

```text
task-api
  -> task-api sidecar
  -> network
  -> notification-service sidecar
  -> notification-service
```

## What The Sidecars Can Add

- Internal request metrics.
- Trace propagation.
- mTLS between workloads.
- Route policy.
- Timeout and retry policy.

## What The Services Still Own

- Request validation.
- User authentication and authorization.
- Domain decisions.
- Database transactions.
- Business error responses.

