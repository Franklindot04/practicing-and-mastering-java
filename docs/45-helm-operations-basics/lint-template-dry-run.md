# Lint, Template, And Dry Run

## helm lint

`helm lint` checks chart structure and common chart problems. It does not prove a chart is production-ready, but it catches useful beginner mistakes.

Command shape:

```bash
helm lint path/to/chart
```

## helm template

`helm template` renders a chart locally into Kubernetes YAML. It is one of the safest first commands because it does not install anything.

Command shape:

```bash
helm template demo path/to/chart --values path/to/chart/values-local.yaml
```

## Dry-Run Concept

Helm supports dry-run modes for install and upgrade workflows. Dry-run helps preview what Helm would do, but beginners should still inspect rendered manifests and avoid real clusters in this stage.

## Inspect Rendered Manifests

Look for:

- image names and tags
- Service selectors and Pod labels
- ConfigMap and Secret references
- probe paths
- resource requests and limits
- namespace assumptions
