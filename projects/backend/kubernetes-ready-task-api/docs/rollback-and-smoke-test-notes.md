# Rollback And Smoke-Test Notes

Kubernetes can roll a Deployment forward or back, but a safe release still needs human-readable checks.

## Smoke Tests

After a local learning rollout, inspect:

- Pod status
- Deployment rollout status
- Service selector and endpoints
- `/actuator/health`
- `/api/version`
- `/api/smoke`

These endpoints come from the deployment-readiness project concept and help learners connect application behavior to platform checks.

## Rollback Concept

A rollback returns a Deployment to a previous revision. It can help when the new version fails probes or obvious smoke tests.

Rollback does not automatically undo database changes, external side effects, or broken configuration stored elsewhere.

## Local Learning Boundary

Do not run these examples against shared or production clusters. Use a disposable local namespace if you choose to experiment.
