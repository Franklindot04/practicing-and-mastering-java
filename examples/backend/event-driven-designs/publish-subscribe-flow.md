# Publish Subscribe Flow

## Scenario

A task API publishes `TaskCreated`. Multiple subscribers use the same event for different reasons.

## Participants

- Task API producer.
- Conceptual event publication boundary.
- Notification subscriber.
- Activity feed subscriber.
- Metrics subscriber.

## Flow

```text
Task API
  |
  v
TaskCreated
  |
  +--> Notification subscriber
  |
  +--> Activity feed subscriber
  |
  +--> Metrics subscriber
```

## Failure Considerations

- Failure in one subscriber should not automatically stop other subscribers.
- The producer should not know subscriber internals.
- Adding a new subscriber increases the importance of contract compatibility.
- Each subscriber needs its own success and failure visibility.

## Review Questions

- What makes this publish/subscribe instead of direct calls?
- Which team or boundary owns `TaskCreated`?
- What consumer failures should trigger alerts?
- What event change could break subscribers?
