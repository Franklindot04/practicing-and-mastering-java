# Timeouts, Retries, And Backoff

Timeouts bound waiting, deadlines bound total request time, cancellation stops work that no longer matters, and retry policy decides which failures deserve another attempt.

Stage 24 follows the observability stage: telemetry helps you notice symptoms, and reliability engineering helps you decide what failure means, how far it can spread, and how recovery should work.

## Scope

This section stays framework-light and Java-oriented. It focuses on concepts that apply whether the Java code later runs in a CLI, web service, batch job, or message consumer.

## Recommended Reading Order

1. [Timeouts, Deadlines, And Cancellation](timeouts-deadlines-and-cancellation.md)
2. [Retry Strategies And Backoff](retry-strategies-and-backoff.md)
3. [Retry Safety And Budgeting](retry-safety-and-budgeting.md)

## Files

- [Timeouts, Deadlines, And Cancellation](timeouts-deadlines-and-cancellation.md)
- [Retry Strategies And Backoff](retry-strategies-and-backoff.md)
- [Retry Safety And Budgeting](retry-safety-and-budgeting.md)
