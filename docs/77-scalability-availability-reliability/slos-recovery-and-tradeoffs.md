# SLOs Recovery And Tradeoffs

Reliability work should be tied to user expectations and business value. Otherwise teams can spend heavily on protection that does not change the user experience.

## SLO-Oriented Thinking

A Service Level Objective, or SLO, is a target for service behavior.

Examples:

- 99.9 percent of create-task requests complete successfully over 30 days.
- 95 percent of list-task requests respond under 300 ms.
- Background notification backlog drains within 10 minutes during normal traffic.

SLOs help prioritize engineering work. If a path is not critical, it may not need the same target as checkout, payment, or authentication.

## Recovery Objectives

Recovery objectives describe what happens after failure.

| Term | Plain Meaning | Example Question |
| --- | --- | --- |
| RTO | How quickly service should be restored | Can users submit orders within 30 minutes? |
| RPO | How much data loss is acceptable | Can the system lose the last minute of analytics events? |

Not every system needs extreme RTO or RPO. Stronger targets cost more in architecture, operations, and testing.

## Performance Cost Reliability Tradeoffs

| Goal | Common Technique | Tradeoff |
| --- | --- | --- |
| Lower latency | Cache, replicas, smaller payloads | Staleness and invalidation work |
| Higher availability | Redundancy, failover, graceful degradation | Operational complexity |
| Higher durability | Replication, backups, write-ahead logs | Cost and write latency |
| Better fault isolation | Bulkheads, limits, separate pools | More configuration and tuning |
| Safer retries | Idempotency keys, retry budgets | More state and design work |

## Example Review

Suppose a reporting endpoint is slow.

Possible responses:

- Add an index if the query pattern is stable.
- Precompute a read model if users accept slightly stale data.
- Move reports to a separate pool so they do not slow writes.
- Add pagination if response size is the bottleneck.
- Add caching if invalidation is manageable.

There is no automatic answer. The best choice depends on data freshness, cost, traffic, and failure impact.

## Review Questions

- Which user journey deserves the strongest reliability target?
- Which path can degrade without data loss?
- Which retry could create duplicate work?
- Which component has no fault isolation today?
- Which metric would show saturation first?
- Which reliability improvement is not worth the cost yet?
- What incident would make this design need revision?
