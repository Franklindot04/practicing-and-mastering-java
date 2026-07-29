# Context Propagation And Asynchronous Work

Trace context must cross boundaries deliberately. HTTP calls, message headers, executor tasks, scheduled jobs, and callbacks can all lose context.

## Propagation Boundaries

| Boundary | Risk | Safer habit |
| --- | --- | --- |
| HTTP client/server | Missing or overwritten headers. | Propagate approved trace and correlation headers. |
| Executor service | `ThreadLocal` context stays on the wrong thread or leaks. | Capture, install, and clear context around the task. |
| Messaging | Producer and consumer run at different times. | Put safe correlation fields in message metadata. |
| Background job | No caller request exists. | Create a new root context with a job ID. |
| Retry | Attempts blur together. | Record attempt number and parent relationship. |

OpenTelemetry concepts such as trace context, spans, attributes, links, resources, and sampling are useful vendor-neutral vocabulary. This section does not add an SDK, collector, agent, or exporter.

## Fan-Out, Fan-In, Batching, And Cancellation

Fan-out creates multiple child operations. Fan-in combines their results. Batching may link many source spans to one batch span instead of pretending one parent caused everything. Timeouts and cancellation should be visible so learners can distinguish slow work from stopped work.

## Context-Loss Investigation Checklist

- [ ] Do logs have a request ID but traces do not?
- [ ] Do spans stop at an executor, queue, or callback?
- [ ] Are retries visible as separate attempts?
- [ ] Do background jobs create their own root context?
- [ ] Is context cleared after pooled-thread work?
- [ ] Are message metadata fields safe and bounded?
- [ ] Are trace IDs and correlation IDs confused?

