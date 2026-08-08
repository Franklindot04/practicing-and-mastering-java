# Performance Budgets

The capstone models capacity with an explicit admission budget and reports load shedding. This is a learning budget, not a benchmark result. Real budgets would require representative traffic, latency distributions, saturation tests, and profiling evidence.

## Purpose

The admission budget demonstrates a design decision: under pressure, bounded rejection can be safer than accepting unlimited checkout work. The budget is intentionally simple so the behaviour is deterministic in tests.

## What The Budget Means

The budget represents local capacity for checkout attempts. When exhausted, the system rejects new checkout work and increments load-shedding metrics. This teaches capacity protection and operational visibility without requiring load tests.

## What The Budget Does Not Mean

It does not estimate real throughput, latency, CPU usage, memory pressure, database capacity, payment-provider limits, or queue depth. It is not a benchmark and should not be used as a capacity claim.

## Production Comparison

A production budget would be based on measured latency percentiles, dependency limits, database headroom, payment-provider rate limits, queue depth, saturation signals, and business priority. It would likely include per-tenant limits, retry-after responses, dashboards, and incident thresholds.
