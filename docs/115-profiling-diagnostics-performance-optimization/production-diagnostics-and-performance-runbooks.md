# Production Diagnostics And Performance Runbooks

A runbook turns panic into a sequence of safe evidence-gathering steps. For high CPU, collect recent deployments, traffic changes, CPU profile, top endpoints, thread states, GC activity, and dependency latency. For memory growth, compare heap, direct memory, native memory, allocation rate, retained objects, and cache behavior.

For GC pause increases, collect GC logs, heap occupancy after collection, allocation rate, promotion, humongous allocation evidence, latency correlation, and recent data-shape changes. For thread-pool exhaustion, inspect active count, queue depth, rejection count, dependency latency, blocked threads, and pool sizing assumptions.

For deadlock suspicion, take multiple thread dumps and look for cycles or repeated blocked stacks. For latency regression, compare p50, p95, p99, throughput, saturation, dependency timing, CPU, allocation, locks, and error rate before and after the suspected change. For allocation spikes, profile allocation sites and connect them to workload changes.

Safe production profiling should define owner, duration, command, expected overhead, artifact location, cleanup rule, and stop condition. Escalation should be based on user impact, uncertainty, and whether rollback is safer than continued diagnosis.
