# Garbage Collection And Heap Behaviour

Garbage collection reclaims unreachable objects. Reachability starts from GC roots such as thread stacks, static fields, JNI references, and live JVM internals. Generational collectors use the observation that many objects die young. Young collections reclaim short-lived allocations. Old collections handle longer-lived objects.

Stop-the-world pauses stop application threads. Concurrent collectors do some work while the application runs, trading CPU and complexity for shorter pauses. Serial GC is simple and suited to small heaps. Parallel GC focuses on throughput. G1 divides the heap into regions and targets predictable pauses. ZGC and Shenandoah focus on very low pauses through concurrent work. Collector choice is a trade-off, not a universal ranking.

Important signals include allocation rate, promotion rate, evacuation failures, remembered-set pressure, humongous allocations, pause duration, pause frequency, heap occupancy after GC, and time spent in GC. Heap sizing affects throughput, pause behavior, startup, and memory cost. Too small a heap can cause constant collections. Too large a heap can increase footprint and sometimes pause or warm-up costs.

GC logs provide evidence. They should be correlated with latency, throughput, allocation profiles, CPU, and workload events. Do not tune GC from a single pause, copied flag list, or generic blog post. Measure allocation and pause behavior first.

## Evidence Warnings

Benchmark and runtime data are evidence, not prophecy. Results depend on workload, hardware, JVM version, flags, data shape, warm-up, neighboring processes, and production architecture. Do not copy JVM flags or treat a microbenchmark as whole-system truth without workload evidence.
