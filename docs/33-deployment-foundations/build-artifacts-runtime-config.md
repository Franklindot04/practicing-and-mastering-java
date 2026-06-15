# Build Artifacts And Runtime Configuration

A build artifact is the thing you deploy. Runtime configuration is the set of values supplied when the artifact runs.

## JAR

A Spring Boot project often produces a runnable JAR:

```bash
mvn package
java -jar target/app.jar
```

Do not commit `target/` or generated JAR files to this repository.

## Container Image

A container image packages the application with runtime instructions. Images are useful, but they should not contain real secrets.

Use environment variables or platform secret configuration at runtime.

## Release Package

A release package is not always a single file. It can include:

- Artifact name and version.
- Git commit.
- Required environment variables.
- Database or migration notes.
- Smoke test checklist.
- Rollback notes.

## Runtime Environment Variables

Runtime values often include:

- `APP_PORT`
- `SPRING_PROFILES_ACTIVE`
- Database URL
- Feature flags
- Logging level

Real secrets must come from approved secret handling, not committed files.
