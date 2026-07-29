# Sampling, Correlation, And Trace Diagnostics

Sampling reduces trace volume. It should reduce diagnostic cost without changing business behavior.

## Sampling Styles

| Style | Idea | Tradeoff |
| --- | --- | --- |
| Head sampling | Decide near the start of a trace. | Cheap, but may miss later errors. |
| Tail sampling | Decide after seeing more of the trace. | More informed, but more complex. |
| Probabilistic sampling | Keep a percentage of traces. | Simple, but rare failures may be missed. |
| Error-biased sampling | Prefer traces with failures. | Good for incidents, but can distort normal-shape views. |

Filtering removes data after it exists. Sampling decides what to keep. Both need privacy review.

## Correlating Signals

Trace-log correlation lets a log event include trace and span identifiers. Trace-metric correlation can use exemplars as a concept: a metric observation points to a representative trace. Metrics still need low-cardinality labels; traces and logs can carry high-cardinality correlation IDs when safe.

## Diagnostic Limits

Common trace anti-patterns:

- Missing spans around dependency calls.
- Raw user input or secrets in attributes.
- Span names containing IDs.
- Ignoring asynchronous work.
- Sampling all slow traces away by accident.
- Treating partial traces as complete evidence.
- Forgetting clock skew between processes.

Partial traces can still help, but they should be described honestly. An instrumentation gap is not the same as a service failure.

## Request-Correlation Testing

Tests should verify behavior, not exact timing:

- A child operation receives the expected safe context.
- Pooled-thread work clears context after completion.
- Missing context creates a new root only where appropriate.
- Retried attempts keep the same request correlation and distinct attempt fields.
- Sensitive fields are rejected or redacted.

