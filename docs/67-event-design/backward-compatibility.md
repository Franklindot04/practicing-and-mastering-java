# Backward Compatibility

Backward compatibility means newer consumers can still handle older events.

## Example

Older event:

```text
TaskCreated v1
  taskId
  title
```

Newer consumer expects:

```text
TaskCreated v2
  taskId
  title
  priority optional
```

The newer consumer remains backward compatible if it can process the older event without `priority`.

## Why It Matters

Events may remain in storage, logs, retry workflows, dead-letter review queues, or test fixtures. A new deployment may need to read older shapes.

## Good Habits

- Treat new fields as optional at first.
- Use defaults carefully and document what they mean.
- Keep old parsing paths until old events no longer matter.
- Test consumers with older event examples.

## Backward Compatibility Questions

- Can the latest consumer read last month's event?
- What default should be used when a field is absent?
- Does the new code accidentally assume every event has the newest shape?
- Are old event examples part of documentation or tests?
