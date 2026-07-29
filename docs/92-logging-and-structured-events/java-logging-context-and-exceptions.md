# Java Logging Context And Exceptions

Java applications often use a logging facade so application code is separated from the concrete logging implementation. SLF4J-style parameterized logging keeps templates stable:

```java
logger.info("operation={} outcome={} durationMs={}", operation, outcome, durationMs);
```

The important habit is the shape of the event, not a specific vendor.

## Contextual Logging

Mapped Diagnostic Context, often called MDC, stores contextual fields such as request ID or operation name so log events on the same thread can include them. MDC-like context is useful, but it has risks:

- Thread pools reuse threads, so context must be cleared.
- Asynchronous work can lose context unless it is explicitly propagated.
- `ThreadLocal` state can leak between requests when cleanup is missed.
- Context should contain safe identifiers, not secrets or raw personal data.

Prefer explicit context passing in core business code. Use MDC-like mechanisms at boundaries where the logging system needs fields.

## Exception Logging

Preserve exception chains. A wrapper exception may describe the operation, while the root cause may describe the low-level failure.

| Good practice | Why |
| --- | --- |
| Log once at the boundary that handles the failure. | Avoids duplicate stack traces and mixed severity. |
| Include exception type and root-cause type. | Helps classify failures safely. |
| Keep a stable error code or category. | Supports grouping and dashboards. |
| Avoid raw stack traces in user responses. | Prevents internal detail leakage. |

Stack traces can be useful in controlled diagnostic logs, but they may include paths, SQL text, headers, or values from exception messages. Review what can appear before logging broadly.

## Boundary Logging

Log at boundaries:

- Request received and completed when useful.
- Validation rejected.
- Dependency call failed, timed out, or retried.
- Background job started, completed, failed, or skipped.
- Startup, shutdown, and configuration changes.

Avoid logging the same exception at every layer. The service that catches, classifies, and decides the outcome usually has the best context.

