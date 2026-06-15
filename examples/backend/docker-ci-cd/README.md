# Docker And CI/CD Learning Assets

These files are learning assets for Java backend projects. They show how Docker and GitHub Actions can be structured without introducing real deployment.

## Files

- `Dockerfile.spring-boot-example`: example runtime image for a built Spring Boot jar.
- `dockerignore-example`: example ignore rules to adapt into a real `.dockerignore`.

## How To Adapt The Dockerfile

1. Run project tests first.
2. Build the jar for the specific Spring Boot project.
3. Copy only the built jar into the image.
4. Provide environment-specific values at runtime.
5. Do not bake secrets into the image.

Example learning flow:

```bash
mvn -f projects/backend/secured-task-api/pom.xml test
mvn -f projects/backend/secured-task-api/pom.xml package
```

The example Dockerfile is not wired to one project on purpose. Copy it into a real project only after updating the jar path and reviewing the settings.

## Secret Safety

Do not put these in a Dockerfile, image, or workflow:

- Real database passwords.
- API tokens.
- JWT signing secrets.
- Private keys.
- `.env` files with real values.

## CI/CD Notes

The workflow added in this branch runs existing backend project tests. It does not deploy anything and does not require secrets.
