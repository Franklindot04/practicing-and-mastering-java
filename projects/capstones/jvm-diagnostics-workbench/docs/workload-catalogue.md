# Workload Catalogue

The workbench includes CPU-heavy calculation, allocation-heavy processing, retained-object pressure, lock contention, bounded blocked thread, thread-pool saturation, cache pressure, latency-distribution generation, simulated regression-budget checks, and bounded GC-observation style allocation.

## Purpose

The catalogue gives learners safe, repeatable symptoms to investigate. Each workload asks a specific diagnostic question rather than trying to imitate a full application.

## Workloads And Questions

- CPU-heavy calculation: which method consumes CPU under a bounded loop?
- Allocation-heavy processing: how does short-lived allocation appear in allocation evidence?
- Retained-object scenario: what changes when objects remain reachable?
- Lock contention: how do blocked or waiting threads appear when a shared lock is busy?
- Bounded blocked thread: what does a waiting thread look like without creating a permanent deadlock?
- Thread-pool saturation: how can too much queued work affect completion?
- Cache pressure: how can churn in a small keyspace hide repeated writes?
- Latency distribution: why are percentiles more useful than averages for tail behaviour?
- Regression budget: how should baseline and candidate evidence be compared?
- GC-observation allocation: what evidence suggests collection pressure without unbounded memory growth?

## Safe Operating Limits

Workload configuration is bounded by the Java code: maximum iterations, allocation bytes, and worker threads are validated before execution. These limits protect default tests from long runtime, memory exhaustion, and uncontrolled thread creation.

## Production Comparison

Production symptoms usually combine traffic, dependencies, data shape, runtime configuration, and deployment environment. These workloads isolate one signal at a time so learners can practice evidence collection without claiming that local behaviour predicts production performance.
