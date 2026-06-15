# Smoke Testing And Deployment Checks

A smoke test is a small check that confirms a deployment is basically usable.

Smoke tests are not a full test suite. They answer: "Did the new version start and can it perform one or two critical actions?"

## Before Deployment

- Confirm the version to deploy.
- Confirm tests passed.
- Confirm required runtime configuration exists.
- Confirm rollback steps are known.
- Confirm no generated artifacts or secrets are committed.

## Health Checks

Health checks should answer whether the application is alive and, when appropriate, ready.

Examples:

- `GET /actuator/health`
- `GET /api/info`

Do not expose sensitive details in public health responses.

## Smoke Test Examples

- Call the health endpoint.
- Call a public info endpoint.
- Authenticate with a demo user in a learning environment.
- Create and read one safe test record.

## After Deployment

- Watch logs for repeated errors.
- Check response status codes.
- Confirm the expected version is running.
- Keep the smoke test data safe and disposable.
