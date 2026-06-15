# Container Deployment Overview

A container deployment runs a container image built from the application.

## Basic Flow

1. Run tests.
2. Build the JAR.
3. Build the image.
4. Provide runtime configuration.
5. Run the container.
6. Check health and logs.

## Container Readiness

The image should not contain real secrets. Runtime values should come from platform configuration.

Common runtime values:

- Port.
- Active profile.
- Database URL.
- Log level.
- Release version.

## Common Mistakes

- Baking secrets into the image.
- Copying unnecessary files into the image.
- Assuming local disk is permanent.
- Not documenting the start command.
