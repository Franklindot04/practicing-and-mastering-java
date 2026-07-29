# JVM Tuning And Capacity Planning

JVM tuning should follow measurement. Capacity planning should start with demand, targets, and bottlenecks. Neither should begin by copying flags from a different application.

## Study Order

1. [JVM Tuning Goals And Memory Controls](jvm-tuning-goals-and-memory-controls.md)
2. [Garbage Collector Tradeoffs](garbage-collector-tradeoffs.md)
3. [Capacity Planning And Regression Gates](capacity-planning-and-regression-gates.md)

## Tuning Goal Examples

| Goal | Useful evidence |
| --- | --- |
| Higher throughput | Completed work, CPU use, GC overhead, error rate. |
| Lower pause time | GC pause distribution, p95/p99 response time, allocation rate. |
| Lower memory footprint | Heap occupancy, native memory, direct buffers, thread count. |
| Faster startup | startup timing, class loading, initialization work. |

## Core Principle

Tune only after you know the workload, baseline, and target. A flag that helps a batch processor may hurt an interactive API. A collector that reduces pauses may consume more CPU. A bigger heap may reduce GC frequency while making some pauses or memory costs worse.

## Review Questions

1. Why should `-Xms` and `-Xmx` choices reflect workload and environment?
2. How can more threads increase native-memory pressure?
3. Why is no garbage collector universally best?
4. What is the difference between average demand and peak demand?
5. What makes a performance regression gate useful but not brittle?
