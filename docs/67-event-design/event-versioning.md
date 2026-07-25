# Event Versioning

Event versioning helps producers change event structure while consumers continue to work.

## Why Versions Exist

An event may start simple.

```text
TaskCreated v1
  taskId
  title
```

Later, consumers may need more context.

```text
TaskCreated v2
  taskId
  title
  createdByUserId
```

The version tells consumers which shape they are reading.

## Version The Contract

Version the event contract, not the Java class name or database table.

Good:

```text
eventType: TaskCreated
version: 2
```

Weak:

```text
className: TaskEntityV2
```

## Prefer Compatible Changes

Small additive changes are often easier than replacing an event entirely.

Usually safer:

- Add an optional field.
- Add a new event for a new fact.
- Keep old fields until consumers migrate.

Usually risky:

- Rename a required field.
- Change the meaning of an existing field.
- Remove a field that consumers may still need.
- Reuse a version number with a different shape.

## Versioning Questions

- Which consumers already depend on this event?
- Can old consumers ignore the new field?
- Can new consumers handle older events?
- How long should the old version remain supported?
