# Runtime Configuration For Kubernetes

The deployment-ready task API already introduces runtime metadata and profile-based configuration. Kubernetes builds on that habit by injecting values at runtime.

## Configuration Sources

Use ConfigMaps for non-secret values:

- `SPRING_PROFILES_ACTIVE`
- release version
- commit SHA
- log level
- feature flags for local demos

Use Secrets for sensitive values, but remember:

- Secret manifests in this repository must be placeholders only
- Base64 encoding is not encryption
- Real secret management belongs outside these beginner examples

## Java Backend Notes

Spring Boot can read many settings directly from environment variables. That makes a Kubernetes Deployment a natural place to connect ConfigMaps and Secrets to the app process.

Avoid rebuilding the container image just to change environment-specific values.
