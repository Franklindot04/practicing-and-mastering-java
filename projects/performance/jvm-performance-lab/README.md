# JVM Performance Lab

This lab models an event processing and aggregation pipeline. It is intentionally local-only: no databases, message brokers, external APIs, containers, deployment files, or infrastructure.

## Components

- `Event` represents a parsed event.
- `EventParser` parses pipe-delimited input.
- `EventValidator` rejects invalid or duplicate events.
- `EventAggregator` totals amount by customer.
- `BatchProcessor` processes work in deterministic chunks.
- `BoundedEventBuffer` demonstrates bounded buffering and backpressure reasoning.
- `LruCache` demonstrates bounded cache behavior.
- `NaiveEventProcessor` and `OptimizedEventProcessor` provide equivalent results with different internal choices.
- `MetricsSnapshot` captures counts for reasoning about workload behavior.
- `ExecutorConfiguration` and `ConcurrentEventProcessor` demonstrate bounded concurrent processing and graceful shutdown.

## Performance Hypotheses

- Parsing once and reusing parsed events should avoid repeated allocation and repeated validation work.
- Batching should reduce per-item overhead while preserving item-level correctness.
- Bounded buffers and caches should prevent unbounded memory retention.
- Concurrent processing should preserve correctness while making contention risks explicit.

## What Would Be Measured

- allocation rate during parsing and aggregation;
- retained memory after cache and buffer use;
- CPU hot paths in parsing, validation, aggregation, and duplicate checks;
- queue depth and rejection behavior at buffer bounds;
- throughput and latency distribution for representative event mixes;
- lock contention if shared aggregation is changed to coarser synchronization.

## Profiling And Benchmark Plan

Start with correctness tests, then run small repeatable workloads. For profiling, capture short CPU and allocation profiles around parsing and aggregation. For benchmarking, compare naive and optimized processors with the same data, warm-up, Java version, JVM flags, and measurement window. Do not use one microbenchmark as production evidence.

## GC And Concurrency Considerations

High event volume can create allocation pressure. Caches and duplicate sets can retain memory if they are unbounded. Executors need graceful shutdown so work does not leak threads. Thread-pool sizing depends on CPU count, blocking fraction, downstream limits, and correctness constraints.

## Known Limitations

- No fixed performance thresholds are included.
- No real database, broker, network, or cloud behavior is modeled.
- Metrics are educational snapshots, not production observability.
- The optimized implementation improves data flow shape but is not a universal optimization recipe.

## Release-Confidence Checklist

- Parser, validator, aggregation, batching, cache, buffer, metrics, and executor tests pass.
- Naive and optimized processors produce equivalent summaries.
- Duplicate and invalid inputs are handled deterministically.
- Generated build artifacts are removed before commit.
