# Kubernetes Objects Solutions

## Exercise 1

- Replicated Java API: Deployment
- Stable network name: Service
- Non-secret settings: ConfigMap
- Placeholder sensitive-looking values: Secret example file
- One running workload unit: Pod

Learners usually edit Deployments because Deployments manage ReplicaSets and rolling updates.

## Exercise 2

The Service selector does not match the Pods, so the Service will not route to them. Change the selector to `app: task-api` or change the Pod labels to match, keeping the choice consistent.

## Exercise 3

A namespace groups the learning resources so they are easier to list and delete together. Deleting `learnjava-kubernetes` removes the resources inside that namespace without targeting unrelated namespaces.

Command shape:

```bash
kubectl delete namespace learnjava-kubernetes
```
