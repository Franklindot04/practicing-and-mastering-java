# Platform Deployment Notes

These notes explain deployment options in a vendor-neutral way. They do not require real accounts, credentials, cloud keys, or provider-specific automation.

## Deployment Options

- Server deployment: you manage more of the machine and runtime.
- Container deployment: you package the app as an image and run it on a container platform.
- Managed app platform: the platform handles much of the runtime setup.

## Study Files

- [Server Versus Platform Deployment](server-vs-platform-deployment.md)
- [Container Deployment Overview](container-deployment-overview.md)
- [Environment Variables On Platforms](environment-variables-on-platforms.md)
- [Managed Database Deployment Notes](managed-database-deployment-notes.md)

## Keep Out Of Git

- Real cloud keys.
- Real database passwords.
- Real API tokens.
- Private keys.
- `.env` files with real values.
- Provider-specific credentials.
