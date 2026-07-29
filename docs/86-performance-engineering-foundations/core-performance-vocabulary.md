# Core Performance Vocabulary

Performance language is useful only when it is precise. "Fast" can mean lower latency, higher throughput, less CPU, fewer allocations, shorter startup, or more predictable tail behavior.

## Key Terms

| Term | Meaning | Java-oriented example |
| --- | --- | --- |
| Latency | Time observed for one operation. | Time from HTTP request arrival to response. |
| Response time | Full time a caller waits, including queues and dependencies. | Controller wait plus service code plus database call. |
| Service time | Time spent actively doing the work once it starts. | Method execution after an executor thread picks up the task. |
| Waiting time | Time spent queued or blocked before work progresses. | Waiting for a thread, lock, connection, or rate limit. |
| Throughput | Completed work per unit of time. | Orders processed per second. |
| Utilization | Fraction of a resource currently busy. | CPU at 85% or database pool nearly full. |
| Saturation | Resource has more demand than it can serve promptly. | Executor queue grows and p95 latency climbs. |
| Capacity | Maximum sustainable useful work under a stated target. | 300 requests per second while p95 stays below a budget. |
| Concurrency | Work in progress at the same time. | 80 active requests in a servlet container. |
| Scalability | Ability to handle more work by adding resources or improving design. | More instances, better indexes, or reduced lock contention. |
| Efficiency | Useful work done per resource consumed. | Fewer CPU cycles or allocations per processed event. |
| Responsiveness | Ability to keep serving promptly under normal and bursty demand. | UI or API does not appear stuck during a slow dependency. |

## Work Types

| Bound by | Symptom | Common evidence |
| --- | --- | --- |
| CPU | Cores are busy and profiles show hot Java methods. | CPU profile, run queue, algorithm review. |
| Memory | High allocation rate, GC pressure, or retained objects. | Allocation profile, heap histogram, GC logs. |
| I/O | Threads wait on disk, database, or network. | Wall-clock profile, dependency timing, connection metrics. |
| Contention | Threads block each other. | Thread dumps, lock profiles, queue depth, synchronized hot spots. |

## Bottlenecks And Critical Paths

A bottleneck is the limiting resource or step. A critical path is the chain of work that directly determines response time. In Java applications, bottlenecks often appear in:

- inefficient algorithms, such as repeated linear scans;
- database access, such as missing indexes or excessive round trips;
- allocation-heavy code that creates GC pressure;
- locks and shared mutable state;
- thread pools that are too small, too large, or unbounded;
- remote calls without timeouts or backpressure.

## Little's Law As A Concept

Little's Law connects throughput, latency, and work in progress:

```text
concurrency ~= throughput x response time
```

If a service completes 100 requests per second and each request takes 0.2 seconds, about 20 requests are in progress on average. This is a thinking tool, not a substitute for measurement. Bursty arrivals, retries, and changing workloads can make real systems less tidy.

## Performance Requirements

Weak requirement:

```text
The endpoint must be fast.
```

Better requirement:

```text
For the checkout-read workload of 200 requests per second, p95 response time should stay below 250 ms and errors below 0.1% in the staging-like test environment.
```

The better version names the workload, target, statistic, resource context, and error expectation.
