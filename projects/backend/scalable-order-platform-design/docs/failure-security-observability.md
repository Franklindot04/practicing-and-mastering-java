# Failure Security And Observability

## Failure Scenarios

| Scenario | Design Response |
| --- | --- |
| Duplicate client submission | Idempotency key returns the original order result |
| Inventory unavailable | Order is rejected with an inventory failure category |
| Payment unavailable | Order is rejected or pending according to product decision |
| Notification fails | Order remains created; notification can be retried later |
| Read model lags | User-facing status explains pending work |

## Retry Boundaries

Retries are safest at boundaries where the operation is idempotent.

Examples:

- Retrying order submission requires an idempotency key.
- Retrying payment authorization requires a payment-side idempotency concept in a real adapter.
- Retrying notifications should tolerate duplicate delivery.

## Dead-Letter Handling Concepts

If asynchronous follow-up is added later, failed messages should include:

- Correlation identifier.
- Order identifier.
- Failure category.
- Attempt count.
- Safe diagnostic detail.

Do not include secrets or payment credentials in dead-letter payloads.

## Observability

Capture:

- Order creation attempts.
- Idempotency replays.
- Inventory failure counts.
- Payment failure counts.
- Notification backlog if async work exists.
- Latency for critical boundaries.
- Correlation identifiers across calls.

## Security Boundaries

- Validate all external input.
- Keep payment details outside this order boundary.
- Return safe errors to clients.
- Avoid logging sensitive data.
- Separate customer-facing and operator-facing views.
