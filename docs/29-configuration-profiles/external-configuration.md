# External Configuration

External configuration means values are provided from outside the compiled application. The application reads those values at startup or runtime.

## Why It Matters

If values are hard-coded, every environment change requires a code change. That is risky for production systems because configuration changes should be reviewable, reversible, and environment-specific.

Examples of configuration:

- Server port.
- Database URL.
- Logging level.
- Feature flag.
- Public application name.
- Timeout duration.

Examples of secrets:

- Database password.
- API token.
- JWT signing secret.
- Private key.

Configuration and secrets are related, but secrets need stricter handling.

## Common Sources

- `application.properties` or `application.yml` for safe defaults.
- Environment variables for values supplied by the runtime environment.
- Command-line arguments for temporary overrides.
- Secret managers in real production environments.

This repository does not introduce cloud secret managers yet. For now, learn the boundary: safe defaults can be committed; real secrets cannot.

## Environment Variable Example

Spring Boot can read environment variables and use defaults:

```properties
server.port=${APP_PORT:8080}
app.demo-mode=${APP_DEMO_MODE:true}
```

The value before the colon is the environment variable name. The value after the colon is the fallback.

Use fallbacks for safe local defaults. Avoid fallbacks that accidentally become production credentials.

## Fail Fast For Required Values

Some values should not have a production fallback. If a real deployment needs a signing secret, the app should fail to start when it is missing instead of silently using a demo value.

Learning projects can document this with comments and README notes. Real production apps usually validate required configuration at startup.
