# Templates And Values

Helm templates use values to produce Kubernetes YAML.

## Values Concept

A value is a configurable setting such as:

- image repository
- image tag
- replica count
- service port
- active Spring profile
- resource requests and limits
- probe paths

## Template Concept

A template references values with expressions such as:

```text
{{ .Values.image.repository }}
```

When Helm renders the chart, that expression is replaced with the configured value.

## Override Files

An override file such as `values-local.yaml` can change defaults for a specific learning environment.

## Safe Defaults

Default values in this repository should use placeholder image names and placeholder secret examples only.
