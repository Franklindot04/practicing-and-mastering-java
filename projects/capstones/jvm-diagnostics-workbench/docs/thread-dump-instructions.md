# Thread-Dump Instructions

Thread dumps are useful for lock contention, blocked-thread, and pool-saturation exercises. Capture multiple samples and compare thread states instead of relying on one snapshot.

## Diagnostic Question

Use thread dumps to ask what threads were doing at a point in time. They are most useful for blocked threads, waiting threads, lock contention, runaway pools, and request-handling stalls.

## Collection Guidance

- Capture more than one sample when investigating a persistent symptom.
- Record timing, workload, thread count, and JVM version.
- Compare thread names, states, stack frames, and repeated blockers.
- Keep dumps in a temporary path and remove them before commit.

## Common Misinterpretations

One blocked thread in one dump may be normal. A thread in `WAITING` may be idle rather than broken. A stack frame that appears in a dump is not automatically the cause of the incident. Look for repeated patterns across samples and correlate with latency, CPU, logs, and workload state.

## Production Comparison

Production thread-dump collection may expose sensitive data in stack traces, headers, request identifiers, or class names. It should follow incident policy and access-control rules.
