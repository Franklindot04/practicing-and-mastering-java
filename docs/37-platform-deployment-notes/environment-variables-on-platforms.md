# Environment Variables On Platforms

Platforms usually let you configure environment variables for a deployed app.

Examples:

- `SPRING_PROFILES_ACTIVE`
- `APP_RELEASE_VERSION`
- `APP_COMMIT_SHA`
- `SERVER_PORT`
- Database connection settings

## Secrets

Some environment values are sensitive. Use the platform's secret handling for passwords, tokens, cloud keys, and private keys.

Do not print environment variables in logs. Do not paste real values into documentation.

## Platform Checklist

- [ ] Required variables documented.
- [ ] Safe defaults identified.
- [ ] Secrets stored outside Git.
- [ ] Missing required values fail clearly.
- [ ] Values differ by environment.
