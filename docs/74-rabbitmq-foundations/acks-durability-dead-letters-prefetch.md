# Acknowledgements Durability Dead Letters And Prefetch

RabbitMQ reliability depends on queue configuration, message properties, consumer acknowledgements, and application behavior.

## Acknowledgements

A consumer acknowledges a message after successful processing.

If a consumer fails before acknowledgement, RabbitMQ can make the message available again.

## Message And Queue Durability

Durability concepts are about surviving broker restarts.

Durable queues and persistent messages are related, but one does not replace the other. Application-level correctness still depends on idempotent consumers and safe acknowledgement timing.

## Dead-Letter Concepts

A dead-letter exchange can receive messages that cannot be processed normally.

Reasons may include:

- Rejected messages
- Expired messages
- Retry limits implemented by application design
- Queue limits

## Prefetch

Prefetch limits how many unacknowledged messages RabbitMQ sends to a consumer.

Lower prefetch can help fairness and reduce the amount of in-flight work during failures. Higher prefetch can improve throughput for some workloads.

## Review Questions

1. Why should consumers acknowledge after successful processing?
2. What does prefetch control?
3. Why is durability not the same as end-to-end correctness?
