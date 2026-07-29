# Tracing And Request Correlation

Distributed tracing connects related work across service, thread, dependency, messaging, retry, and background-job boundaries. Request correlation is the broader practice of connecting diagnostic evidence for the same unit of work.

## Study Order

1. [Trace And Span Model](trace-and-span-model.md)
2. [Context Propagation And Asynchronous Work](context-propagation-and-asynchronous-work.md)
3. [Sampling, Correlation, And Trace Diagnostics](sampling-correlation-and-trace-diagnostics.md)

## Core Terms

| Term | Meaning |
| --- | --- |
| Trace | A group of related spans for one request, job, or workflow. |
| Span | One timed operation inside a trace. |
| Root span | The first span for a unit of work. |
| Parent span | The span that caused another span. |
| Attribute | Safe key/value context attached to a span. |
| Event | Point-in-time note inside a span. |
| Status | Whether the span completed successfully, failed, or was cancelled. |

Correlation IDs are stable identifiers used to find related logs, metrics exemplars, traces, and diagnostic events. Trace IDs identify traces. A system may have both.

## Review Questions

1. Why is a trace more than a request ID?
2. Where can context be lost in Java asynchronous code?
3. What is the difference between sampling and filtering?
4. Why should trace attributes avoid secrets and high-cardinality values?
5. How can missing spans mislead an investigation?

