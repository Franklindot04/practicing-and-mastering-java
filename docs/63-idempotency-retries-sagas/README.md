# Idempotency, Retries, And Sagas

Distributed systems fail in awkward places: before a request is received, after work is done but before the response returns, or halfway through a multi-step workflow.

This section explains patterns that help teams handle those failures without building a real distributed system.

## Topics

- [Idempotency](idempotency.md)
- [Retry Patterns](retry-patterns.md)
- [Distributed Transactions](distributed-transactions.md)
- [Saga Pattern](saga-pattern.md)

## Learning Goals

- Explain why duplicate requests happen.
- Identify which operations are safe to retry.
- Describe why distributed transactions are difficult.
- Explain sagas and compensation actions in plain language.
- Design failure handling before adding automation.

## Request Failure Shape

```text
Client -> API -> Payment Service
              X response lost

Did payment happen?
Should the client retry?
Could retry charge twice?
```

Good distributed design answers these questions deliberately.

