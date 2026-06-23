# Distributed Debugging

Debugging a local program often means following one process. Debugging a distributed system means following work across boundaries.

## What Makes It Different

- The failing service may not be the service showing the error.
- A timeout may hide whether downstream work succeeded.
- Retries can make one user action appear as several attempts.
- Background work may fail after the user request already returned.
- Logs may live in several places.

## Request-Centered Debugging

Use a request ID or correlation ID to follow one action.

```text
request-id abc

API log: received create task
API log: saved task
Worker log: notification attempt failed
Worker log: retry scheduled
```

Without a shared identifier, teams guess by timestamp and message text. That is slow and error-prone.

## Useful Questions

- Where did the request enter?
- Which dependencies were called?
- Did the caller time out?
- Did the downstream service still complete the work?
- Was there a retry?
- Is there a durable workflow or status record?

