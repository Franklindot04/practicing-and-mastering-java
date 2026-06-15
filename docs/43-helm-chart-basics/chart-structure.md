# Chart Structure

A small chart often looks like this:

```text
my-chart/
  Chart.yaml
  values.yaml
  templates/
    deployment.yaml
    service.yaml
    configmap.yaml
    secret.example.yaml
    _helpers.tpl
```

## Chart.yaml

`Chart.yaml` contains chart metadata such as name, description, chart version, and application version.

## values.yaml

`values.yaml` contains default configuration. Values should be safe defaults, not real secrets.

## templates/

`templates/` contains Kubernetes manifests with Helm template expressions. Helm renders these files into normal YAML.

## _helpers.tpl

Helper templates are reusable snippets for names, labels, and other repeated values.

## NOTES.txt

`NOTES.txt` can print friendly next steps after an install. In this repo, notes should stay local-only and educational.
