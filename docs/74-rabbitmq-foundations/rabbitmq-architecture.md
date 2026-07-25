# RabbitMQ Architecture

RabbitMQ receives messages from producers and routes them to queues through exchanges.

```text
Java producer
  |
  v
Exchange
  |
  +--> Queue A ---> Consumer A
  |
  +--> Queue B ---> Consumer B
```

## Producers

Producers publish messages to an exchange. The producer may include a routing key and message properties.

## Exchanges

An exchange decides which queues should receive a message.

The exchange type controls the routing behavior.

## Queues

A queue stores messages until consumers receive them.

Queues are commonly used for background work and competing consumers.

## Consumers

Consumers receive messages from queues and acknowledge successful handling.

## Java-Oriented Example

A Java task API could publish a `task.created` message to an exchange. A notification queue and an audit queue could each receive the message using different bindings.

## Review Questions

1. What does an exchange do?
2. What does a queue do?
3. Why does RabbitMQ routing involve bindings?
