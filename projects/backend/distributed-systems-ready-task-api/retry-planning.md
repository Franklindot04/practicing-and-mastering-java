# Retry Planning

Retries should be designed before a task API starts calling other services.

## Operation Review

| Operation | Retry Risk | Safer Design |
| --- | --- | --- |
| Create task | Duplicate task | Use idempotency key if retried across network |
| Update task title | Usually safe if replacing value | Include validation and version expectations |
| Mark complete | Idempotent if setting state | Return current completed state on duplicate |
| Send notification | Duplicate message | Store notification request and deduplicate |

## Retry Diagram

```text
Caller -> Task API -> Dependency
          timeout      response lost

Retry requires knowing whether the operation is safe to repeat.
```

## Planning Checklist

- Define which operations are naturally idempotent.
- Add idempotency-key design before retrying create operations.
- Use timeout, retry limit, backoff, and jitter.
- Record final failure states instead of retrying forever.
- Avoid retrying validation, authorization, or permanent business-rule failures.

