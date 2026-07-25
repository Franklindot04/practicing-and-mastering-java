# Exchanges Queues Bindings And Routing Keys

RabbitMQ routing depends on how exchanges, queues, bindings, and routing keys fit together.

## Bindings

A binding connects an exchange to a queue.

```text
Exchange: task.events
  |
  +-- binding task.created ---> task-email-queue
  |
  +-- binding task.*       ---> task-audit-queue
```

## Routing Keys

A routing key is a string provided by the producer when it publishes a message.

Examples:

- `task.created`
- `task.closed`
- `invoice.paid`

Some exchange types use routing keys directly. Some do not.

## Queue-Style Delivery

Multiple consumers can compete for messages from one queue.

```text
work.queue
  |
  +--> Worker 1
  +--> Worker 2
```

Each message normally goes to one worker.

## Publish Subscribe

Multiple queues can receive copies of a message.

```text
Exchange
  |
  +--> audit.queue
  +--> email.queue
  +--> reporting.queue
```

Each queue can then have its own consumers.

## Review Questions

1. What is a binding?
2. Why are routing keys useful?
3. How can RabbitMQ support both work queues and publish/subscribe?
