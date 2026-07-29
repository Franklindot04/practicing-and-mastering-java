# Benchmark Types And JMH Concepts

Microbenchmarks are easy to write badly. Java Microbenchmark Harness exists because the JVM optimizes aggressively and naive timing loops often measure the wrong thing.

## Benchmark Scopes

| Scope | Example | Risk |
| --- | --- | --- |
| Microbenchmark | `HashMap` lookup versus list scan. | Does not include real request path costs. |
| Component benchmark | Event parser with realistic payloads. | May miss downstream contention or queueing. |
| Application benchmark | API endpoint with in-memory dependencies. | More realistic but harder to isolate. |

## JMH Concepts

| Concept | Purpose |
| --- | --- |
| Warm-up iterations | Let class loading, profiling, and JIT compilation settle. |
| Measurement iterations | Collect timed samples after warm-up. |
| Forks | Run benchmark in separate JVM processes to reduce cross-test pollution. |
| Benchmark modes | Measure throughput, average time, sample time, or single-shot time. |
| Benchmark state | Holds data used by benchmark methods. |
| Setup scope | Controls whether data is prepared once, per iteration, or per invocation. |
| Operations per invocation | Describes how much work one benchmark call represents. |
| Blackhole concept | Consumes results so useful work is not optimized away. |

## JVM Optimization Risks

| Risk | Example |
| --- | --- |
| Dead-code elimination | A result is computed but never observed. |
| Constant folding | Inputs are constants and the JVM precomputes work. |
| Inlining differences | Tiny benchmark call shapes differ from application call shapes. |
| Escape analysis | Allocation disappears in a benchmark but not in the real path. |
| False sharing | Threads update nearby fields and invalidate each other's cache lines. |

## When Not To Use Microbenchmarks

- The suspected bottleneck is database, network, disk, queueing, or lock contention.
- The behavior depends on full request routing or framework configuration.
- The target improvement is smaller than likely noise.
- The code is not on a hot path.
- Correctness or design clarity is the real issue.
