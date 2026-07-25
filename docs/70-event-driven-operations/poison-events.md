# Poison Events

A poison event is an event that repeatedly fails processing and blocks or disrupts progress.

## Example

```text
TaskCreated
  taskId: missing
```

If a consumer requires `taskId`, this event may fail every time it is retried.

## Common Causes

- Invalid payload.
- Missing required field.
- Unsupported event version.
- Consumer bug.
- Data that violates consumer assumptions.

## Handling Options

- Move the event to a review area.
- Record the failure reason.
- Alert the owning team.
- Correct data when appropriate.
- Fix the consumer if the event is valid.

## Questions

- Is the event invalid or is the consumer too strict?
- Who owns the event contract?
- Can other events continue processing?
- What evidence is needed for review?
