# IaC Readiness Checklist

Before a Java backend is described with real IaC, confirm that the application and team workflow are ready.

## Application Readiness

- The app can receive configuration from environment variables.
- Health endpoints are documented.
- Runtime ports are known.
- Required database settings are documented without real passwords.
- Logs are written to standard output or an approved logging path.
- Container image naming is understood without embedding registry credentials.

## Infrastructure Readiness

- Environments are named consistently.
- State storage and locking have an approved design.
- Provider credentials are managed outside Git.
- Least-privilege permissions are planned.
- Rollback and recovery expectations are documented.
- Reviewers know how to read plans.

## Repository Safety

- Do not commit state files.
- Do not commit saved plan files.
- Do not commit `.terraform/`.
- Do not commit real database URLs, tokens, private keys, kubeconfig files, or provider credentials.

