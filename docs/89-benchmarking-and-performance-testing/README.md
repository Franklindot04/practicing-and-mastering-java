# Benchmarking And Performance Testing

Benchmarking and performance testing are different ways to gather performance evidence. A microbenchmark can isolate a small Java operation. A load test can show application behavior under realistic traffic. Neither one proves every production outcome.

## Study Order

1. [Benchmark Types And JMH Concepts](benchmark-types-and-jmh-concepts.md)
2. [Application Performance Testing](application-performance-testing.md)
3. [Result Analysis And Benchmark Smells](result-analysis-and-benchmark-smells.md)

## Evidence Levels

| Evidence | Scope | Best used for |
| --- | --- | --- |
| Microbenchmark | Tiny code path. | Comparing algorithms or API choices in isolation. |
| Component benchmark | One module or boundary. | Checking parser, serializer, cache, or repository behavior. |
| Application benchmark | Running application slice. | End-to-end service behavior under a defined workload. |
| Load test | Expected traffic. | Confidence under normal and peak demand. |
| Stress test | Beyond expected traffic. | Discovering saturation and failure behavior. |

## Core Warning

A benchmark result should include the code version, workload, environment, warm-up, measurement settings, result distribution, and error behavior. Without that context, the number is decoration.

## Review Questions

1. When is a microbenchmark the wrong tool?
2. Why do warm-up iterations matter for Java?
3. What is coordinated omission?
4. Why should error rate be analyzed with latency?
5. How can a tiny before-and-after difference be measurement noise?
