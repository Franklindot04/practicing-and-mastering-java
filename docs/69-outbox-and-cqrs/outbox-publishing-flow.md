# Outbox Publishing Flow

An outbox publishing flow separates the user-facing transaction from later event publication.

## Conceptual Flow

```text
User request
  |
  v
Validate command
  |
  v
Save business state and outbox record
  |
  v
Return response

Later:

Outbox publisher
  |
  v
Read unpublished records
  |
  v
Publish event
  |
  v
Mark record as published or record failure
```

## Failure Handling

If publishing fails, the outbox record remains available for retry or investigation.

If marking as published fails after the event was published, the event may be published again. Consumers should be designed to tolerate duplicates.

## Operational Needs

- Count unpublished records.
- Track oldest unpublished record age.
- Record publish attempt failures.
- Alert when the outbox stops draining.
- Keep enough context to investigate failed records.

## Learning Rule

The outbox pattern changes "Did we publish immediately?" into "Did we durably record that this event must be published?"
