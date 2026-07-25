# Event Evolution And Observability

Event-driven readiness includes planning for contract change and operational visibility.

## Event Evolution

Event contracts should evolve carefully.

Safer changes:

- Add optional fields.
- Keep existing field meanings stable.
- Support old event examples while consumers migrate.
- Create a new event type when the fact meaning changes.

Risky changes:

- Rename required fields.
- Remove fields without migration.
- Change the meaning of an existing field.
- Reuse a version number for a different payload.

## Observability Planning

Useful event metadata:

- `eventId`
- `eventType`
- `version`
- `occurredAt`
- `correlationId`
- `producer`

## Trace Concept

```text
HTTP request correlationId=123
  |
  v
TaskCreated correlationId=123
  |
  +--> Notification logs correlationId=123
  |
  +--> Reporting logs correlationId=123
```

## Questions

- Can a developer follow one user action across event handling?
- Can operators see stuck or failed event processing?
- Are old event examples kept for compatibility review?
- Which dashboards or logs would be needed later?
