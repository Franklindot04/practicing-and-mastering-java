# Production Readiness Checklist

Use this checklist as a learning guide before calling a backend application production-ready.

## Configuration

- [ ] Environment-specific values are not hard-coded in Java classes.
- [ ] Local and test settings are safe to commit.
- [ ] Real credentials are provided outside source control.
- [ ] Required configuration is documented.
- [ ] Missing required configuration fails fast with a clear startup error.

## Security And Secrets

- [ ] Passwords are never stored or logged in plain text.
- [ ] Tokens, private keys, API keys, and JWT secrets are not committed.
- [ ] Demo credentials are clearly labeled as demo-only.
- [ ] Error responses do not reveal stack traces, SQL details, framework internals, or secret values.
- [ ] Protected routes require authentication and authorization where appropriate.

## Validation And Errors

- [ ] Request DTOs use validation annotations for basic shape rules.
- [ ] Services still protect business rules that annotations cannot express.
- [ ] Validation errors use a consistent response shape.
- [ ] Not-found errors are clear without exposing storage details.
- [ ] Unexpected errors return a safe generic message.

## Logging And Observability

- [ ] Logs include useful events such as startup, important state changes, and failures.
- [ ] Logs avoid secrets, passwords, raw tokens, and sensitive personal data.
- [ ] Log levels are chosen deliberately.
- [ ] Health checks are available for basic application status.
- [ ] The team can answer what failed, where it failed, and roughly when it failed.

## Dependencies And Builds

- [ ] Maven tests run in CI.
- [ ] Dependency versions are intentional and reviewed periodically.
- [ ] Build artifacts are not committed.
- [ ] Local-only files such as `.env`, `target/`, and `.class` files are ignored or removed before commits.

## Deployment Readiness

- [ ] The app can be configured without editing code.
- [ ] The app can run with a local profile and a test profile.
- [ ] The README explains how to run and test the project.
- [ ] The app has a clear list of what is simplified for learning.
- [ ] Real production needs, such as managed databases, secret managers, TLS, monitoring, backups, and incident response, are not hidden.
