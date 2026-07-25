# Replay Backlog And Slow Consumer Investigation

Backlogs and slow consumers are common messaging incidents.

## Backlog Investigation

Ask:

1. Did producer volume increase?
2. Did consumer throughput decrease?
3. Did a dependency become slow?
4. Are retries consuming capacity?
5. Is one message repeatedly failing?

## Slow Consumer Investigation

Look for:

- Increased processing latency
- Database or API dependency timeouts
- Larger payloads
- Reduced consumer instances
- Too much in-flight work
- Lock contention or thread starvation

## Message Replay Safety

Replay can repair projections or rebuild read models, but only when handlers are safe.

Replay checklist:

- Is the handler idempotent?
- Does it call external side effects?
- Can duplicate emails, charges, or writes occur?
- Is the replay scope limited?
- Will operators observe progress and failures?

## Common Mistakes

- Scaling consumers before understanding the bottleneck.
- Replaying messages into unsafe side-effect handlers.
- Ignoring oldest-message age.
- Treating backlog as only a broker problem.
