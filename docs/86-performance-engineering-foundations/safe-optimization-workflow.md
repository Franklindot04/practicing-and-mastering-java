# Safe Optimization Workflow

Safe optimization is controlled learning. It avoids guessing, avoids heroic rewrites, and keeps correctness visible.

## Workflow

1. Define the user or system goal.
2. State a measurable target and workload.
3. Confirm correctness tests still pass.
4. Measure a baseline.
5. Identify the likely bottleneck with profiles, metrics, logs, or traces.
6. Form one optimization hypothesis.
7. Change one meaningful thing.
8. Re-run the same measurement.
9. Compare latency, throughput, resource use, and error rate.
10. Keep, adjust, or revert the change based on evidence.

## Java Example: Algorithmic Improvement

Repeated lookup with a list:

```java
boolean containsUser(List<String> userIds, String target) {
    return userIds.contains(target);
}
```

If this happens once for a small list, it is fine. If it happens thousands of times inside a request, an index may help:

```java
Set<String> userIdIndex = new HashSet<>(userIds);
boolean exists = userIdIndex.contains(target);
```

The index is not free. It costs allocation, memory, and construction time. The right choice depends on list size, lookup count, lifetime, and whether the data changes.

## Queues And Backpressure

Queues smooth short bursts, but an always-growing queue is delayed failure. Backpressure means the system intentionally slows, rejects, sheds, or limits work before resources collapse.

```text
incoming work -> bounded queue -> workers -> downstream dependency
                    |
                    v
             reject, retry later, or degrade when full
```

Unbounded queues can hide saturation until memory pressure or long tail latency appears.

## Performance Budgets And SLOs

A performance budget is an agreed limit, such as "this operation should allocate little enough to avoid noticeable GC pressure" or "p95 should stay below 300 ms for the stated workload." A service-level objective adds an operational target over time, such as "99.5% of checkout requests should complete within 400 ms over a rolling week."

Budgets and SLOs should guide decisions, not encourage unsafe shortcuts. A missed target is a signal to investigate.

## Common Mistakes

- Optimizing before measuring.
- Treating a microbenchmark as proof of application performance.
- Using average latency as the only response-time statistic.
- Comparing two runs with different workloads or environments.
- Ignoring error rates while celebrating throughput.
- Adding a cache without eviction, invalidation, or memory analysis.
- Making thread pools larger without checking contention and downstream limits.
- Copying tuning flags from another application.
- Removing defensive copies or synchronization before proving correctness is preserved.
- Chasing tiny differences that are likely measurement noise.

## Review Checklist

- What user-visible or system-level problem are we solving?
- What is the representative workload?
- What baseline do we trust, and why?
- Which bottleneck is supported by evidence?
- What correctness tests protect the change?
- What metric could get worse after the optimization?
- How will we detect a regression later?
