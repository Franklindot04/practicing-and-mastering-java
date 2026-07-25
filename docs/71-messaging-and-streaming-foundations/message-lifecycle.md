# Message Lifecycle

A message has a lifecycle from creation to final handling.

```text
Create
  |
  v
Publish
  |
  v
Store or route in broker
  |
  v
Deliver to consumer
  |
  v
Process
  |
  +--> acknowledge success
  |
  +--> retry or dead-letter failure
```

## Basic Steps

1. A producer creates a message with an identifier, type, timestamp, and payload.
2. The producer publishes the message to a queue, topic, stream, or exchange.
3. The broker stores, routes, or exposes the message according to its model.
4. A consumer receives the message.
5. The consumer processes the message.
6. The consumer reports success or failure through an acknowledgement pattern.

## Simple Example

```text
User signs up
  |
  v
User API publishes UserRegistered
  |
  v
Email consumer sends welcome email
```

The user registration should not depend on the email server being fast. The email consumer still needs retries and duplicate protection.

## Common Misconceptions

- Publishing a message does not prove the consumer processed it.
- Acknowledging too early can lose work.
- Retrying without a limit can hide broken messages.
- Dead-lettering is not failure recovery by itself; someone must inspect the problem.

## Review Questions

1. What information should a basic message envelope contain?
2. Why is acknowledging too early dangerous?
3. What should happen after repeated consumer failures?
