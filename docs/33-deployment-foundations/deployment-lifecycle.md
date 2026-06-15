# Deployment Lifecycle

A simple deployment lifecycle helps teams avoid guessing.

## Basic Flow

1. Choose the code version.
2. Run automated tests.
3. Build the artifact.
4. Prepare runtime configuration.
5. Deploy to the target environment.
6. Check health.
7. Run smoke tests.
8. Watch logs and metrics.
9. Decide whether to continue, pause, or roll back.

## Before Deployment

Confirm:

- Tests pass.
- The artifact version is known.
- Required environment variables are documented.
- Secrets are available through approved runtime configuration, not Git.
- The rollback option is understood.

## After Deployment

Confirm:

- The app starts.
- Health checks pass.
- Smoke tests pass.
- Logs do not show repeated errors.
- Users or dependent systems can reach the expected endpoint.

## Common Mistakes

- Deploying an untested build.
- Editing code to change environment-specific values.
- Shipping without knowing how to roll back.
- Ignoring health checks.
- Exposing secrets in logs, release notes, or deployment commands.
