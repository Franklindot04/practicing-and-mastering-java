# Answer Key

## Multiple Choice

1. B
2. A
3. A
4. A
5. A

## Short Answer

Answers should emphasize measurement, representative workload, correctness before optimization, and uncertainty. Latency is one operation's wait; throughput is completed work over time. Profiling finds where costs occur; monitoring tracks ongoing system signals. Warm-up matters because the JVM interprets, profiles, and JIT-compiles hot paths.

## Classification

1. heap
2. stack
3. metaspace
4. code cache
5. direct memory
6. native memory through thread stacks
7. heap, retained by a static reference
8. stack

## Profiling Tool Selection

1. CPU profile
2. thread dump or lock profile
3. heap dump, histogram, and retained-size analysis
4. Java Flight Recorder
5. thread dump
6. heap histogram
7. correlated incident timeline using logs, metrics, traces, and profiles

## Thread States

`BLOCKED` suggests lock contention. Idle `WAITING` executor threads can be normal. `TIMED_WAITING` during database calls should lead to connection-pool, query, timeout, and dependency checks. More than one dump helps separate snapshots from trends.

## Benchmark Smells

No warm-up, dead-code elimination, single-run confidence, constant folding risk, production claim from microbenchmark, fragile time threshold, and missing percentile/error analysis.

## Percentiles

p95 means 95% of observations are at or below that value. Complaints can come from p99 and max tail delays. Analyze error rate, throughput, queueing, retries, GC, locks, and dependencies.

## GC Tradeoffs

Batch jobs may prioritize throughput; interactive services often care about pause distribution. Concurrent collectors can trade CPU/memory overhead for shorter pauses. Promotion pressure means objects surviving young collection and moving older.

## Memory Leaks

Reachable objects are not collectible. High allocation is not necessarily retained growth. `ThreadLocal` retention in pools can keep stale data live. Heap dumps may contain user data or credentials.

## Concurrency

More threads can increase context switching, lock contention, memory pressure, and downstream saturation. Lock granularity is how broad or narrow protected sections are. `LongAdder` reduces contention for high-frequency counters.

## Load Testing

Load tests expected demand; stress pushes beyond; spike tests bursts; soak tests duration. Coordinated omission hides latency by pausing arrivals while waiting. Report percentiles, errors, throughput, resource use, queues, and saturation.

## Java Code Reading

1. Repeated list lookup can become O(n*m); a `Set` improves lookup but costs memory and build time.
2. It is accidental retention from an unbounded static map.
3. Unused result risks dead-code elimination; constant input risks constant folding or unrealistic specialization.

## Capacity Planning

Peak demand drives saturation. Headroom absorbs variance, retries, GC, and failures. Containers need non-heap memory for stacks, direct buffers, metaspace, code cache, and JVM overhead. Regression gates need representative workloads and noise-aware comparisons.
