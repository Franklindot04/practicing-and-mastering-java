# Kubernetes YAML Vs Helm

Kubernetes YAML describes concrete objects. Helm charts describe reusable templates that can render Kubernetes YAML.

## Raw Kubernetes YAML

Raw YAML is direct and good for learning:

- You see every field exactly as Kubernetes receives it
- There is less template logic to understand
- Small examples stay simple

Raw YAML becomes repetitive when many environments need similar manifests with small differences.

## Helm Charts

A Helm chart lets you define a reusable shape:

- Template files describe the object structure
- `values.yaml` provides defaults
- Override files can adjust settings for local, staging, or other environments

## Template Rendering

Rendering means Helm combines templates with values and produces normal Kubernetes YAML. Learners should inspect rendered manifests before thinking about installation.

## Beginner Rule

Understand the raw Kubernetes object first. Then use Helm to package and configure it.
