# Forward Compatibility

Forward compatibility means older consumers can tolerate newer events.

## Example

Older consumer understands:

```text
TaskCreated v1
  taskId
  title
```

Newer producer emits:

```text
TaskCreated v2
  taskId
  title
  priority optional
```

The older consumer is forward compatible if it ignores the unknown `priority` field and still handles the event correctly.

## Why It Matters

In real systems, producers and consumers are often deployed at different times. Forward compatibility lets a producer add non-breaking data without requiring every consumer to deploy first.

## Good Habits

- Ignore unknown fields when possible.
- Avoid changing the meaning of existing fields.
- Do not make old required fields disappear without a migration.
- Keep event names stable unless the meaning truly changes.
- Make consumers strict about required business meaning, not strict about harmless extra data.

## Forward Compatibility Questions

- Can existing consumers ignore this new field?
- Does the new event change the meaning of an old field?
- Will old consumers fail when they see the new version?
- Should this be a new event type instead of a changed event?
