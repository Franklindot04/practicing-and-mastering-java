# Service Mesh Readiness Checklist

Use this checklist before proposing a service mesh for the task API.

## Application Readiness

- [ ] Service boundaries are clear.
- [ ] Public API behavior is documented.
- [ ] Internal dependencies are named.
- [ ] Timeout behavior is known.
- [ ] Retry behavior is deliberate.
- [ ] Health and readiness signals exist.
- [ ] Logs avoid secrets and sensitive data.
- [ ] Authentication and authorization are handled in application code.

## Platform Readiness

- [ ] Deployment and rollback steps are documented.
- [ ] Kubernetes workload concepts are understood.
- [ ] Helm values and rendered manifests can be reviewed safely.
- [ ] IaC planning separates real secrets from committed examples.
- [ ] Cloud architecture tradeoffs are understood.
- [ ] Observability has owners.
- [ ] Incident response has a basic runbook.

## Mesh Readiness

- [ ] The team can explain why mesh policy is needed.
- [ ] The team can monitor proxy and control-plane health.
- [ ] mTLS identity boundaries are understood.
- [ ] Traffic policy changes have review and rollback paths.

