# Distributed Tracing Concepts

Distributed tracing records the path of a request through multiple services or components.

## Trace And Span

A trace represents one end-to-end request or workflow.

A span represents one operation inside that trace.

```text
Trace: create task request

Span 1: API receives request
Span 2: API writes task
Span 3: API sends notification command
Span 4: worker sends notification
```

## Why Tracing Helps

Tracing helps answer:

- Which service was slow?
- Which dependency failed?
- How many retries happened?
- Did background work continue after the user response?
- Which path did this request take?

## Tracing Is Not Enough

Traces should be used with logs, metrics, and business state. A trace may show a failed call, but logs and status records explain whether the workflow was recovered.

## Beginner Caution

Do not add tracing vocabulary to hide unclear boundaries. First understand the workflow. Then use tracing to make the workflow visible.

