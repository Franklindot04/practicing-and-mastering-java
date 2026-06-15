# Dockerfile Basics

A Dockerfile is a recipe for building an image.

Small educational example:

```dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Common Instructions

- `FROM`: chooses the base image.
- `WORKDIR`: sets the working directory inside the image.
- `COPY`: copies files into the image.
- `RUN`: runs a build-time command.
- `EXPOSE`: documents the port the app listens on.
- `ENTRYPOINT`: sets the command that runs when the container starts.

## What Not To Put In Images

Do not bake these into images:

- Real database passwords.
- API keys.
- JWT signing secrets.
- Private keys.
- `.env` files with real values.
- Local machine paths that only work on one computer.

Images can be shared. Treat anything inside an image as something that may be inspected.

## Common Mistakes

- Copying the whole repository into the image.
- Building images with generated files from an old `target/` directory.
- Running as if Docker replaces tests.
- Putting secrets into `ENV` instructions.
- Using huge base images without understanding what they contain.
