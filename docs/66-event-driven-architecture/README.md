# Event-Driven Architecture Foundations

Event-driven architecture is a way to design systems around meaningful things that happen in the business or application.

This section is conceptual. It does not require a message broker, queue, stream, cloud service, or local infrastructure. The goal is to learn the vocabulary and tradeoffs before choosing any technology.

## Topics

- [What Is Event-Driven Architecture](what-is-event-driven-architecture.md)
- [Events Vs Commands](events-vs-commands.md)
- [Synchronous Vs Asynchronous Communication](synchronous-vs-asynchronous-communication.md)
- [Producers And Consumers](producers-and-consumers.md)
- [Event Brokers Concept](event-brokers-concept.md)
- [Event Lifecycle](event-lifecycle.md)
- [Benefits And Tradeoffs](benefits-and-tradeoffs.md)

## Learning Goals

After this section, you should be able to:

- Explain what event-driven architecture means without naming a vendor or tool.
- Distinguish facts that happened from requests for work.
- Compare synchronous request/response flows with asynchronous event flows.
- Identify producers, consumers, and conceptual broker responsibilities.
- Describe the life of an event from creation to handling.
- Recognize the benefits and costs of event-driven design.

## Big Idea

In a direct request flow, one component asks another component for an immediate response.

```text
Client
  |
  v
Task API ----request----> Email Component
  ^
  |
response
```

In an event-driven flow, one component records that something happened, and other components may react later.

```text
Task API
  |
  v
TaskCreated event
  |
  +--> Notification handler
  |
  +--> Reporting handler
  |
  +--> Audit handler
```

The event is not a command to a specific handler. It is a statement of fact that other parts of the system can observe.
