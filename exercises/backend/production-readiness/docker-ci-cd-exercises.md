# Docker And CI/CD Exercises

## Exercise 1: Dockerfile Review

Difficulty: Beginner

Concepts practiced: Dockerfile basics, image contents, secret safety

Problem statement: Review a Dockerfile that copies the whole repository and sets `ENV DB_PASSWORD=real-password`. Identify the problems.

Hints:

- Images can be inspected.
- Copying the whole repo may include unnecessary files.
- Secrets should come from the runtime environment or secret manager.

Stretch challenge: Write a safer high-level Dockerfile outline.

## Exercise 2: `.dockerignore` Planning

Difficulty: Beginner

Concepts practiced: build context, generated files, local-only files

Problem statement: List files and directories that should usually be excluded from a Java Docker build context.

Hints:

- Generated build output can become stale.
- Local IDE settings are not application runtime files.
- Secret-like files should not enter the build context.

Stretch challenge: Explain why excluding `.git` can make images smaller and safer.

## Exercise 3: CI Workflow Reasoning

Difficulty: Intermediate

Concepts practiced: pull request checks, Maven tests, workflow safety

Problem statement: Design a simple GitHub Actions workflow for a Java repository. It should run tests on pull requests and pushes to `learnjava`.

Hints:

- Use checkout and setup-java.
- Keep deployment out of the first workflow.
- Avoid requiring secrets for tests.

Stretch challenge: Add a matrix idea for multiple backend Maven projects.
