# Service Mesh Answer Key

## Service Mesh Basics Quiz

1. B
2. A
3. A
4. An API gateway usually handles north-south entry traffic, while a service mesh usually handles east-west internal service-to-service traffic.
5. Example answers: unclear service boundaries, no observability basics, no internal service calls, no rollout/rollback process, no ownership for mesh operations.
6. Recommend improving application health checks, logs, metrics, deployment notes, service boundaries, and API design before adding mesh complexity.

## Traffic Reliability Quiz

1. A
2. B
3. B
4. Conflicting timeouts can create confusing failures. The caller, application, and mesh need a shared deadline model.
5. Example answers: error rate, latency percentiles, retry volume, resource saturation, business correctness, application logs.
6. Retrying task creation may duplicate side effects if the first attempt completes. Safer options include avoiding automatic retries for non-idempotent operations or designing idempotency keys.

## Observability, Security, And Operations Quiz

1. A
2. A
3. B
4. Workload identity identifies services or workloads. User identity identifies the person or client making a business request. Application code still authorizes user actions.
5. Example answers: proxy upgrades, control-plane monitoring, certificate rotation, policy review, traffic rollback, proxy resource planning, incident debugging.
6. Stop risky rollout, identify the policy change, compare mesh telemetry with application logs, check routing, retries, timeouts, mTLS or authorization rejections, and roll back the smallest safe change if users are affected.

