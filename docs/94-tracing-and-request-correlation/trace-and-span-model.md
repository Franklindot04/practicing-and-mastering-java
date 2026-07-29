# Trace And Span Model

A trace shows the shape of work. Spans describe operations, their timing, status, and parent-child relationships.

```text
trace checkout req-42
  root span: HTTP POST /checkout
    child span: validate request
    child span: reserve inventory
    child span: charge payment
      child span: retry payment attempt
    child span: persist order
```

Span names should be stable operation names, not raw URLs, IDs, exception messages, or user input. Attributes should describe useful context such as `dependency=payment`, `attempt=2`, `outcome=timeout`, or `queue=checkout`.

## Parent-Child Relationships

Parent-child relationships show causality: one operation directly caused another. Links can connect related work when there is no strict parent, such as batching, fan-in, fan-out, or a message produced by one trace and consumed later.

## Resource Context

Resource context identifies the emitting service or process: service name, version, environment, and runtime. Keep it safe. Do not expose internal host details or build metadata publicly unless the audience and risk are understood.

## Latency Breakdown

Trace waterfalls help reason about critical path:

- Which operation took most of the time?
- Did fan-out run concurrently or sequentially?
- Did retries consume the budget?
- Did cancellation stop downstream work?
- Is a missing span hiding the slow part?

Traces explain timing relationships. They do not automatically prove root cause.

