# Performance Considerations

The capstone models capacity and hot partitions but does not benchmark real scheduler throughput. Production claims would require representative workloads, contention tests, queue-depth evidence, and recovery drills.

## Capacity Model

Admission capacity is a local budget that demonstrates bounded rejection. It is not a throughput number. Worker capacity is represented in registration data, but the current deterministic scheduler does not model CPU saturation, blocking IO, or queue contention.

## Evidence Needed For Real Performance Claims

Real claims would need workload definitions, task mix, dependency latency, worker count, queue depth, lease duration, retry volume, p50/p95/p99 latency, saturation behaviour, and recovery timing. Measurements should include environment and repeatability notes.

## Trade-Offs

Keeping performance synthetic avoids flaky tests and infrastructure requirements. The trade-off is that learners must not extrapolate local counts into deployment capacity.
