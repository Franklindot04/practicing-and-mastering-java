# Event Design Principles

Events are contracts. Once other parts of a system depend on them, their names and payloads need the same care as public APIs.

This section is conceptual and vendor neutral. It focuses on how to design event names, payloads, immutability, versions, schema evolution, and compatibility.

## Topics

- [Event Naming](event-naming.md)
- [Event Payloads](event-payloads.md)
- [Immutable Events](immutable-events.md)
- [Event Versioning](event-versioning.md)
- [Schema Evolution](schema-evolution.md)
- [Backward Compatibility](backward-compatibility.md)
- [Forward Compatibility](forward-compatibility.md)

## Learning Goals

After this section, you should be able to:

- Name events as clear business facts.
- Decide what belongs in an event payload.
- Explain why emitted events should not be changed in place.
- Plan simple event versioning without breaking consumers.
- Describe backward and forward compatibility in plain language.

## Big Idea

An event should be easy for another team or future version of your own application to understand.

```text
Good:
TaskCompleted
  taskId
  completedAt
  completedByUserId

Weak:
UpdateTaskMessage
  data
```

Good event design makes future change less surprising.
