# Benefits Tradeoffs And When Not To Use

Messaging and streaming are useful tools, not automatic upgrades.

## Benefits

- Services can be less temporally coupled.
- Slow work can move outside the main request.
- Multiple consumers can react to the same fact.
- Backlogs can absorb short spikes in work.
- Streams can preserve history for projections, analytics, and replay.

## Tradeoffs

- Failures become less visible than direct call failures.
- Debugging often crosses process and time boundaries.
- Consumers must handle duplicates.
- Ordering is limited by the broker model and consumer design.
- Operations now include broker health, lag, queue depth, retries, and dead letters.

## When Messaging Is Unnecessary

Messaging may be unnecessary when:

- The caller needs an immediate answer.
- The application is small and a direct method call is enough.
- There is only one service and no meaningful asynchronous work.
- The team is not ready to operate the broker.
- A database transaction already solves the local consistency problem.

## Practical Decision Check

Ask:

1. What coupling problem are we solving?
2. What happens if the consumer is down?
3. How will duplicates be handled?
4. How will failures be observed?
5. Who owns the message contract?

## Review Questions

1. Name two benefits of messaging.
2. Name two operational costs of messaging.
3. When would a direct request/response call be a better choice?
