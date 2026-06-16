# Simple Service-To-Service Flow

This example follows an internal request from `task-api` to `notification-service`.

```text
client
  -> api gateway
  -> task-api
  -> notification-service
  -> message provider abstraction
```

## What To Notice

- The gateway handles entry into the backend.
- `task-api` owns task business rules.
- `notification-service` owns notification behavior.
- The internal service call is the kind of traffic a mesh may observe or control.

## Design Questions

- Is the notification call synchronous or asynchronous?
- What happens if notification delivery is slow?
- What timeout protects the user-facing request?
- Which logs connect the task request to the notification request?

