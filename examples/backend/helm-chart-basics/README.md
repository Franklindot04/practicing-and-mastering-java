# Helm Chart Basics Example

This folder contains a small educational Helm chart for a generic Java backend. It is local-only and not production-ready.

## Files

- [Chart.yaml](Chart.yaml): chart metadata.
- [values.yaml](values.yaml): safe default values with placeholder image and configuration.
- [values-local.yaml](values-local.yaml): local learning overrides.
- [templates/deployment.yaml](templates/deployment.yaml): renders a Deployment.
- [templates/service.yaml](templates/service.yaml): renders a ClusterIP Service.
- [templates/configmap.yaml](templates/configmap.yaml): renders non-secret settings.
- [templates/secret.example.yaml](templates/secret.example.yaml): renders placeholder Secret data only.
- [templates/_helpers.tpl](templates/_helpers.tpl): reusable names and labels.
- [templates/NOTES.txt](templates/NOTES.txt): local learning notes.

## Safe Inspection Commands

Use these only to inspect the chart locally:

```bash
helm lint examples/backend/helm-chart-basics
helm template demo-java-backend examples/backend/helm-chart-basics --values examples/backend/helm-chart-basics/values-local.yaml
```

Do not run `helm install` or `helm upgrade` against a real cluster in this stage.

## Production Hardening Later

Production charts need real secret management outside Git, image provenance, security review, resource tuning, monitoring, rollout strategy, and environment-specific ownership.
