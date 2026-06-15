# Java Container Best Practices

For beginner projects, keep Docker simple and boring.

## Good Starting Habits

- Run `mvn test` before building an image.
- Build a fresh jar before copying it into the image.
- Use `.dockerignore` to avoid copying `target/`, `.git/`, IDE files, and local secrets.
- Keep runtime configuration outside the image.
- Document which port the application uses.
- Label Docker examples as learning assets if they are not production deployment files.

## Environment Variables

Containers commonly receive environment variables at runtime:

```bash
docker run -e APP_DEMO_MODE=true -p 8080:8080 task-api
```

Do not place real secrets in documentation examples. Real systems usually use secret management provided by the deployment platform.

## Local-Only Docker Learning

Local Docker examples are useful for practicing packaging and runtime configuration. They are not a replacement for:

- Real production secret management.
- TLS and network policy.
- Centralized logs and metrics.
- Security scanning.
- Deployment rollback plans.
- Infrastructure ownership.

Those topics come later.
