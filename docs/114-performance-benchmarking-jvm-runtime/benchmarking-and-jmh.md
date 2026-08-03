# Benchmarking And JMH

Benchmarks answer narrow questions under controlled conditions. Microbenchmarks isolate tiny operations. Macrobenchmarks measure larger application workflows. Load tests evaluate expected traffic. Stress tests find failure thresholds. Spike tests check sudden changes. Soak tests reveal slow leaks and degradation. Capacity tests estimate resource needs.

JMH is the standard Java harness for microbenchmarks because it handles warm-up, measurement iterations, forks, timing, dead-code elimination safeguards, and JVM isolation better than hand-written loops. A JMH benchmark should define benchmark state, setup and teardown, benchmark mode, warm-up, measurement iterations, forks, operations per invocation when relevant, and parameters.

Use `Blackhole` or returned values to prevent dead-code elimination. Avoid constant folding by passing realistic state rather than compile-time constants. Understand that inlining, escape analysis, scalar replacement, tiered compilation, and deoptimization can change what is measured. Measure allocation when allocation is part of the hypothesis.

Common benchmark mistakes include timing with `System.currentTimeMillis`, measuring cold startup accidentally, running too few iterations, ignoring forks, sharing mutable state unsafely, benchmarking logging or I/O accidentally, using unrealistic input, comparing results without confidence intervals, and assuming the fastest microbenchmark wins in production.

JMH benchmarks should not run during default `mvn test`. They should be invoked by a separate command or Maven profile so normal correctness feedback stays fast and deterministic.

## Evidence Warnings

Benchmark and runtime data are evidence, not prophecy. Results depend on workload, hardware, JVM version, flags, data shape, warm-up, neighboring processes, and production architecture. Do not copy JVM flags or treat a microbenchmark as whole-system truth without workload evidence.
