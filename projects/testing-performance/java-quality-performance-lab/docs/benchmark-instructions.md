# Benchmark Instructions

This lab maps business-critical reservation paths, contract compatibility, cache behavior, concurrency, latency, allocation, and diagnostic readiness to focused evidence. Unit tests cover deterministic time, boundaries, invariants, and calculations. Component-style tests cover the API-like service boundary with fakes. Contract checks verify compatible and breaking schema changes. Regression gates compare measured values to explicit budgets.

The workload profiles are seeded and bounded: baseline, CPU-heavy, allocation-heavy, contention, cache-hit, stale-cache, and simulated-regression. Benchmarks and profiling commands are opt-in. Results should be interpreted cautiously because local measurements do not automatically represent production.

Production comparison: a real service would add real databases, brokers, HTTP contracts, deployment telemetry, security controls, larger data, and release gates. This lab intentionally stays infrastructure-independent so default validation remains reliable.
