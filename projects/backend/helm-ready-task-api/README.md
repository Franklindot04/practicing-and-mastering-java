# Helm-Ready Task API Notes

This folder explains how the deployment-ready and Kubernetes-ready task API concepts could be packaged with Helm.

It is documentation plus a local-only educational chart. It is not a full Spring Boot project copy and it is not production deployment automation.

## Contents

- [chart/Chart.yaml](chart/Chart.yaml): chart metadata.
- [chart/values.yaml](chart/values.yaml): safe default values.
- [chart/values-local.yaml](chart/values-local.yaml): local rendering overrides.
- [chart/templates/](chart/templates/): Deployment, Service, ConfigMap, Secret example, and helpers.
- [docs/helm-readiness-checklist.md](docs/helm-readiness-checklist.md): readiness review checklist.
- [docs/values-design-for-java-backends.md](docs/values-design-for-java-backends.md): value design notes.
- [docs/upgrade-rollback-notes.md](docs/upgrade-rollback-notes.md): upgrade and rollback notes.

The image `example/deployment-ready-task-api:local` is a placeholder. Learners must build, load, or publish their own image before any real deployment.

Do not run `helm install` or `helm upgrade` against a real cluster in this stage.
