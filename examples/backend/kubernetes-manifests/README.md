# Kubernetes Manifest Basics

This folder contains local-only educational Kubernetes manifests for a simple Java backend API. They are meant for reading, review, and optional local experimentation in a disposable local learning cluster.

These files are not production-ready. They do not create a cluster, do not require cloud resources, do not include real secrets, and do not assume a real image registry.

## Files

- [namespace.yaml](namespace.yaml): creates a learning namespace so resources are grouped together.
- [configmap.yaml](configmap.yaml): demonstrates non-secret runtime configuration for a Java backend.
- [secret.example.yaml](secret.example.yaml): demonstrates Secret shape with placeholder values only.
- [deployment.yaml](deployment.yaml): shows a small Deployment using a placeholder local image and environment variables.
- [service.yaml](service.yaml): exposes the Pods inside the cluster with a `ClusterIP` Service.
- [health-probes-example.yaml](health-probes-example.yaml): focuses on startup, readiness, and liveness probes.
- [resource-limits-example.yaml](resource-limits-example.yaml): focuses on CPU and memory requests and limits.
- [kustomization.yaml](kustomization.yaml): groups the basic manifests for local review.

## How This Relates To A Java Backend

A Spring Boot backend typically needs:

- Runtime settings such as `SPRING_PROFILES_ACTIVE`
- Health endpoints for probes
- A stable Service name for in-cluster traffic
- A Deployment that manages replicated Pods
- Resource settings that respect JVM memory behavior

The image name `example/deployment-ready-task-api:local` is a placeholder. Learners must build their own image before trying these manifests in a real local cluster.

## Suggested Reading Order

1. Start with [namespace.yaml](namespace.yaml)
2. Read [configmap.yaml](configmap.yaml) and [secret.example.yaml](secret.example.yaml)
3. Compare labels in [deployment.yaml](deployment.yaml) and [service.yaml](service.yaml)
4. Review probe and resource examples separately

## Optional Local-Only Commands

Only run these if you already have a disposable local Kubernetes cluster. Do not run them against a shared, production, or cloud cluster.

```bash
kubectl apply --dry-run=client -k examples/backend/kubernetes-manifests
kubectl apply -k examples/backend/kubernetes-manifests
kubectl get all -n learnjava-kubernetes
kubectl delete namespace learnjava-kubernetes
```

The delete command removes the learning namespace and the resources inside it. Review every manifest before applying anything.
