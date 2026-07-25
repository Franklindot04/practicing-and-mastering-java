# Exchange Types

RabbitMQ exchange types route messages in different ways.

## Direct Exchange

A direct exchange routes by exact routing key match.

```text
routing key: task.created

Direct exchange
  |
  +-- task.created ---> task-created-queue
```

## Topic Exchange

A topic exchange routes by pattern.

```text
routing key: order.created.eu

Topic exchange
  |
  +-- order.*.eu ---> eu-order-queue
  +-- order.#    ---> all-order-queue
```

## Fanout Exchange

A fanout exchange broadcasts to bound queues and ignores routing keys.

```text
Fanout exchange
  |
  +--> audit.queue
  +--> notification.queue
```

## Headers Exchange

A headers exchange routes based on message headers rather than a routing key.

This can be useful for metadata-based routing, but it is less common in beginner Java backend examples.

## Review Questions

1. Which exchange type uses exact routing-key matches?
2. Which exchange type broadcasts to all bound queues?
3. Why should a team choose exchange type based on routing needs?
