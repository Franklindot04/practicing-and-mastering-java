# Networks Latency And Message Delivery

Distributed systems communicate by messages. A message may be delayed, lost, duplicated, reordered, delivered to an old version of code, or processed successfully while the acknowledgement is lost.

## Latency And Throughput

Latency is the time for one operation to complete. Throughput is the amount of work completed over time. A Java service can have high throughput while individual requests experience high tail latency.

```java
Duration deadline = Duration.ofMillis(250);
Instant startedAt = clock.instant();
```

A deadline should represent the caller's remaining patience, not only the server's average response time. A request that arrives after the caller has already given up can still consume CPU, database connections, and locks.

## Message Loss

Message loss can happen before the server receives a request, while the server sends a response, or while an intermediate system forwards data. The caller often cannot distinguish these cases.

Production consequence: a retry can create duplicates if the first request committed but the response was lost.

## Message Duplication

Duplicates are normal when clients retry, load balancers replay, operators rerun jobs, or consumers restart after processing but before recording progress.

Java-oriented mitigation often starts with stable identifiers:

```java
record CommandId(UUID value) { }
record CreateInvoice(CommandId commandId, String accountId, long cents) { }
```

The identifier lets the receiver recognize that two messages represent the same intended command. It does not make delivery exactly-once; it gives the application a way to handle repeats.

## Message Reordering

Two messages sent in one order can arrive in another, especially if they take different routes, are retried, or are handled by different workers.

Example:

1. `OrderAddressChanged`
2. `OrderShipped`

If `OrderShipped` is processed first, a naive consumer might reject it because it has not seen the address update. The fix depends on the domain: sequence numbers, version checks, buffering, idempotent state transitions, or rejecting unsafe transitions.

## Network Success Is Not Application Success

An HTTP 200 or successful socket write proves only that some network exchange completed. It does not prove the business operation is durable, replicated, authorized, or visible to later reads.

Likewise, network failure does not prove application failure. The server might have committed and then failed to respond.

## Retries Can Amplify Failure

Retries are useful when failure is transient. They are dangerous when every client retries immediately during overload.

Failure amplification pattern:

1. a dependency slows down
2. callers time out
3. callers retry without jitter or budget
4. the dependency receives more work than before
5. queues grow and latency worsens

Retries need deadlines, attempt limits, backoff, jitter, idempotency, and observability.

## Review Questions

1. Why can a retry create a duplicate command?
2. How can message reordering break a state machine?
3. What does a successful network response not prove?
4. Why should retries have a budget?
