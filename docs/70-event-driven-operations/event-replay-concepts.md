# Event Replay Concepts

Event replay means processing stored events again.

Replay can be useful, but it is not harmless. Consumers must be designed carefully before old events are processed again.

## Possible Uses

- Rebuild a read model.
- Recover after a consumer bug.
- Reprocess events after a temporary dependency outage.
- Validate a new consumer against historical examples.

## Risks

- Duplicate side effects.
- Replaying events in the wrong order.
- Processing events with old schema versions.
- Triggering user-visible actions again.
- Overloading downstream systems.

## Conceptual Flow

```text
Stored events
  |
  v
Replay selection
  |
  v
Consumer processing
  |
  v
Verified result
```

## Questions

- Which events should be replayed?
- Which consumers are safe to replay?
- What side effects must be disabled or deduplicated?
- How will replay progress and failures be monitored?
