# RabbitMQ Foundations

RabbitMQ is a message broker often used for task queues, routing, request decoupling, and publish/subscribe workflows.

This section is foundational. It does not provision brokers, use cloud-provider services, include credentials, or show unsafe default-password examples.

## Topics

- [RabbitMQ Architecture](rabbitmq-architecture.md)
- [Exchanges Queues Bindings And Routing Keys](exchanges-queues-bindings-routing-keys.md)
- [Exchange Types](exchange-types.md)
- [Acknowledgements Durability Dead Letters And Prefetch](acks-durability-dead-letters-prefetch.md)
- [Strengths Tradeoffs And Mistakes](strengths-tradeoffs-mistakes.md)

## Terminology

| Term | Meaning |
| --- | --- |
| Producer | Application that publishes a message. |
| Exchange | Routing point that receives published messages. |
| Queue | Buffer from which consumers receive messages. |
| Binding | Rule connecting an exchange to a queue. |
| Routing key | Text value used by some exchanges to choose queues. |
| Consumer | Application that receives messages from a queue. |
| Acknowledgement | Consumer signal that processing succeeded. |
| Prefetch | Limit for unacknowledged messages sent to a consumer. |

## Big Idea

RabbitMQ routes messages through exchanges into queues.

```text
Producer ---> Exchange ---> Queue ---> Consumer
```
