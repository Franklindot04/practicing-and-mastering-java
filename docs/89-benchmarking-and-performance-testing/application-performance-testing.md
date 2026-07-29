# Application Performance Testing

Application-level tests describe how a system behaves under demand. They should use representative workloads and should measure more than one success number.

## Test Types

| Test | Goal |
| --- | --- |
| Load test | Validate expected traffic and peak traffic. |
| Stress test | Push beyond expected load to find saturation and failure modes. |
| Spike test | Observe sudden bursts and recovery behavior. |
| Soak test | Run for a long enough period to find leaks, drift, or degradation. |
| Endurance test | Similar to soak, often focused on sustained operation over longer windows. |
| Scalability test | Compare behavior as resources or instances change. |
| Capacity test | Find maximum sustainable work under stated targets. |
| Concurrency test | Exercise shared state, pools, and synchronization under parallel work. |

## Workload Model

| Dimension | Example |
| --- | --- |
| Operation mix | 70% reads, 20% creates, 10% updates. |
| Arrival model | Open model with arrival rate, or closed model with fixed clients. |
| Think time | Delay between operations from the same simulated user. |
| Ramp-up | Gradual increase before steady state. |
| Steady state | Measurement window after warm-up and ramp-up. |
| Data realism | Hot keys, large payloads, duplicates, invalid requests. |

Open workloads model arrivals independent of completion. Closed workloads keep a fixed number of active clients that wait before sending more work. They can produce very different latency signals.

## Metrics To Capture

- p50, p90, p95, p99, and maximum response time.
- Throughput and arrival rate.
- Error rate and timeout rate.
- CPU, memory, allocation, GC, and thread-pool behavior.
- Queue depth and saturation signals.
- Dependency latency and connection-pool usage.

## Coordinated Omission

Coordinated omission happens when a test stops sending new work while waiting for a slow response, hiding the delay that real arrivals would have experienced. Arrival-rate-based tests can reveal this more clearly than tests that only model a fixed number of waiting clients.
