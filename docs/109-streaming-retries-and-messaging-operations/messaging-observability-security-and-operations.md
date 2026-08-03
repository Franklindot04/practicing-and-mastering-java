# Messaging Observability Security And Operations

## Signals

Messaging observability should connect producer, broker, consumer, and business outcome. Useful metrics include throughput, publish latency, processing latency, end-to-end latency, acknowledgement failures, commit failures, queue depth, consumer lag, retry volume, dead-letter volume, redelivery, hot partitions, partition imbalance, unacknowledged messages, and rebalance frequency.

Trace propagation should use correlation IDs and causation IDs. Logs should include message ID, event type, version, destination, partition or routing key, attempt count, and outcome. Avoid logging sensitive payloads by default.

## Broker Health

Kafka operators watch under-replicated partitions, offline partitions, controller health, disk usage, retention pressure, request latency, and consumer group state. RabbitMQ operators watch node alarms, queue depth, unacknowledged messages, redelivery rate, connection churn, channel errors, and dead-letter volume.

## Security

Broker access should use least privilege. Producers should only publish to allowed destinations. Consumers should only read their destinations. TLS, credential rotation, audit logs, and secret management are production concerns even when examples use local fakes.

Message payloads are untrusted input. Validate size, content type, schema version, tenant authorization, and required fields before business handling.

## Runbooks

Runbooks should cover lag spikes, retry storms, poison messages, schema failures, credential expiration, broker disk pressure, partition imbalance, replay, dead-letter repair, consumer deployment rollback, and backup or recovery procedures.

Operational readiness is not proven by an in-memory simulation or a local integration test. Those tests are useful learning tools, but production readiness also requires capacity planning, security review, monitoring, ownership, and incident practice.
