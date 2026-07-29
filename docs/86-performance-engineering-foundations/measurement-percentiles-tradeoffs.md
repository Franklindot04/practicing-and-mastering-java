# Measurement, Percentiles, And Tradeoffs

Performance measurement turns opinions into evidence. The first useful measurement is usually a baseline: current behavior under a described workload in a described environment.

## Averages And Percentiles

An average is easy to compute but easy to misuse. One very slow request can be hidden by many fast requests, and one average does not show how many users were affected.

| Statistic | Meaning | Useful caution |
| --- | --- | --- |
| p50 | Half of observations are at or below this value. | Describes the median case, not the tail. |
| p90 | 90% are at or below this value. | Better view of slower users. |
| p95 | 95% are at or below this value. | Common service-level target, but still hides the slowest 5%. |
| p99 | 99% are at or below this value. | Sensitive to rare pauses, queues, retries, and coordinated effects. |
| Maximum | Slowest observed value. | Can be important, but may also reflect one unusual event. |

Example:

| Request group | Count | Latency |
| --- | ---: | ---: |
| Cache hits | 950 | 20 ms |
| Database misses | 45 | 180 ms |
| Timeout retries | 5 | 2,000 ms |

The average may look acceptable, while p99 and maximum reveal retry pain. Tail latency matters because real user journeys often make several calls; one slow call can make the whole journey feel slow.

## Throughput Versus Latency

Higher throughput is not always better. A Java service can accept more concurrent work than it can complete promptly. Once CPU, database connections, memory bandwidth, or locks saturate, queues grow and response time rises.

| Change | Possible benefit | Possible cost |
| --- | --- | --- |
| Larger thread pool | More blocking I/O can overlap. | More context switching, memory use, and contention. |
| Bigger cache | Fewer repeated computations or calls. | More retained memory and stale-data risk. |
| Larger batches | Better throughput per operation. | Higher latency for items waiting to fill a batch. |
| More aggressive precomputation | Faster reads. | Slower writes and larger memory footprint. |

## Workload Models

A representative workload should describe:

- operation mix, such as 80% reads and 20% writes;
- arrival pattern, such as steady, bursty, or scheduled spikes;
- data shape, such as hot keys, large payloads, or many small objects;
- concurrency level or arrival rate;
- dependency behavior, such as database latency and error rate;
- warm-up and steady-state duration.

## Environmental Noise

Measurements are affected by background processes, CPU frequency scaling, thermal throttling, garbage collection, JIT warm-up, network variability, shared machines, test data, and logging volume. Repeat runs and controlled comparisons help separate signal from noise.

## Baseline Comparison Checklist

- Same code path and input data shape.
- Same Java version and JVM flags.
- Same machine or clearly documented environment.
- Same warm-up and measurement periods.
- Same concurrency and arrival model.
- Same logging and diagnostic overhead.
- Multiple runs when differences are small.
- Error rates considered alongside latency and throughput.
