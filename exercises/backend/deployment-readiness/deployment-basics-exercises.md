# Deployment Basics Exercises

## Exercise 1: Deployment Lifecycle

Difficulty: Beginner

Concepts practiced: deployment lifecycle, artifacts, verification

Problem statement: List the steps you would follow to deploy a tested Spring Boot learning API to a shared demo environment.

Hints:

- Start with tests and artifact version.
- Include runtime configuration.
- End with health checks and smoke tests.

Stretch challenge: Add one rollback decision point.

## Exercise 2: Runtime Configuration

Difficulty: Beginner

Concepts practiced: environment variables, profiles, safe defaults

Problem statement: Choose environment variable names for port, active profile, release version, and commit SHA.

Hints:

- Use safe defaults only for non-secret values.
- Do not include real credentials.

Stretch challenge: Explain which value should appear in `/api/version`.

## Exercise 3: Build Artifact Review

Difficulty: Intermediate

Concepts practiced: JARs, images, release packages

Problem statement: Compare a JAR, a container image, and release notes. What question does each one answer?

Hints:

- A JAR is executable app output.
- An image packages runtime instructions.
- Release notes explain what changed.

Stretch challenge: Explain why generated artifacts should not be committed.
