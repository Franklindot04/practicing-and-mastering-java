# Event-Driven Operational Readiness

An event-driven workflow is production-ready only when teams can observe, diagnose, and recover it. Publication success alone is not enough.

## Metrics

Track technical signals:

- throughput
- consumer lag
- redelivery count
- retry count
- dead-letter volume
- processing latency
- end-to-end latency
- event age
- partition imbalance
- hot keys
- schema failures
- replay progress

Track business outcomes:

- submitted orders
- confirmed orders
- cancelled orders
- inventory releases
- payment rejections
- notification failures

Technical metrics say the pipeline is moving. Business metrics say the workflow is producing the intended outcome.

## Correlation And Causation Tracing

Correlation IDs group related work. Causation IDs explain why one event exists. Together they let operators reconstruct a flow from request to event to consumer reaction.

Logs should include event ID, event type, consumer name, attempt, correlation ID, causation ID, partition key, and outcome.

## Event-Flow Diagrams

Event-flow diagrams show producers, channels, consumers, side effects, retries, dead-letter handling, and ownership. They help teams spot hidden dependencies and unclear recovery paths.

## Runbooks

Runbooks should explain:

- how to identify stuck workflows
- how to inspect dead-lettered events
- who owns each event and consumer
- how to decide replay eligibility
- how to replay safely
- how to verify business recovery
- when to escalate

## Rolling Deployments And Schema Rollout

Rolling deployments mean old and new code can run together. Schema rollout should deploy tolerant consumers before producers require new fields. Operators should monitor validation failures and consumer lag during rollout.

## Incident Response And Recovery Drills

Incident response should name the customer impact, affected event types, consumer lag, dead-letter volume, and recovery plan.

Recovery drills prove the team can replay events, repair dead letters, rebuild read models, and verify outcomes before a real incident forces the issue.
