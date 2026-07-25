# Event Contracts

An event contract defines the name, meaning, payload, version, and compatibility expectations for an event.

## Example Contract Sketch

```text
TaskCreated
  version: 1
  eventId
  occurredAt
  taskId
  title
  createdByUserId optional
  correlationId optional
```

## Contract Notes

- `eventId` helps consumers detect duplicate handling.
- `occurredAt` tells when the fact happened.
- `taskId` identifies the affected task.
- `title` may help read-model or notification consumers avoid an extra lookup.
- Optional fields should remain safe for older consumers to ignore.

## Contract Questions

- Is the event name past tense?
- Is the payload minimal but useful?
- Does the payload avoid secrets and unnecessary personal data?
- Can consumers ignore unknown fields?
- Can new consumers read older event examples?

## Documentation Habit

Keep event examples near the planning notes so future code does not become the only contract documentation.
