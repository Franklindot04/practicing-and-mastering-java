# Performance Requirements And Workload Modelling

A Java service needs performance requirements before optimization work has meaning. Requirements should describe latency, throughput, concurrency, utilization, saturation, capacity, and tail behavior under a named workload. A vague goal such as faster is not enough. A useful goal says, for example, that the order-confirmation path should keep p95 latency below a budget for a representative request mix while CPU, memory, queue depth, and error rate remain acceptable.

Latency measures elapsed time for work. Service time is the time a component spends processing. Response time includes queueing, network, and waiting. Percentiles describe distribution: p50 is typical, p95 and p99 reveal tails. Throughput measures completed work per unit time. Concurrency counts work in progress. Utilization measures busy capacity. Saturation appears when queues grow, threads block, pools exhaust, or latency rises faster than throughput.

Workload modelling defines request mix, data sizes, read/write ratio, cache state, user behavior, concurrency, arrival pattern, dependency behavior, peak traffic, average traffic, and failure modes. Representative data matters because tiny inputs hide allocation, indexing, serialization, and cache behavior.

Queueing explains why latency can explode before CPU reaches 100 percent. When arrival rate approaches service capacity, waiting dominates. Tail latency often comes from contention, garbage collection, page faults, slow dependencies, retries, locks, cache misses, or noisy neighbors.

Performance budgets turn expectations into gates. A budget may cover p95 latency, maximum allocation per operation, throughput floor, memory growth, startup time, or test-suite duration. Budgets should include uncertainty and should be revised when product goals or infrastructure change.

## Evidence Warnings

Benchmark and runtime data are evidence, not prophecy. Results depend on workload, hardware, JVM version, flags, data shape, warm-up, neighboring processes, and production architecture. Do not copy JVM flags or treat a microbenchmark as whole-system truth without workload evidence.
