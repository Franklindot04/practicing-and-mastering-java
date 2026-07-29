# Performance Investigation Checklists

Investigation should move from symptom to evidence to hypothesis. Avoid jumping from "CPU is high" to "rewrite the algorithm" without proving the hot path.

## CPU Spike

- Confirm request rate, batch jobs, and background work.
- Check whether CPU is user code, GC, JIT compilation, or native work.
- Capture a short CPU profile during the spike.
- Compare hot methods with expected workload.
- Look for algorithmic blowups, repeated parsing, logging volume, or serialization cost.

## Slow Request

- Identify whether the delay is CPU, waiting, lock contention, database, network, or queueing.
- Compare p50, p95, p99, maximum, and error rate.
- Trace the request path when available.
- Capture a wall-clock profile if blocking is suspected.
- Check thread pools, connection pools, retries, and timeouts.

## Memory Leak Reasoning

- Distinguish high allocation rate from growing retained memory.
- Compare heap after similar workload phases.
- Inspect histograms for unexpected growth.
- Use dominator trees to find retaining paths.
- Check static maps, caches, listeners, callbacks, thread locals, and classloaders.
- Verify cleanup behavior with correctness tests when possible.

## Lock Contention And Deadlock

- Capture thread dumps while the application is slow.
- Look for many `BLOCKED` threads on the same monitor.
- Identify lock ordering and shared mutable state.
- Check synchronized logging, shared counters, caches, and coarse-grained locks.
- For deadlocks, find cycles where threads each hold a resource the other needs.

## Long GC Pause

- Check GC logs around the pause.
- Compare allocation rate, live heap, and promotion pressure.
- Look for sudden large object creation or retention.
- Consider whether diagnostics or test workload caused extra pressure.
- Avoid changing collectors before proving the workload and goal.

## Performance Incident Checklist

- What changed recently?
- What user-visible symptom occurred?
- Which metrics moved first?
- Is the issue constant, periodic, bursty, or data-specific?
- What evidence supports the current hypothesis?
- What evidence would disprove it?
- What is the safest mitigation?
- What follow-up test prevents a regression?

## Common Mistakes

- Treating one stack trace as a profile.
- Trusting a single short capture for an intermittent issue.
- Ignoring profiling overhead.
- Optimizing the widest flame graph frame without checking expected work.
- Confusing blocked threads with CPU-bound work.
- Sharing heap dumps or recordings without sensitivity review.
