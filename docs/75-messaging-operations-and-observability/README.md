# Messaging Operations And Observability

Messaging systems need observability because work often happens after the original request is gone.

This section starts with vendor-neutral guidance. Kafka and RabbitMQ terminology appears only in clearly labelled comparison notes. No monitoring infrastructure, credentials, or provider-specific setup is included.

## Topics

- [Health Signals And Metrics](health-signals-and-metrics.md)
- [Tracing Logging And Correlation](tracing-logging-correlation.md)
- [Replay Backlog And Slow Consumer Investigation](replay-backlog-slow-consumer-investigation.md)
- [Poison Message And Dead Letter Investigation](poison-message-dead-letter-investigation.md)
- [Kafka And RabbitMQ Terminology Comparison](kafka-rabbitmq-terminology-comparison.md)
- [Messaging Incident Workflow](messaging-incident-workflow.md)

## Learning Goals

After this section, you should be able to:

- Identify useful producer and consumer health signals.
- Explain queue depth, consumer lag, latency, retry count, and dead-letter volume.
- Use correlation identifiers across asynchronous flows.
- Investigate backlog, slow consumers, poison messages, and replay risks.
- Avoid common messaging operational mistakes.
