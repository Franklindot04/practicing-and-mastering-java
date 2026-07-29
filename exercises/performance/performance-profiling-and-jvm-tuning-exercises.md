# Performance Profiling And JVM Tuning Exercises

## 1. Latency Versus Throughput

- Difficulty: beginner
- Concepts: latency, throughput, response time
- Scenario: A Java API completes 800 requests per second, but users complain checkout feels slow.
- Requirements: Identify which metrics are missing.
- Constraints: Do not propose code changes yet.
- Questions: Which percentiles would you request? What workload context matters?
- Hints: Throughput alone says nothing about individual waits.
- Stretch challenge: Define a performance budget.

## 2. Percentile Interpretation

- Difficulty: beginner
- Concepts: p50, p95, p99, maximum
- Scenario: Average latency is 90 ms, p95 is 420 ms, p99 is 2 seconds.
- Requirements: Explain why the average is insufficient.
- Constraints: Include user impact.
- Questions: Which users are affected? What could cause the tail?
- Hints: Look for queues, retries, GC, and dependencies.
- Stretch challenge: Propose an SLO.

## 3. Bottleneck Identification

- Difficulty: beginner
- Concepts: CPU-bound, I/O-bound, contention-bound
- Scenario: Threads spend most time waiting for database connections.
- Requirements: Classify the bottleneck and name evidence to collect.
- Constraints: Avoid blaming the JVM first.
- Questions: What metrics would confirm saturation?
- Hints: Connection-pool wait time is not CPU time.
- Stretch challenge: Add a backpressure option.

## 4. Workload Modeling

- Difficulty: intermediate
- Concepts: representative workload, operation mix, data shape
- Scenario: A benchmark uses only one tiny customer account.
- Requirements: Improve the workload model.
- Constraints: No production data or secrets.
- Questions: What data variation is missing?
- Hints: Include hot keys, large accounts, invalid requests, and duplicates.
- Stretch challenge: Define ramp-up and steady-state periods.

## 5. Allocation Analysis

- Difficulty: intermediate
- Concepts: allocation rate, retained memory, GC pressure
- Scenario: A parser creates many short-lived objects but heap usage returns to normal.
- Requirements: Explain whether this is a leak.
- Constraints: Use precise memory vocabulary.
- Questions: What profile would help?
- Hints: High allocation is not the same as retained growth.
- Stretch challenge: Suggest a safe optimization hypothesis.

## 6. Memory Leak Reasoning

- Difficulty: intermediate
- Concepts: heap histogram, retained size, accidental retention
- Scenario: A static map stores request IDs forever.
- Requirements: Explain the leak and mitigation options.
- Constraints: Preserve duplicate detection correctness.
- Questions: Should the map be bounded, time-based, or externalized?
- Hints: GC cannot collect reachable objects.
- Stretch challenge: Define a test for cleanup behavior.

## 7. Garbage Collection Tradeoffs

- Difficulty: intermediate
- Concepts: pause time, throughput, collector selection
- Scenario: A batch job has high throughput but long pauses.
- Requirements: Decide whether pauses are a problem.
- Constraints: Do not prescribe one collector as universal.
- Questions: Who observes the pauses? What goal matters?
- Hints: Batch and interactive workloads differ.
- Stretch challenge: Design a collector comparison experiment.

## 8. Thread State Interpretation

- Difficulty: intermediate
- Concepts: RUNNABLE, BLOCKED, WAITING, TIMED_WAITING
- Scenario: Many request threads are `BLOCKED` on the same synchronized cache.
- Requirements: Diagnose the likely contention.
- Constraints: Keep cache correctness.
- Questions: What code or profile evidence would you inspect?
- Hints: Coarse locks can serialize callers.
- Stretch challenge: Compare lock splitting and immutable snapshots.

## 9. Simplified Flame Graph Reading

- Difficulty: intermediate
- Concepts: flame graphs, inclusive time, hot path
- Scenario: A wide stack is `parse -> normalize -> regex`.
- Requirements: Form an optimization hypothesis.
- Constraints: Do not remove validation.
- Questions: Is regex expected? Can parsing be done once?
- Hints: Wide frames are questions, not guilt.
- Stretch challenge: Suggest a component benchmark.

