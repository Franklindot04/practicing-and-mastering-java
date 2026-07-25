# Event Boundaries

Event boundaries describe where meaningful facts are created and who owns their meaning.

## Candidate Task API Events

- `TaskCreated`
- `TaskUpdated`
- `TaskCompleted`
- `TaskReopened`
- `TaskDeleted`

These are planning candidates, not implementation requirements.

## Boundary Ownership

```text
Task API boundary
  |
  +--> Owns task state
  |
  +--> Owns task event meaning
  |
  +--> Does not own notification internals
  |
  +--> Does not own reporting internals
```

## Boundary Questions

- Which state change makes the event true?
- Which API layer validates the command before the event exists?
- Which data belongs to the task boundary?
- Which future consumers should react without changing task logic?
- Which reactions are actually commands that should remain explicit calls?

## Learning Rule

Emit events for domain facts, not for controller methods or database table updates.
