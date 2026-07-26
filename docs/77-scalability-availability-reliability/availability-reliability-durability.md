# Availability Reliability And Durability

These terms describe different promises. Mixing them up leads to vague designs.

## Availability

Availability asks whether the system can respond when users need it.

Example:

```text
Healthy response -> available
Timeout/error    -> unavailable for that request
```

Improving availability can include redundancy, health checks, failover, dependency timeouts, graceful degradation, and operational readiness.

## Reliability

Reliability asks whether the system behaves correctly over time.

A service that always returns a fast but wrong answer is available, but not reliable.

Reliability includes:

- Correct business behavior.
- Predictable failure handling.
- Safe retries.
- Consistent validation.
- Good monitoring and incident learning.

## Durability

Durability asks whether committed data survives failures.

Examples:

- A completed order should not disappear after a process restart.
- A payment record should survive disk failure according to the chosen storage guarantees.
- An audit log should not be casually deleted by application code.

Durability is about data survival, not response uptime.

## Redundancy

Redundancy means adding extra components or copies.

Examples:

- Multiple API instances.
- Replicated storage.
- Backup workers.
- Multiple availability zones in a conceptual architecture.

Redundancy is useful, but it is not resilience by itself. If every redundant instance depends on the same broken configuration, all of them can fail the same way.

## Failover

Failover moves work from an unhealthy component to a healthier one.

Design questions:

- How is failure detected?
- Is failover automatic or manual?
- What data may be lost or delayed?
- How does the system avoid split-brain behavior?
- How do clients experience the transition?

## Graceful Degradation

Graceful degradation means the system provides reduced behavior instead of failing completely.

Examples:

- Show cached product details when recommendations fail.
- Accept a request and process it later when notification delivery is slow.
- Disable non-critical analytics while core checkout remains available.

Degradation should be explicit. Silent data loss is not graceful degradation.
