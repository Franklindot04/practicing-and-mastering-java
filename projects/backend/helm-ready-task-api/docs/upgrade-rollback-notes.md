# Upgrade And Rollback Notes

Helm can upgrade and roll back releases, but this repository does not run those commands against a cluster.

## Safe Local Review

Use render-only checks:

```bash
helm lint projects/backend/helm-ready-task-api/chart
helm template demo-task-api projects/backend/helm-ready-task-api/chart --values projects/backend/helm-ready-task-api/chart/values-local.yaml
```

## Upgrade Thinking

Before an upgrade, compare rendered manifests and review image tag, config values, probe changes, and resource changes.

## Rollback Thinking

Rollback may help with a bad chart or application version, but it does not automatically undo database changes, external side effects, or unsafe secret handling.
