# Concurrency Performance And Resource Pools

Concurrency performance depends on the kind of work. CPU-bound tasks generally need a pool near available CPU capacity. Blocking work needs separate limits based on dependency latency, throughput target, and backpressure. Queue sizing controls memory, latency, and failure behavior; an unbounded queue can turn overload into memory growth.

Lock contention appears when threads spend time waiting for synchronized sections or contended locks. Atomics can reduce locking for simple state but can still create contention. `volatile` gives visibility, not atomic compound updates. False sharing occurs when independent hot fields share cache lines. Context switching grows when runnable threads exceed useful CPU capacity.

Connection pools, thread pools, and bulkheads should be sized from workload evidence. Too small causes saturation and latency. Too large can overload downstream systems and increase contention. Thread starvation, deadlock, livelock, fairness settings, and parallel-stream usage all need measurement.

Virtual-thread concepts can simplify blocking workloads, but they do not remove downstream capacity limits, locking problems, database limits, or memory pressure. Structured-concurrency concepts help bound lifetime and cancellation. Backpressure and load shedding protect systems when demand exceeds capacity.
