# Scaling And Cleanup Basics

## Scaling Deployments

Scaling a Deployment changes the desired replica count.

Command shape:

```bash
kubectl scale deployment/task-api --replicas=3 -n learnjava-kubernetes
```

Scaling up is not just a number change. The cluster must have enough resources, and the application must handle multiple copies safely.

## Restarting Deployments Concept

A rollout restart asks Kubernetes to replace the Pods for a Deployment.

Command shape:

```bash
kubectl rollout restart deployment/task-api -n learnjava-kubernetes
```

This can help after configuration changes, but it should not hide unknown failures.

## Safe Cleanup Commands

For local learning resources, deleting the namespace is often the cleanest cleanup:

```bash
kubectl delete namespace learnjava-kubernetes
```

Only use cleanup commands against resources you created for local learning.

## Why Production Operations Need More Depth

Production Kubernetes operations also involve:

- Monitoring and alerting
- Capacity planning
- Network policy
- Secret management
- Upgrade strategy
- Incident response
- Backup and recovery
- Security controls

Those topics come later. This stage focuses on beginner troubleshooting vocabulary and safe local practice.
