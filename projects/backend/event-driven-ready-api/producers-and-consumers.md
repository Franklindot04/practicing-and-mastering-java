# Producers And Consumers

The Task API would act as the producer for task lifecycle events.

Future consumers might include notification, reporting, audit, or activity feed boundaries.

## Producer Planning

```text
Task command accepted
  |
  v
Task state changed
  |
  v
Task event recorded
```

Producer responsibilities:

- Validate the command before creating the event.
- Create events only for accepted state changes.
- Use stable event names and payloads.
- Record enough context for tracing and debugging.

## Consumer Planning

```text
TaskCreated
  |
  +--> Notification consumer
  |
  +--> Reporting consumer
  |
  +--> Audit consumer
```

Consumer responsibilities:

- Process events idempotently where possible.
- Keep local failures visible.
- Avoid assuming every other consumer succeeded.
- Treat event data as a versioned contract.

## Planning Questions

- Which consumers are required for business correctness?
- Which consumers are optional side effects?
- What should happen when a consumer is delayed?
- Who owns retry and investigation for each consumer?
