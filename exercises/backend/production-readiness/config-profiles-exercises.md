# Configuration And Profiles Exercises

## Exercise 1: Separate Safe Defaults From Environment Values

Difficulty: Beginner

Concepts practiced: external configuration, defaults, environment variables

Problem statement: Design three configuration values for a task API: app name, port, and demo mode. Decide which values can have safe defaults and which should be supplied by the environment.

Hints:

- Local defaults are useful for non-secret values.
- Secrets should not use real fallback values.
- Document the environment variable names.

Stretch challenge: Add one required secret-like value and explain how the app should behave when it is missing.

## Exercise 2: Local And Test Profiles

Difficulty: Beginner

Concepts practiced: Spring profiles, local/test separation, H2 configuration

Problem statement: Sketch `application.properties`, `application-local.properties`, and `application-test.properties` for a Spring Boot task API that uses H2 locally and in tests.

Hints:

- Base settings belong in the shared file.
- Test settings should be isolated and repeatable.
- Avoid real production connection strings.

Stretch challenge: Explain why committing a real `application-prod.properties` with credentials is unsafe.

## Exercise 3: Configuration Review

Difficulty: Intermediate

Concepts practiced: configuration review, secret safety, fail-fast thinking

Problem statement: Review this configuration idea: "If `APP_SIGNING_SECRET` is missing, use `local-demo-secret`." Decide when that is acceptable and when it is dangerous.

Hints:

- Demo-only local behavior is different from production-like behavior.
- Silent fallbacks can hide broken deployments.
- Security-sensitive values should be explicit.

Stretch challenge: Write a short startup validation rule in plain English.
