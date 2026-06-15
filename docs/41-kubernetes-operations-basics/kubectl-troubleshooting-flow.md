# kubectl Troubleshooting Flow

`kubectl` is the standard command-line tool for interacting with Kubernetes. This page explains a beginner troubleshooting flow conceptually.

## Basic Flow

1. List resources in the namespace.
2. Check Pod status.
3. Describe the failing object.
4. Read logs for the relevant container.
5. Check events.
6. Compare labels and selectors.
7. Review config and secret references.
8. Review probes and resource settings.

## Useful Command Shapes

```bash
kubectl get pods -n learnjava-kubernetes
kubectl describe pod <pod-name> -n learnjava-kubernetes
kubectl logs <pod-name> -n learnjava-kubernetes
kubectl get events -n learnjava-kubernetes --sort-by=.lastTimestamp
```

Run commands only in a disposable local learning cluster while practicing.

## What `describe` Helps With

`kubectl describe` can show:

- Events attached to an object
- Image pull problems
- Probe failures
- Scheduling problems
- Volume or config reference problems

## Beginner Habit

Do not jump straight to deleting everything. First learn what Kubernetes is reporting.
