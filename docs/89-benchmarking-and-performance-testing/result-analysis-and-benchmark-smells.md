# Result Analysis And Benchmark Smells

Performance results need interpretation. The goal is not to win one run; the goal is to understand whether a change helps a meaningful workload without hurting something else.

## Analysis Checklist

- Compare against a baseline from the same environment.
- Check the full latency distribution, not only the average.
- Include throughput, errors, and resource utilization.
- Repeat runs when differences are small.
- Record code version, Java version, JVM flags, input data, and machine details.
- Look for warm-up, GC, CPU scaling, background process, and thermal effects.
- State uncertainty and likely noise.

## Before-And-After Comparison

| Signal | Good question |
| --- | --- |
| p95 improved but p99 worsened | Did the change help most users while hurting tail behavior? |
| Throughput increased and errors increased | Are failed operations being counted as success? |
| CPU decreased but latency increased | Did batching or queueing trade responsiveness for efficiency? |
| Allocation decreased but retained heap increased | Did object reuse accidentally keep data alive? |

## Statistical Concepts

Statistical significance asks whether observed differences are likely real rather than noise. Confidence intervals describe uncertainty around an estimate. For learning, the main habit is simple: do not over-explain tiny differences from one run.

## Benchmark Smells

- No workload description.
- No warm-up.
- One run only.
- Only average latency reported.
- No error rate.
- Production claim from a microbenchmark.
- Timing code uses wall-clock time for elapsed measurement instead of `System.nanoTime`.
- Test data is too small, too clean, or fully constant.
- Results are compared across different machines or JVM flags.
- Performance assertions use fixed wall-clock thresholds in unit tests.

## Versioning Results

Useful benchmark notes include:

```text
code version:
java version:
JVM flags:
workload:
warm-up:
measurement window:
environment:
result summary:
known limitations:
```

This makes later regression reviews possible.
