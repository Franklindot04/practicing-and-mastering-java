# Rolling Updates Basics

A rolling update replaces old Pods with new Pods gradually. Deployments support rolling updates by default.

## Why Rolling Updates Matter

Rolling updates reduce downtime risk by avoiding a full stop-and-start replacement of all replicas at once.

In a simple backend API, a rolling update might:

1. Create a new Pod using the new image
2. Wait for readiness checks to pass
3. Route traffic to the new Pod
4. Remove an old Pod
5. Repeat until all Pods are updated

## Rollback Concept

Kubernetes can roll a Deployment back to a previous revision. This is useful when a new version fails health checks or causes obvious runtime issues.

Rollback is not a substitute for testing, release notes, database migration planning, or monitoring.

## Common Update Mistakes

- Updating an image tag without changing the actual image
- Using `latest` and losing track of what version is running
- Shipping a new config shape without updating the app
- Ignoring readiness probes during rollout
- Assuming rollback fixes database or external system changes

## Local-Only Reminder

The examples in this stage are meant to explain the mechanics. Production rollout strategy requires deeper planning, monitoring, and incident response practice.
