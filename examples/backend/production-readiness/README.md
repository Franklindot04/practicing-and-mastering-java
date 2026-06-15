# Production Readiness Examples

These examples demonstrate production-readiness ideas with plain Java. They are intentionally small and do not require external services.

## Examples

- `EnvironmentConfigDemo.java`: reads safe configuration from environment variables with local defaults.
- `SecretSeparationDemo.java`: separates safe public configuration from secret-sensitive values.
- `LoggingLevelsDemo.java`: demonstrates Java standard logging levels.
- `SafeLoggingDemo.java`: logs useful context without printing secrets.
- `HealthCheckShapeDemo.java`: models a simple health-check response.
- `SafeErrorResponseDemo.java`: models a safer API error response.
- `GracefulFallbackDemo.java`: shows a fallback when an optional value is unavailable.

## Compile And Run

From the repository root:

```bash
javac examples/backend/production-readiness/*.java
java -cp examples/backend/production-readiness EnvironmentConfigDemo
```

Remove generated `.class` files before committing.

## Why This Matters

Production-style backend work depends on habits that start before deployment:

- Configuration should come from the environment when values differ by environment.
- Secrets should not be committed, printed, or returned to clients.
- Logs should explain behavior without exposing sensitive data.
- Health and error response shapes should be predictable.
- Fallbacks should be explicit, limited, and documented.

These examples prepare you for the Spring Boot production-readiness project without claiming to be a complete production system.
