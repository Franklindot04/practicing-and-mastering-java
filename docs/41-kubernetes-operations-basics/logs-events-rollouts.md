# Logs, Events, And Rollouts

## Logs

Application logs explain what the containerized process did after it started. For Java backends, logs should usually go to standard output so Kubernetes can collect them.

Useful command shape:

```bash
kubectl logs deployment/task-api -n learnjava-kubernetes
```

## Events

Events explain Kubernetes-side activity such as scheduling, image pulls, probe failures, and restarts.

Useful command shape:

```bash
kubectl get events -n learnjava-kubernetes --sort-by=.lastTimestamp
```

## Rollout Status

Rollout status helps you see whether a Deployment update completed.

```bash
kubectl rollout status deployment/task-api -n learnjava-kubernetes
```

## Rollout Undo Concept

Rollback can return a Deployment to a previous revision:

```bash
kubectl rollout undo deployment/task-api -n learnjava-kubernetes
```

This is a concept for local learning here. Production rollback needs release notes, monitoring, database planning, and incident communication.
