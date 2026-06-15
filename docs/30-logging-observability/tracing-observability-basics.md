# Tracing And Observability Basics

Tracing follows a request as it moves through code. In a small application, tracing may simply mean carrying a request ID through logs. In larger systems, distributed tracing follows a request across multiple services.

## Why Tracing Helps

When an API is slow or failing, you need to know where time was spent and where the failure happened.

Questions tracing can help answer:

- Did the request reach the controller?
- Did validation pass?
- Did service logic run?
- Did a repository call fail?
- Which downstream service was slow?

This repository does not introduce distributed tracing tools yet. Learn the mental model first.

## Basic Span Concept

A span represents one unit of work.

Example request:

```text
request: POST /api/tasks
  controller span
  validation span
  service span
  repository span
```

Each span has timing and status. Real tracing systems connect spans into a trace.

## Observability Questions

Good observability helps answer:

- What changed?
- What failed?
- How many users or requests were affected?
- Is the failure still happening?
- Which component should be inspected first?

## Beginner-Friendly Starting Point

For now, practice:

- Clear log messages.
- Consistent request IDs.
- Safe error responses.
- Simple health checks.
- Tests that cover important behavior.

Add advanced tracing tools later, after the app has clear structure and a reason to use them.
