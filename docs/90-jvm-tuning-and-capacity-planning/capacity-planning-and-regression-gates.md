# Capacity Planning And Regression Gates

Capacity planning estimates the resources and limits needed to meet performance targets under expected and peak demand. It is not a one-time spreadsheet; it changes with product usage, data shape, dependencies, and architecture.

## Planning Inputs

| Input | Example |
| --- | --- |
| Workload | request mix, batch size, event arrival rate. |
| Target | p95 latency, p99 latency, throughput, error rate. |
| Peak demand | launch events, daily spikes, seasonal bursts. |
| Headroom | spare capacity for variance and failure. |
| Resource limits | CPU, memory, threads, pools, queues. |
| Cost limits | acceptable spend for target confidence. |

## Scaling Choices

| Choice | Benefit | Risk |
| --- | --- | --- |
| Vertical scaling | More CPU or memory per instance. | Bigger failure unit and cost jumps. |
| Horizontal scaling | More instances. | Requires statelessness, load balancing, and shared limits. |
| Concurrency limits | Protects downstream resources. | May reject or delay work earlier. |
| Backpressure | Prevents collapse under overload. | Requires caller-visible behavior decisions. |
| Load shedding | Preserves critical work. | Drops or degrades lower-priority work. |

## Queueing And Headroom

Average demand can look safe while peaks saturate the system. Headroom gives space for bursts, retries, GC, dependency slowness, and instance loss. Queues can absorb short bursts, but sustained queue growth means capacity is insufficient or downstream work is too slow.

## Regression Gates

A performance regression gate should be:

- tied to a representative workload;
- versioned with code and test data;
- aware of noise and repeated runs;
- focused on meaningful differences;
- paired with correctness tests;
- reviewed when architecture or workload changes.

Avoid brittle unit tests with fixed wall-clock thresholds. Prefer trend checks, benchmark reports, and review gates that compare distributions and resource signals.

## Capacity Review Checklist

- What is the current baseline?
- What demand growth is expected?
- What resource saturates first?
- What happens when queues fill?
- What backpressure or load shedding exists?
- What is the rollback plan for JVM flags or sizing changes?
- What metric warns before users feel the issue?
- What performance budget protects future changes?
