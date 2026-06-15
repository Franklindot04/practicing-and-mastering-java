# Scalable Backend Architecture

```text
Client -> DNS -> Load Balancer -> Backend Service Instances -> Managed Database
```

## What It Shows

Multiple backend instances can receive traffic through a load balancer. This works best when the backend is stateless and shared data lives outside individual instances.

## Tradeoffs

- Better capacity than one instance.
- Health checks can remove unhealthy instances.
- More instances can create more database connections.
- Background jobs must not accidentally run once per instance unless intended.

## Intentionally Simplified

This sketch does not include autoscaling rules, network segmentation, deployment strategy, cache, queue, or provider-specific managed services.

