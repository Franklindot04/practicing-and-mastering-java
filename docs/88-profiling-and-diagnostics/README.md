# Profiling And Diagnostics

Profiling explains where time, allocation, blocking, or memory retention happens. Monitoring tells you that something is happening over time. Both matter, but they answer different questions.

## Study Order

1. [Profiling Methods And Views](profiling-methods-and-views.md)
2. [Java Diagnostic Evidence](java-diagnostic-evidence.md)
3. [Performance Investigation Checklists](performance-investigation-checklists.md)

## Monitoring Versus Profiling

| Practice | Answers | Example evidence |
| --- | --- | --- |
| Monitoring | What is happening to the running system over time? | CPU, memory, request rate, error rate, GC pauses. |
| Profiling | Where is time, allocation, or blocking spent? | Flame graph, allocation profile, lock profile. |
| Tracing | Which path did one request take? | Spans across service, database, and remote calls. |
| Logging | What discrete events happened? | Request IDs, errors, retries, state transitions. |

Good diagnosis correlates these signals instead of trusting one artifact alone.

## Safe Evidence Collection

- Reproduce the issue if possible.
- Capture the smallest useful diagnostic window.
- Record workload, Java version, JVM flags, and environment.
- Avoid excessive overhead on shared or production-like systems.
- Prefer read-only diagnostic commands when learning.
- Treat heap dumps, thread dumps, and recordings as sensitive artifacts.
- Do not commit dumps, recordings, or generated profiler output.

## Review Questions

1. When is a wall-clock profile more useful than a CPU profile?
2. Why can an allocation profile show pressure even when there is no leak?
3. What does inclusive time include that exclusive time does not?
4. How can a thread dump help diagnose contention?
5. Why should one profile rarely be the final conclusion?
