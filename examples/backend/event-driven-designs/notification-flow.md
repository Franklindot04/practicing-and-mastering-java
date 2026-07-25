# Notification Flow

## Scenario

A task is completed. A notification boundary reacts to `TaskCompleted` and prepares a user-facing notification.

## Participants

- Task API.
- Task data store.
- Notification consumer.
- Notification preference reader.
- Notification record store.

## Flow

```text
Task API
  |
  +--> Mark task completed
  |
  v
TaskCompleted
  |
  v
Notification consumer
  |
  +--> Read user preferences
  |
  +--> Save notification record
```

## Failure Considerations

- Task completion should not be undone because notification preparation fails.
- The notification consumer should record failures with enough context to retry.
- Duplicate `TaskCompleted` events should not create duplicate user notifications.
- If preferences are unavailable, the consumer needs a clear fallback or retry policy.

## Review Questions

- Why is `TaskCompleted` better than `SendTaskNotification` as an event?
- Which work belongs in the task boundary?
- Which work belongs in the notification boundary?
- How should duplicate notification records be prevented?
