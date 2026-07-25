# Dead Letters Poison Messages And Duplicates

Reliable messaging systems need a plan for messages that cannot be processed normally.

## Dead-Letter Queues

A dead-letter queue is a place where failed messages are moved after the system decides normal retry should stop.

```text
Main queue ---> Consumer
     ^            |
     |            v
     +--------- retry
                  |
                  v
           Dead-letter queue
```

A dead-letter queue is not a fix. It is a visibility and containment mechanism.

## Poison Messages

A poison message is a message that repeatedly fails because of its content or shape.

Examples:

- Required field is missing.
- Payload uses an unsupported version.
- Business rule can never succeed.
- Consumer code has a bug for one category of message.

## Duplicate Messages

Duplicates happen in at-least-once systems when a message is redelivered after uncertainty.

Examples:

- Consumer processed the message but crashed before acknowledging.
- Broker did not receive the acknowledgement.
- Producer retried a publish after a timeout.

## Handling Duplicates

Use stable identifiers and idempotency storage.

```text
messageId = task-created-123

if alreadyProcessed(messageId):
    skip side effect
else:
    apply change
    record processed messageId
```

## Review Questions

1. Why is a dead-letter queue useful?
2. What makes a message poisonous?
3. What identifier helps detect duplicates?
