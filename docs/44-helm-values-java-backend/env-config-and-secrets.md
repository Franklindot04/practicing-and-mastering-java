# Environment Config And Secrets

Spring Boot applications can read many settings from environment variables. Helm values can feed those variables into ConfigMaps and Secret examples.

## ConfigMap-Backed Values

Use ConfigMap-backed values for non-secret settings:

- `SPRING_PROFILES_ACTIVE`
- release version
- commit SHA
- log level
- demo feature flags

## Secret-Backed Values

Use Secret-backed values only for placeholder examples in this repo.

Do not commit:

- real database passwords
- real JWT secrets
- real API tokens
- real registry credentials
- cloud keys
- kubeconfig data

## Placeholder Secret Pattern

If a chart includes `secret.example.yaml`, it should clearly say the values are examples only.

## Common Mistakes

- Treating base64 as encryption
- Putting real secrets in `values.yaml`
- Mixing non-secret and secret configuration in one structure
- Forgetting that rendered manifests may expose values during review
