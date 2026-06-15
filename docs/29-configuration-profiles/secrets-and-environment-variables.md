# Secrets And Environment Variables

A secret is any value that could cause harm if it is exposed. Passwords, tokens, API keys, private keys, and signing secrets are all secrets.

## Secret Safety Rules

- Do not commit real secrets.
- Do not print secrets in logs.
- Do not include secrets in error responses.
- Do not paste secrets into examples, screenshots, issues, or pull requests.
- Rotate a secret if it was exposed.

## Environment Variables

Environment variables are a common way to supply configuration outside the codebase.

Example:

```properties
app.external-service-url=${EXTERNAL_SERVICE_URL:http://localhost:8081}
```

For secrets, avoid unsafe default values:

```properties
app.signing-secret=${APP_SIGNING_SECRET}
```

If `APP_SIGNING_SECRET` is required, the application should validate that it exists before using security-sensitive features.

## `.env` Files

`.env` files are convenient locally, but they often contain secrets. Do not commit them to this repository.

If a project needs to show expected values, use an example file without real values, such as:

```text
APP_PORT=8080
APP_DEMO_MODE=true
APP_SIGNING_SECRET=replace-with-a-local-demo-value
```

That example text is documentation, not a real secret.

## Demo Values

Learning projects may include demo usernames, local H2 database URLs, or sample passwords. They must be clearly labeled as demo-only and must not be reused outside the learning project.

Real production systems usually use managed secret storage, strict access control, audit trails, and rotation. Those topics come later.
