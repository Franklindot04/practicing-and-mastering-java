# Chart Values Solutions

## Exercise 1

- `Chart.yaml`: chart metadata
- `values.yaml`: default configuration values
- `templates/deployment.yaml`: Deployment template rendered into Kubernetes YAML
- `templates/_helpers.tpl`: reusable template snippets

A placeholder image tag belongs under image values, such as `image.tag: local`.

## Exercise 2

For local-only learning, use values such as `image.repository: example/java-backend` and `image.tag: local`. Avoid real registry credentials. `latest` is confusing because it does not clearly identify which build is running.
