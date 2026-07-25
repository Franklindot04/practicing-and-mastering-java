# Schema Evolution

Schema evolution is the process of changing an event contract over time.

Even if events are represented as simple JSON-like structures, the shape still matters.

## Common Evolution Scenarios

- Adding a field.
- Making an optional field required.
- Renaming a field.
- Splitting one event into two clearer events.
- Replacing one broad event with more specific event types.
- Changing allowed values for a field.

## Safer Evolution

```text
TaskCreated v1
  taskId
  title

TaskCreated v2
  taskId
  title
  priority optional
```

Adding an optional field is usually easier because older consumers can ignore it.

## Risky Evolution

```text
TaskCreated v1
  taskId
  title

TaskCreated v2
  taskId
  taskTitle
```

Renaming a field breaks consumers that still read `title`.

## Migration Thinking

Before changing an event contract, decide:

- Which consumers need the change?
- Which consumers are unknown or outside the current team?
- Whether old events still exist.
- Whether new code must read old and new shapes.
- How to detect consumers that fail after the change.

Schema evolution is not only a serialization problem. It is a coordination problem.
