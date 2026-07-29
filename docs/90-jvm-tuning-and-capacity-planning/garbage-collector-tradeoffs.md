# Garbage Collector Tradeoffs

Garbage collectors make different tradeoffs among throughput, pause predictability, CPU overhead, memory overhead, startup, and operational simplicity.

## Collector Families

| Collector family | General idea | Typical learning caution |
| --- | --- | --- |
| Serial GC | Simple collector using a small number of threads. | Useful for small heaps or simple contexts, not a universal default. |
| Parallel GC | Prioritizes throughput using parallel stop-the-world work. | Can produce longer pauses for latency-sensitive services. |
| G1 GC | Region-based collector balancing throughput and pause goals. | Pause targets are goals, not guarantees. |
| ZGC | Low-pause concurrent collector for large or latency-sensitive heaps. | May use more CPU or memory headroom. |
| Shenandoah | Low-pause concurrent collector with a different implementation approach. | Availability and behavior depend on JDK distribution/version. |

## Tradeoff Vocabulary

| Concept | Meaning |
| --- | --- |
| Pause time | How long application threads stop. |
| Throughput | How much application work completes over time. |
| Concurrent work | GC work done while application threads continue. |
| Stop-the-world work | GC work requiring application threads to pause. |
| Allocation rate | New objects created per time unit. |
| Promotion pressure | Objects surviving young collections and moving older. |
| Humongous objects | Very large allocations that can stress region-based collectors. |
| Fragmentation | Free memory exists but not in a useful shape. |
| GC overhead | CPU and time spent on collection instead of application work. |

## Choosing A Collector

Collector choice should follow:

1. workload shape;
2. heap size and live set;
3. latency and throughput goals;
4. CPU and memory limits;
5. JDK version;
6. operational experience and rollback plan.

Do not treat a collector as best because it is newer, popular, or successful in another service.
