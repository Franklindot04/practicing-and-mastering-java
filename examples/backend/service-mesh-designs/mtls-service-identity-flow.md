# mTLS Service Identity Flow

This flow separates workload identity from user identity.

```text
authenticated user
  -> task-api as workload identity task-api
  -> encrypted mesh connection
  -> notification-service as workload identity notification-service
```

## What mTLS Helps Prove

- The caller workload identity.
- The destination workload identity.
- That traffic is encrypted in transit.

## What Application Code Still Proves

- Which user made the request.
- Whether that user can update the task.
- Whether the input is valid.
- Whether the business action should be audited.

