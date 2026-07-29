# Performance Engineering Foundations

Performance engineering is the practice of making software meet measurable speed, capacity, and resource goals without breaking correctness or maintainability. It starts with evidence: define the workload, measure a baseline, form a hypothesis, change one thing, and compare results.

This section connects testing strategy to performance work. Functional tests can show that a Java service returns the right answer. Performance evidence asks whether it still returns the right answer under realistic demand, resource limits, queueing, and operational noise.

## Study Order

1. [Core Performance Vocabulary](core-performance-vocabulary.md)
2. [Measurement, Percentiles, And Tradeoffs](measurement-percentiles-tradeoffs.md)
3. [Safe Optimization Workflow](safe-optimization-workflow.md)

## Performance Questions

| Question | Useful evidence |
| --- | --- |
| Is one request fast enough? | Latency, response-time percentiles, critical-path analysis. |
| Can the system handle enough work? | Throughput, capacity tests, utilization, saturation signals. |
| Does it stay responsive at peak? | Tail latency, queue depth, error rate, backpressure behavior. |
| Is the code efficient enough? | CPU time, allocation rate, memory use, I/O wait, contention. |
| Did a change make things worse? | Before-and-after baseline comparison using the same workload. |

## A Simple Mental Model

```text
client request
     |
     v
queue or executor wait -> Java service work -> database or network wait -> response
     |                         |                         |
 waiting time              service time              external latency
```

Response time includes waiting time plus service time. Optimizing the Java method alone may not help if most time is spent in a queue, lock, database call, or remote dependency.

## Core Principles

- Correctness comes before speed.
- Performance requirements should describe a workload, a target, and an environment.
- Averages hide slow users; percentiles reveal more of the distribution.
- Throughput and latency often trade off once a resource approaches saturation.
- Local optimization can hurt system-wide performance when it moves bottlenecks elsewhere.
- A benchmark is evidence about one workload, not proof about every workload.
- Repeatable experiments are more valuable than one impressive result.

## Review Questions

1. Why is response time different from service time?
2. Why can average latency look healthy while p99 latency is unacceptable?
3. What information is missing from the statement "the endpoint handles 500 requests per second"?
4. How can raising concurrency increase throughput and also make latency worse?
5. What should you measure before changing an algorithm, cache, thread pool, or JVM flag?
