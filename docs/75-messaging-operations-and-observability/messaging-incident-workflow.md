# Messaging Incident Workflow

Use a calm, repeatable workflow during messaging incidents.

## Workflow

1. Confirm user impact.
2. Identify affected message types, queues, topics, or consumers.
3. Check producer publish success and failure rates.
4. Check consumer throughput and processing latency.
5. Check backlog, lag, queue depth, and oldest-message age.
6. Inspect retry and dead-letter volume.
7. Sample failures by correlation id and message id.
8. Decide whether the issue is producer, broker path, consumer, dependency, or payload related.
9. Stop unsafe replay or retry loops.
10. Apply the smallest safe mitigation.
11. Record follow-up work.

## Common Operational Mistakes

- Looking only at broker health and ignoring consumers.
- Scaling blindly without checking dependency limits.
- Removing dead letters without understanding business impact.
- Forgetting to notify owners of delayed asynchronous work.
- Fixing one message but not the class of failure.

## Review Questions

1. Why should oldest-message age be monitored?
2. What is the first thing to confirm during an incident?
3. Why can retry volume reduce useful throughput?
