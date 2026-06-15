# Kubernetes-Ready Task API Notes

This folder explains how the existing [Deployment-Ready Task API](../deployment-ready-task-api/README.md) could be prepared for Kubernetes learning.

It is not a complete application copy and it is not production deployment automation. The manifests use placeholder image names and example Secret values only.

## What This Folder Contains

- [manifests/namespace.yaml](manifests/namespace.yaml): local learning namespace.
- [manifests/configmap.yaml](manifests/configmap.yaml): non-secret runtime settings.
- [manifests/secret.example.yaml](manifests/secret.example.yaml): placeholder Secret shape for learning.
- [manifests/deployment.yaml](manifests/deployment.yaml): Deployment concept for the task API.
- [manifests/service.yaml](manifests/service.yaml): internal Service concept.
- [manifests/probes-and-resources.yaml](manifests/probes-and-resources.yaml): probe and resource planning example.
- [docs/kubernetes-readiness-checklist.md](docs/kubernetes-readiness-checklist.md): readiness review checklist.
- [docs/runtime-configuration-for-kubernetes.md](docs/runtime-configuration-for-kubernetes.md): runtime config notes.
- [docs/rollback-and-smoke-test-notes.md](docs/rollback-and-smoke-test-notes.md): rollout, rollback, and smoke-test notes.

## Important Boundaries

- No real cluster is created.
- No cloud resources are created.
- No Helm chart is included yet.
- No Terraform or infrastructure-as-code is included.
- No real image registry URL is included.
- No real secrets, tokens, passwords, or kubeconfig files are included.

The image `example/deployment-ready-task-api:local` is a placeholder. Learners must build and publish or load their own image before trying anything in a real local cluster.
