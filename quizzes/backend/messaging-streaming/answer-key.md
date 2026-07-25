# Answer Key

## Multiple Choice

1. B
2. B
3. B
4. B
5. A
6. B

## Short Answer

1. Request/response waits for an immediate answer; messaging sends work or facts through a broker for later processing.
2. Each boundary can fail independently, so the final business effect must be protected by storage, acknowledgement timing, and idempotency.
3. Message id, correlation id, timestamp, type, payload, and sometimes headers or schema version.
4. Unlimited retries can trap poison messages and consume worker capacity.
5. It connects the original request, produced message, consumer logs, traces, retries, and failures.
6. Serialization is a contract boundary; domain logic should not depend on broker record APIs.
7. Kafka is a poor fit when a simple direct call or small work queue solves the problem.
8. RabbitMQ is a good fit for routed task queues and flexible exchange/binding patterns.

## Kafka Design Reading

1. Related task records can land in different partitions, losing per-task order.
2. A crash after commit but before write can skip the record for that group.
3. Lag shows how far the group is behind the latest records.
4. Handlers must be replay-safe and idempotent, especially around side effects.
5. Use `taskId` or another stable task key.

## RabbitMQ Routing

1. Topic exchange.
2. `task.*`
3. `task.created` and `task.failed`
4. `task.failed`
5. Fanout sends to every bound queue and cannot express the selective routing alone.
6. Stop retrying after a limit and dead-letter with useful context.

## Java Code Reading

1. The email may be sent twice on redelivery.
2. Check whether the message or operation was already processed.
3. Domain logic should stay portable and testable behind a consumer boundary.
4. Message id, correlation id, message type, attempt, failure class, and outcome.
5. It lets storage enforce that the business effect happens once.

## Operational Troubleshooting

1. Queue depth or lag, consumer throughput, processing latency, retry count, dependency timeout rate, and oldest-message age.
2. Consumer or dependency problem.
3. Whether messages are accumulating and how delayed the oldest work is.
4. Retries use consumer capacity that could otherwise process healthy messages.
5. Message id, correlation id, message type, consumer name, attempt, outcome, and failure class.
6. Scale consumers only if the dependency can handle it, pause unsafe retries, or temporarily reduce noncritical work.
7. Add dependency resilience, alerting on latency/backlog, better retry limits, and a post-incident review item.
