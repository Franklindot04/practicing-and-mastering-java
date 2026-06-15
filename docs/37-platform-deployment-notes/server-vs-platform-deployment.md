# Server Versus Platform Deployment

Deploying to a server means you manage more details:

- Operating system updates.
- Java runtime.
- Process management.
- Logs.
- Network and firewall rules.

Deploying to a managed platform means the platform handles more of those concerns, but you still own application behavior and configuration.

## Java App Documentation Before Deployment

Document:

- Required Java version.
- Build command.
- Start command.
- Port behavior.
- Health endpoint.
- Required environment variables.
- Rollback plan.

## Tradeoffs

Servers give more control. Managed platforms are often easier to start with. Containers make runtime packaging more repeatable.
