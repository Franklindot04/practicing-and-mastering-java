# Simple Backend Architecture

```text
Client -> DNS -> Backend Service -> Managed Database
```

## What It Shows

This is the smallest useful cloud-style sketch for a Java task API. A client reaches a backend service, and the backend stores persistent task data in a managed database.

## Tradeoffs

- Easy to understand.
- Few moving parts.
- Limited availability if there is only one backend instance.
- Database remains a critical dependency.

## Intentionally Simplified

This diagram omits load balancing, private networking, observability, backups, secrets management, TLS, and provider-specific details.

