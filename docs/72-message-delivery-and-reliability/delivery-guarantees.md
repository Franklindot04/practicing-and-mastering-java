# Delivery Guarantees

Delivery guarantees describe what a system attempts to do when messages move from producers to brokers to consumers.

They are not only broker settings. They depend on the complete workflow.

```text
Producer ---> Broker ---> Consumer ---> Database or side effect
```

## At-Most-Once

At-most-once means a message is processed zero or one time.

If something fails after the broker considers the message delivered, the message may be lost.

Use this only when losing occasional messages is acceptable, such as low-value telemetry in a learning system.

## At-Least-Once

At-least-once means the system tries not to lose messages, but a message may be delivered more than once.

This is common because redelivery is safer than silent loss for important business work.

The consumer must be idempotent.

## Exactly-Once Concepts

Exactly-once means each logical message effect happens once.

This is difficult because the message may pass through several boundaries:

- Producer publish confirmation
- Broker storage
- Consumer delivery
- Consumer acknowledgement
- Database write
- External side effect

If the consumer writes to a database and then crashes before acknowledging, the broker may redeliver the message. The consumer must recognize the duplicate.

## Practical Rule

Treat exactly-once as an end-to-end design problem, not a magic broker checkbox.

## Review Questions

1. Why is at-least-once delivery compatible with duplicate messages?
2. Why does exactly-once require storage design?
3. Which guarantee would you avoid for payment processing?
