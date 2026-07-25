# Immutable Events

An emitted event should be treated as immutable.

Immutable means the event is not edited in place after it has been published or recorded.

## Why Immutability Matters

Events represent history. If a task was completed at 10:15, changing that event later makes the history unreliable.

```text
TaskCompleted at 10:15
```

If the task is reopened later, emit another event.

```text
TaskCompleted at 10:15
TaskReopened at 10:30
```

Do not rewrite the first event to pretend completion never happened.

## Benefits

- Consumers can trust event history.
- Auditing becomes easier.
- Debugging can follow what actually happened.
- Reprocessing old events is more predictable.

## Corrections

If an earlier event was wrong, prefer emitting a correcting event.

```text
TaskCreated
TaskTitleCorrected
```

The exact correction event depends on the domain, but the principle is the same: add history instead of silently changing history.

## Learning Rule

State can be updated. History should be appended.
