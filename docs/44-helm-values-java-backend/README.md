# Helm Values For Java Backends

Java backend applications usually need runtime configuration. Helm values provide a structured way to pass that configuration into Kubernetes templates.

These notes are local-only and educational. Do not place real passwords, tokens, registry credentials, kubeconfig data, or private keys in values files.

## Start Here

1. [Backend Values Structure](backend-values-structure.md)
2. [Environment Config And Secrets](env-config-and-secrets.md)
3. [Probes, Resources, And Ports](probes-resources-and-ports.md)
4. [Environment Overrides](environment-overrides.md)

## What You Should Learn

- How `values.yaml` maps to rendered manifests
- Which values a Java backend commonly needs
- Why image tags should identify a specific build
- How profiles, probes, resources, and ports fit together
- How to separate defaults from local override files