## 10. Heap Histogram Reading

- Difficulty: intermediate
- Concepts: shallow size, retained objects, heap dump
- Scenario: Histograms show many `byte[]` retained by cached reports.
- Requirements: Explain next diagnostic steps.
- Constraints: Treat dumps as sensitive.
- Questions: What retaining path matters?
- Hints: Dominator trees help connect owners to retained data.
- Stretch challenge: Propose a cache budget.

## 11. Benchmark Design

- Difficulty: advanced
- Concepts: warm-up, measurement, forks, blackholes
- Scenario: A loop benchmark computes a value but never observes it.
- Requirements: Identify benchmark smells.
- Constraints: Do not add fragile time assertions.
- Questions: What could the JIT remove?
- Hints: Dead-code elimination can erase work.
- Stretch challenge: Sketch a JMH-style plan.

## 12. Constant Folding And Warm-Up

- Difficulty: advanced
- Concepts: JIT warm-up, constant folding
- Scenario: A benchmark parses the same constant string millions of times.
- Requirements: Explain why results may be misleading.
- Constraints: Keep the benchmark educational.
- Questions: How should inputs vary?
- Hints: Real workloads rarely use one constant.
- Stretch challenge: Define setup state.

## 13. Load-Test Design

- Difficulty: advanced
- Concepts: open workload, closed workload, coordinated omission
- Scenario: A fixed-client test stops sending work while waiting for slow responses.
- Requirements: Explain coordinated omission risk.
- Constraints: No external load-test infrastructure.
- Questions: What arrival model would reveal delayed users?
- Hints: Arrival rate matters during stalls.
- Stretch challenge: Add error-rate analysis.

## 14. Soak-Test Planning

- Difficulty: advanced
- Concepts: endurance, memory drift, leak detection
- Scenario: A service looks fine for ten minutes and fails after six hours.
- Requirements: Plan a soak test.
- Constraints: Avoid production endpoints.
- Questions: What trends matter?
- Hints: Watch retained heap, threads, queues, and cache size.
- Stretch challenge: Add cleanup validation.

## 15. Thread-Pool Sizing Reasoning

- Difficulty: advanced
- Concepts: blocking fraction, CPU limits, downstream saturation
- Scenario: Increasing a pool from 50 to 300 makes p99 worse.
- Requirements: Explain possible causes.
- Constraints: Avoid "more threads is always better."
- Questions: What resources became saturated?
- Hints: Context switching and dependency queues can dominate.
- Stretch challenge: Define a safe experiment.

## 16. Cache Tradeoffs And Backpressure

- Difficulty: advanced
- Concepts: cache bounds, eviction, stale data, backpressure
- Scenario: A cache improves p50 but increases memory and stale results.
- Requirements: Propose a decision process.
- Constraints: Preserve correctness and user trust.
- Questions: What cache budget and invalidation rules are needed?
- Hints: Fast wrong answers are still wrong.
- Stretch challenge: Add overload behavior.

## 17. JVM Heap Sizing And Capacity

- Difficulty: advanced
- Concepts: heap sizing, native headroom, peak demand
- Scenario: A container has 512 MB memory and the heap is set near that limit.
- Requirements: Explain the risk.
- Constraints: Do not provide production flag values.
- Questions: What non-heap memory needs headroom?
- Hints: Threads, metaspace, direct buffers, and JVM overhead count too.
- Stretch challenge: Design a sizing test.

## 18. Performance Regression Review

- Difficulty: advanced
- Concepts: regression gates, safe optimization
- Scenario: A change improves average latency by 2% but worsens p99 by 35%.
- Requirements: Make a review recommendation.
- Constraints: Include uncertainty and tradeoffs.
- Questions: What additional evidence is needed?
- Hints: Tail regressions may matter more than small average wins.
- Stretch challenge: Propose rollback criteria.
