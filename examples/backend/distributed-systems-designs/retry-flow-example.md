# Retry Flow Example

This example shows safe retry boundaries for a notification request.

```text
Task API
  |
  v
Notification Service
  |
  v
Email Provider
```

## Safer Retry Design

```text
Task API stores notification request with idempotency key
Notification Worker sends with retry limit
Worker records success or failure
Duplicate request returns existing result
```

## Retry Rules

- Retry temporary network failures.
- Do not retry invalid email addresses.
- Limit retry attempts.
- Use backoff.
- Record the final outcome.

## Review Questions

- What makes the notification request idempotent?
- How many attempts are reasonable?
- What should happen after all attempts fail?
- What should logs include without exposing sensitive content?

