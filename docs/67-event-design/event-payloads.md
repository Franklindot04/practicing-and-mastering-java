# Event Payloads

An event payload contains the data consumers need to understand and react to the event.

## Common Fields

Many events include:

- Event identifier.
- Event name or type.
- Event version.
- Time the fact happened.
- Identifier of the affected entity.
- Minimal business data needed by consumers.
- Correlation or request identifier when useful for tracing.

Example shape:

```text
TaskCreated
  eventId: 7f3
  version: 1
  occurredAt: 2026-07-25T10:15:00Z
  taskId: 42
  title: Write release notes
  createdByUserId: 15
```

## Minimal But Useful

Too little data forces consumers to make extra calls back to the producer.

Too much data leaks private implementation details and increases compatibility risk.

## Avoid Sensitive Data

Events may be stored, replayed, logged, or handled by several consumers. Avoid including secrets, passwords, tokens, unnecessary personal data, or internal-only fields.

## Payload Questions

- Which consumers need this field?
- Is this field part of the event fact or only a current implementation detail?
- Could this data expose something sensitive?
- Will this field be stable enough for future consumers?
- Can consumers handle the event if an optional field is missing?
