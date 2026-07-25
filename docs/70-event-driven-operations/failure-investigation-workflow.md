# Failure Investigation Workflow

A failure investigation workflow gives teams a repeatable way to debug event-driven behavior.

## Workflow

```text
1. Identify user-visible symptom
2. Find affected entity id
3. Find correlation id
4. Find related event id
5. Check producer result
6. Check consumer result
7. Classify failure
8. Retry, correct, or create a fix
9. Document what prevented faster diagnosis
```

## Failure Classes

- Event was not created.
- Event was created but not made available.
- Consumer did not process it yet.
- Consumer failed with a retryable error.
- Consumer failed with a poison event.
- Consumer succeeded but visible state is stale.
- Consumer succeeded but user expectation was different from system behavior.

## Evidence To Collect

- Event name and version.
- Event ID and correlation ID.
- Entity ID.
- Producer timestamp.
- Consumer attempt timestamps.
- Error message or rejection reason.
- Current entity state.

## After The Incident

Ask what signal would have made the problem obvious sooner. Good event-driven operations improve after each investigation.
