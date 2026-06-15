# Observability And Tracing Planning

Mesh observability works best when application observability already exists.

## Needed Signals

- Request count.
- Error rate.
- Latency percentiles.
- Trace IDs across services.
- Application logs with safe context.
- Deployment version.
- Dependency status.

## Trace Planning

A useful trace should connect:

```text
gateway request -> task-api -> conceptual dependency -> response
```

The trace should help answer which hop was slow, which version handled the request, and whether retries occurred.

## Privacy Reminder

Do not log passwords, tokens, private keys, session identifiers, raw authorization headers, personal data, or secret configuration values.

