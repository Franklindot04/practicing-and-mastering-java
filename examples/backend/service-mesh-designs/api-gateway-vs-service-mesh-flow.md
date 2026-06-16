# API Gateway Vs Service Mesh Flow

This flow distinguishes entry traffic from internal traffic.

```text
external client
  -> api gateway
  -> task-api
  -> service mesh managed internal call
  -> notification-service
```

## Gateway Focus

- Public routes.
- Client authentication at the edge.
- External rate limits.
- Public API shape.

## Mesh Focus

- Internal service identity.
- Service-to-service telemetry.
- Internal mTLS.
- Traffic splitting and reliability policy.

## Design Habit

Start by naming the traffic direction. North-south traffic usually points to gateway concerns. East-west traffic usually points to mesh concerns.

