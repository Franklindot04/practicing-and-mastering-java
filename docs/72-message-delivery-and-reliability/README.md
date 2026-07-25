# Message Delivery And Reliability

Message reliability is the combined result of producer behavior, broker behavior, consumer behavior, and data storage behavior.

No broker guarantee is complete by itself. A Java backend must still decide when to publish, when to acknowledge, how to retry, how to store progress, and how to make consumers safe to run more than once.

## Topics

- [Delivery Guarantees](delivery-guarantees.md)
- [Acknowledgements And Retries](acknowledgements-and-retries.md)
- [Dead Letters Poison Messages And Duplicates](dead-letters-poison-messages-duplicates.md)
- [Idempotent Consumers And Ordering](idempotent-consumers-and-ordering.md)
- [Failure Recovery Examples](failure-recovery-examples.md)

## Learning Goals

After this section, you should be able to:

- Compare at-most-once, at-least-once, and exactly-once concepts.
- Explain why exactly-once is difficult in real systems.
- Describe acknowledgements, negative acknowledgements, retries, backoff, and retry limits.
- Explain dead-letter queues and poison messages.
- Design consumers that tolerate duplicate messages.
- Explain ordering limits in queue and stream systems.
