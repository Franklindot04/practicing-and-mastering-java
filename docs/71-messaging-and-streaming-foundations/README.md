# Messaging And Streaming Foundations

Messaging and streaming let Java backend services communicate without every service calling every other service directly.

This section connects the event-driven architecture notes to concrete communication models. It stays vendor-neutral and does not require Kafka, RabbitMQ, cloud services, credentials, or production infrastructure.

## Topics

- [Messaging Basics](messaging-basics.md)
- [Streaming Basics](streaming-basics.md)
- [Queues Topics Producers And Consumers](queues-topics-producers-consumers.md)
- [Message Lifecycle](message-lifecycle.md)
- [Benefits Tradeoffs And When Not To Use](benefits-tradeoffs-when-not-to-use.md)

## Learning Goals

After this section, you should be able to:

- Explain messaging and streaming in plain language.
- Compare messaging with direct request/response calls.
- Describe queues, topics, producers, consumers, and brokers.
- Distinguish messages from events.
- Explain competing consumers and publish/subscribe.
- Identify when messaging is useful and when it is unnecessary.

## Big Idea

Messaging adds a communication layer between services.

```text
Direct call:

Service A ---> Service B

Messaging:

Service A ---> Broker ---> Service B
```

That middle layer can reduce temporal coupling, but it also adds operational responsibility.
