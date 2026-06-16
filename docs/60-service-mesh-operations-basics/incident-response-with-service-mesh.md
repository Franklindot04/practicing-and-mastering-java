# Incident Response With Service Mesh

During incidents, a service mesh can provide useful traffic signals, but it also adds another layer to reason about.

## Incident Questions

- What changed recently: application, policy, proxy, certificate, deployment, or dependency?
- Which services show elevated errors?
- Are retries increasing load?
- Are timeouts protecting callers or causing early failure?
- Is traffic going to the expected version?
- Are mTLS or authorization policies rejecting calls?

## Useful Signals

- Request rate by source and destination.
- Error rate by route and version.
- Latency percentiles.
- Retry and timeout counts.
- Proxy resource usage.
- Application logs with trace IDs.

## Response Pattern

1. Stabilize user impact.
2. Stop risky rollouts.
3. Reduce traffic to bad versions or dependencies if safe.
4. Compare mesh telemetry with application and infrastructure signals.
5. Record what changed, what worked, and what needs a follow-up fix.

## Post-Incident Review

Ask whether the mesh made the incident easier to detect and resolve. If it made things harder, improve ownership, dashboards, policy review, or rollback practice before adding more mesh features.

