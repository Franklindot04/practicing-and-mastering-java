# Task API Service Mesh Flow

This is a conceptual diagram, not a deployment manifest.

```text
external client
  -> api gateway
  -> task-api sidecar
  -> task-api
  -> task-api sidecar
  -> notification-service sidecar
  -> notification-service
```

## Reading The Flow

- The gateway handles entry traffic.
- The sidecars manage conceptual internal traffic policy.
- `task-api` still owns validation, authorization, and task behavior.
- `notification-service` is a future dependency example, not required implementation.

