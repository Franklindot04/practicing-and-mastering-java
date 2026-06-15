# Deployment Foundations

Deployment means moving a tested application version into an environment where it can run for other people or systems.

In this repository, deployment examples are educational. They do not create real servers, cloud resources, accounts, credentials, or production systems.

## Environments

- Local: your machine, fast feedback, safe demo data.
- Test: automated checks in a repeatable environment.
- Staging: production-like review before real users.
- Production: real users, real data, real consequences.

The same code should be able to move between environments, but runtime configuration changes by environment.

## Running Locally Versus Deployed

Running locally usually means using your IDE or `mvn spring-boot:run`.

Running on a server or platform usually means:

- Building an artifact.
- Providing runtime configuration.
- Starting the app with the correct profile.
- Checking health.
- Running smoke tests.
- Watching logs after release.

## Common Build Artifacts

- JAR: a Java archive produced by Maven or Gradle.
- Container image: a packaged runtime image for containers.
- Release package: a documented set of artifact, version, configuration expectations, and release notes.

## Study Order

1. [Deployment Lifecycle](deployment-lifecycle.md)
2. [Build Artifacts And Runtime Configuration](build-artifacts-runtime-config.md)
3. [Smoke Testing And Deployment Checks](smoke-testing-deployment-checks.md)
4. [Release And Rollback Basics](../34-release-rollback-basics/README.md)
