# RabbitMQ Retries Dead Letters And Publisher Confirms

## Publisher Confirms

Publisher confirms tell a publisher that RabbitMQ accepted responsibility for a message according to the broker state. They are not consumer acknowledgements. They do not prove that a consumer processed the message or that a business transaction completed.

Java publisher code should treat confirms, returns, and timeouts separately. A timeout leaves uncertain delivery: the broker may have accepted the message even though the client did not observe the confirm. Retrying publication can create duplicates, so message IDs and idempotent consumers remain necessary.

## Dead-Letter Exchanges

RabbitMQ can dead-letter messages when they are rejected without requeue, expire by message TTL, exceed queue length limits, or in some quorum-queue delivery-limit cases. A queue can define a dead-letter exchange and routing key so failed messages move to a repair destination.

Dead-letter records should preserve:

- original exchange, queue, and routing key
- message ID and correlation ID
- failure reason and exception type
- attempt count
- first failure and last failure timestamps
- payload content type and schema version

## Retry Queues And TTL

A common retry design uses a retry exchange and delay queues with message TTL. When a message expires in a retry queue, RabbitMQ dead-letters it back to the work exchange. This approximates delayed retry without sleeping inside consumers.

Use bounded attempts, jitter where the scheduler supports it, and retry budgets. Unbounded retry queues can amplify outages into retry storms.

## Poison Messages

A poison message is not likely to succeed through repetition. Examples include malformed JSON, missing required fields, unsupported schema version, unauthorized tenant, or impossible business state. Poison messages should be quarantined or dead-lettered with enough context for repair.

## Operational Checks

Watch queue depth, unacknowledged counts, publish rates, confirm latency, redelivery counts, dead-letter volume, consumer connection churn, channel exceptions, disk alarms, and memory alarms.

Access control and TLS matter because brokers carry business events, identifiers, and sometimes sensitive payloads. Avoid embedding credentials in examples or committed configuration.
