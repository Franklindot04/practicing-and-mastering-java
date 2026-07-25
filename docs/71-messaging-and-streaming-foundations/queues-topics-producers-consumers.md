# Queues Topics Producers And Consumers

Messaging systems use a few recurring building blocks.

## Producers

A producer sends a message.

```text
Task API ---> TaskCreated message
```

Producer responsibilities usually include choosing the message type, creating a useful payload, assigning identifiers, and handling publish failures.

## Consumers

A consumer receives and processes a message.

```text
TaskCreated message ---> Search Index Consumer
```

Consumer responsibilities usually include validation, idempotency, error handling, acknowledgements, and observability.

## Queues

A queue is commonly used when each message should be processed by one worker from a group.

```text
Queue
  |
  +--> Worker A
  +--> Worker B
  +--> Worker C
```

If Worker A takes one message, Worker B should not process that same message at the same time in the normal case.

## Competing Consumers

Competing consumers are multiple consumers sharing work from the same queue.

This helps scale processing, but it can affect ordering because different messages may be handled by different workers at different speeds.

## Topics

A topic is commonly used when multiple independent subscribers need the same message.

```text
Topic: task-events
  |
  +--> Audit Consumer
  +--> Notification Consumer
  +--> Reporting Consumer
```

Each subscriber can react for its own purpose.

## Publish Subscribe

Publish/subscribe means the publisher sends a message to a topic or exchange, and subscribers receive messages based on subscription rules.

The producer does not need to know every subscriber.

## Review Questions

1. Why does a queue fit background work?
2. Why does a topic fit independent reactions?
3. What can go wrong if competing consumers require strict ordering?
