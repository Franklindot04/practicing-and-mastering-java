# Acknowledgements And Retries

An acknowledgement tells the broker that the consumer has handled a message successfully enough that it does not need immediate redelivery.

## Positive Acknowledgement

```text
Broker ---> Consumer
              |
              v
           Process
              |
              v
Broker <--- ack
```

Acknowledging after the durable work is safer than acknowledging before work starts.

## Negative Acknowledgement

A negative acknowledgement says the consumer could not process the message.

The broker or application may requeue it, retry later, or move it to a dead-letter destination depending on the design.

## Retry Strategies

Common retry strategies:

- Immediate retry for short transient failures.
- Delayed retry for dependencies that need time to recover.
- Exponential backoff to avoid hammering a failing dependency.
- Retry limit to prevent infinite loops.

## Retry Limits

Every retry strategy needs a stopping point.

Without a limit, one bad message can consume worker capacity forever.

## Backoff Example

```text
Attempt 1: now
Attempt 2: after 5 seconds
Attempt 3: after 30 seconds
Attempt 4: after 2 minutes
Then: dead-letter for inspection
```

## Review Questions

1. Why is acknowledging before processing risky?
2. What problem does backoff solve?
3. Why should retries have a limit?
