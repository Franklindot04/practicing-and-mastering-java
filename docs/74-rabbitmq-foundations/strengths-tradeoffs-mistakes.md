# Strengths Tradeoffs And Mistakes

RabbitMQ is useful for routing and work distribution, but it should not be treated as interchangeable with Kafka.

## Strengths

- Flexible routing through exchanges.
- Strong fit for work queues.
- Good support for publish/subscribe through exchange and queue design.
- Consumer acknowledgements are central to the model.
- Prefetch gives useful control over in-flight work.

## Tradeoffs

- Routing topology can become hard to understand.
- Dead-letter flows need operational ownership.
- Large replayable event history is not the core model.
- Ordering can be affected by competing consumers and retries.
- Durable messaging still requires idempotent consumers.

## When RabbitMQ Is Not Appropriate

RabbitMQ may be unnecessary when:

- A direct method call is enough.
- The application needs long-term replayable streams as the primary model.
- The team cannot operate queues, dead letters, and consumers.
- Routing rules are simple enough to stay inside one process.

## Common Mistakes

- Publishing directly to a queue mentally and ignoring exchange design.
- Retrying poison messages forever.
- Forgetting to set useful correlation identifiers.
- Treating dead-letter queues as a place where problems disappear.
- Using prefetch without understanding consumer processing time.

## Review Questions

1. What makes RabbitMQ routing flexible?
2. Why is RabbitMQ not the same thing as Kafka?
3. What is one common dead-letter mistake?
