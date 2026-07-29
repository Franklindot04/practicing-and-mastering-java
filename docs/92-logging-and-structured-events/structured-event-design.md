# Structured Event Design

Structured logging treats each log line as an event with fields. A human message can still exist, but the fields carry the diagnostic value.

## Structured Versus Unstructured

| Style | Example | Tradeoff |
| --- | --- | --- |
| Unstructured | `Payment failed for order 42` | Easy to write, hard to query reliably. |
| Structured | `event=payment.failed operation=checkout outcome=failure orderType=standard` | Easier to aggregate and correlate. |

Prefer stable event names and message templates. Do not build messages by concatenating untrusted input. Preserve newline and delimiter safety so user-provided text cannot forge extra log records.

## Event Naming

Good names are stable and action-oriented:

- `checkout.request.received`
- `checkout.validation.failed`
- `payment.dependency.timed_out`
- `worker.queue.saturated`
- `application.shutdown.started`

Avoid names that contain request IDs, user names, raw URLs, or exception messages. Those values belong in fields after safety review.

## Field Design Checklist

- [ ] Use UTC timestamps.
- [ ] Use stable operation names.
- [ ] Include request ID or job ID when safe.
- [ ] Include trace ID and span ID when a tracing system provides them.
- [ ] Include outcome and status fields.
- [ ] Include explicit duration units.
- [ ] Keep metric-like fields low cardinality.
- [ ] Redact sensitive values before formatting.
- [ ] Distinguish audit fields from diagnostic fields.

## Java Example Concept

```text
event=inventory.reserve.completed
severity=info
operation=reserveInventory
requestId=req-1007
traceId=trace-9c4
outcome=success
durationMs=18
itemCategory=book
```

The example uses fictional IDs and a safe category. It does not include a raw user identifier, payment token, authorization header, or full address.

