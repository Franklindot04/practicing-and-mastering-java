# Messaging And Streaming Answer Key

## MC1

B. Broker acceptance is scoped to broker policy. It does not prove consumer completion or external side effects.

## MC2

B. `orderId` keeps one order's records on the same partition. Random keys spread load but break per-order order.

## MC3

B. Lag is distance between produced offsets and consumed or committed offsets.

## MC4

C. Fanout exchanges broadcast to all bound queues.

## MC5

A. Prefetch limits unacknowledged in-flight deliveries.

## MC6

C. JMS is a Java API implemented by providers. Operations and redelivery behavior can vary.

## MC7

A. Optional additive fields with defaults are usually easier for tolerant consumers.

## MC8

B. A consumer can complete a side effect and crash before acknowledgement or commit.

## MC9

A. Poison messages usually need repair or quarantine rather than repeated retry.

## MC10

C. Replay should not repeat emails, payments, or other protected side effects unless guarded.

## MC11

A. Event time is when the business event occurred.

## MC12

A. Unacknowledged count shows deliveries held by consumers.

## SA1

Queue competing consumers divide work so one logical message is handled by one consumer instance in the group. Topic subscribers each receive independent delivery or position, enabling fan-out to multiple reactions.

## SA2

If the offset is committed first and the process dies before the database write, Kafka will treat the record as handled and the side effect may never happen.

## SA3

Publisher confirms tell the publisher the broker accepted a message. Consumer acknowledgements tell the broker a delivery was handled or can be removed from that queue or position.

## SA4

Examples include redelivery policy, dead-letter configuration, clustering, persistence tuning, selectors, monitoring, security setup, and operational tooling.

## SA5

Backward compatibility means new consumers can read old messages. Forward compatibility means old consumers can survive newer messages, often by ignoring unknown fields and relying on defaults.

## SA6

Bounded retries prevent infinite work, retry storms, delayed recovery, and hidden poison-message loops.

## SA7

Use a grace period, update the prior window, emit correction events, route very late records to a late-events topic, or trigger manual repair.

## SA8

Inspect producer errors, broker health, queue depth, consumer lag, processing latency, acknowledgement or commit failures, retry volume, dead-letter volume, schema failures, and tracing IDs.

## AS1

A strong design uses Kafka topics for retained order events keyed by `orderId`, or RabbitMQ exchanges for command-like routing where appropriate. Consumers commit or acknowledge after idempotent side effects. Retryable failures go to bounded retry infrastructure; malformed or exhausted messages go to dead letters. Replay rebuilds projections with external side effects disabled. Observability includes message ID, correlation ID, lag, queue depth, retry volume, dead-letter volume, and processing latency.

## AS2

Kafka fits high-throughput retained streams, replay, consumer groups, offsets, and analytics. RabbitMQ fits exchange-based routing, work queues, direct/topic/fanout delivery, and operational commands. JMS can help Java portability across JMS providers, but does not make Kafka follow JMS semantics or make provider operations identical.

## AS3

Add optional `currency`, `amountCents`, and `processorReference` with defaults or nullable handling. Deploy tolerant readers before requiring new fields. Keep `v1` examples in contract tests, reject malformed payloads clearly, and avoid renaming existing fields in place.

## AS4

Filter payment events, map to revenue amounts, group by event-time five-minute windows, maintain a state store with a changelog, use watermarks and grace periods, emit corrections for late data, and support replay from retained events to restore state.

## FA1

The risk is duplicate side effects after redelivery. Use an inbox/idempotency store or unique constraint and acknowledge only after the protected transaction completes.

## FA2

Malformed JSON is poison, so requeueing creates a loop. Reject without requeue to a dead-letter exchange, alert the owner, inspect payloads, fix producer or contract validation, and replay repaired messages deliberately.

## FA3

Uncommitted records can be processed again by another consumer after reassignment. Reduce risk with idempotent handlers, revoke callbacks, shorter batches, processing timeouts, and careful manual commits.

## FA4

The timeout created uncertain delivery: the broker may have accepted the first send. Retrying can duplicate the business fact. Use stable message IDs, idempotent producers where available, duplicate-safe consumers, and outcome metrics.

## FA5

The replay used normal side-effecting handlers. Safer replay separates projection rebuild mode from external side effects, enforces idempotency, records operator intent, and validates output before resuming normal processing.

## FA6

Options include updating the old window within a grace period, emitting correction records after publication, routing very late data to a late-events workflow, or manual repair. Silently ignoring late events should be a deliberate business decision.
