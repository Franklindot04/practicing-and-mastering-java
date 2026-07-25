# Messaging Basics

Messaging is a style of communication where one part of a system sends a message to another part through a broker or channel.

The sender usually does not call the receiver directly. It gives the message to messaging infrastructure, and a consumer receives it later.

## Direct Request Response

In a direct request/response call, the caller waits for the callee.

```text
API ---> Payment Service
 |          |
 |<---------+
```

This is simple when the caller needs an immediate answer.

## Messaging

In messaging, the sender records work or information for later processing.

```text
Order API ---> message broker ---> Email Consumer
```

The order API can finish the main request even if the email consumer is temporarily slow.

## Messages Versus Events

A message is the unit of communication. It is an envelope that moves through the system.

An event is a fact that something happened.

```text
Message envelope
  id: msg-1001
  type: OrderCreated
  correlationId: checkout-42
  payload: event data
```

Many event-driven systems send events inside messages.

## Message Brokers

A message broker accepts messages from producers and makes them available to consumers.

The broker may support queues, topics, acknowledgements, routing, retention, retries, or dead-letter behavior depending on the technology.

## Common Misconceptions

- Messaging does not automatically make a system reliable.
- Asynchronous communication does not remove the need for error handling.
- A broker is not a substitute for clear domain boundaries.
- A message is not always an event; it can also be a command-like work request.

## Review Questions

1. Why might a backend send a message instead of calling another service directly?
2. What is the difference between a message and an event?
3. What new responsibility appears when a broker is introduced?
