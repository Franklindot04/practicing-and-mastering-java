# Request Flow Example

This example shows how one user request can cross several boundaries.

```text
Browser
  |
  v
Task API
  |
  +--> User Service
  |
  +--> Task Database
  |
  +--> Notification Worker
```

## What To Notice

- The browser only sees one request.
- The API may depend on several internal components.
- Each network call can be slow, fail, or return stale information.
- The notification work may not need to block the user response.

## Design Questions

- Which calls are required before responding to the user?
- Which calls can happen later?
- What timeout should each dependency have?
- Which failures should be visible to the user?
- Which failures should be retried or queued for later review?

