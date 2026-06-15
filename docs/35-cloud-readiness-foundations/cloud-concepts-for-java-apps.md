# Cloud Concepts For Java Apps

Cloud platforms provide infrastructure that can run applications without you owning physical servers.

## Compute Options

- Virtual machine: a server-like environment you manage more directly.
- Container: a packaged app runtime that can run on container platforms.
- Managed app platform: a higher-level service where the platform handles much of the runtime setup.

Each option has tradeoffs in control, simplicity, cost, and operational responsibility.

## Java App Needs

A Java backend usually needs:

- A JDK or JRE runtime.
- A built JAR or container image.
- Runtime environment variables.
- A network port.
- Health checks.
- Logs.
- Access to a database or other dependencies.

## Environment Variables And Secrets

Cloud platforms usually provide ways to configure environment variables and secrets. Treat them differently:

- Environment variable: runtime value, may or may not be sensitive.
- Secret: sensitive runtime value that must be protected.

Do not paste real cloud keys, database passwords, or JWT secrets into Git.
