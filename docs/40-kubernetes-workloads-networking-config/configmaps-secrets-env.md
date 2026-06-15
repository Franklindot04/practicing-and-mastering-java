# ConfigMaps, Secrets, And Environment Variables

Java backend services should get runtime settings from the environment when possible. Kubernetes can provide those values through ConfigMaps and Secrets.

## ConfigMaps

A ConfigMap stores non-secret configuration such as:

- Active profile name
- Feature flags for demos
- Public endpoint names
- Log level choices

ConfigMaps are not for passwords, tokens, private keys, or real credentials.

## Secrets

A Kubernetes Secret stores values intended to be sensitive, but the name `Secret` does not automatically make the value safe everywhere.

Important warnings:

- Do not commit real Secret manifests
- Do not store real passwords in this repository
- Do not assume base64 encoding is encryption
- Treat Secret handling as a security topic, not just a YAML topic

This repository uses `secret.example.yaml` style files with placeholders only.

## Environment Variables

Deployments can load values from ConfigMaps and Secrets into container environment variables.

This is useful for Java apps because Spring Boot can read settings such as active profiles, database URLs, and release metadata from environment variables.

## Common Mistakes

- Rebuilding an image for every config change
- Putting secrets in ConfigMaps
- Committing real Secret values
- Forgetting that a changed ConfigMap may require a rollout or restart to affect running Pods
