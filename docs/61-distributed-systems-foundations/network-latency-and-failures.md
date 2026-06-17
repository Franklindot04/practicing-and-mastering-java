# Network Latency And Failures

Local method calls feel immediate and predictable. Network calls are slower and less reliable because they depend on another process, another machine, and the path between them.

## Latency

Latency is the time between sending a request and receiving a response. In a distributed system, latency can come from:

- Network distance.
- Queueing inside a service.
- Database calls.
- Retries.
- Slow downstream dependencies.
- Serialization and deserialization.

Small delays can add up when one request calls several services.

```text
Client -> API -> Service A -> Service B -> Database
          20ms     50ms        80ms        40ms
```

The user experiences the total path, not just the fastest component.

## Partial Failures

A partial failure happens when one part of the system fails while other parts keep running.

Examples:

- The API is healthy, but the payment service is unavailable.
- A write succeeds, but the response is lost.
- Service A can reach Service B, but Service C cannot.
- A retry creates a duplicate request.

## Timeouts

A timeout is a decision to stop waiting. Without timeouts, one slow dependency can consume threads, connections, and memory until more parts of the system fail.

Timeouts should be chosen deliberately. Too short can fail healthy work. Too long can make callers wait and pile up.

## Failure-Aware Thinking

For every network call, ask:

- What happens if the call is slow?
- What happens if it fails?
- What happens if it succeeds but the response is lost?
- Is it safe to retry?
- How will we observe the failure?

