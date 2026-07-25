# Failure Recovery Examples

Reliability design is easier when failures are described as workflows.

## Example 1: Consumer Crashes After Database Write

```text
Broker delivers message
  |
Consumer writes database row
  |
Consumer crashes before ack
  |
Broker redelivers message
```

Recovery design:

- Use a stable message id.
- Make the database write idempotent.
- Acknowledge the redelivered message after detecting the existing result.

## Example 2: Downstream Dependency Is Temporarily Down

```text
Consumer receives message
  |
Calls dependency
  |
Dependency times out
```

Recovery design:

- Retry with backoff.
- Stop after a limit.
- Dead-letter the message with enough context for investigation.

## Example 3: Payload Shape Is Unsupported

```text
Consumer receives version 3 payload
  |
Consumer only supports version 1 and 2
```

Recovery design:

- Reject the message clearly.
- Dead-letter rather than retry forever.
- Update compatibility tests and consumer version support.

## Design Reminder

Delivery guarantees depend on the whole chain:

```text
Producer publish
  + broker persistence
  + consumer processing
  + acknowledgement timing
  + database transaction
  + side-effect safety
```

## Review Questions

1. Which failures should be retried?
2. Which failures should go straight to dead-letter review?
3. What should be logged for a failed message?
