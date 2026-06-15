# GitHub Actions For Java

GitHub Actions can run Java checks when a pull request or push happens.

Minimal learning workflow:

```yaml
name: Java CI

on:
  pull_request:
  push:
    branches: [ learnjava ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '17'
      - run: mvn test
```

## Maven Test Workflow

`mvn test` compiles the project and runs tests. For a repository with several independent Maven projects, you may add separate commands for each project.

## Safe Workflow Habits

- Do not print secrets.
- Do not require deployment credentials for test-only workflows.
- Give workflows clear names.
- Keep commands understandable.
- Prefer pull request checks before deployment automation.

## What Comes Later

Real delivery pipelines may build Docker images, publish artifacts, deploy to staging, run migrations, and support rollbacks. Those are later topics after fundamentals are stable.
