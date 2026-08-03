# Profiling CPU, Memory, Allocation, And Locks

Profiling identifies where time, allocation, memory, or contention occurs. CPU profiling samples runnable execution. Wall-clock profiling includes waiting and blocking. Allocation profiling shows object creation rate and allocation sites. Memory profiling studies retained objects and reachability. Lock profiling highlights contention and monitor waits.

Sampling profilers interrupt execution periodically and usually have lower overhead. Instrumentation profilers modify or wrap execution and can provide richer detail at higher cost. Profiler overhead can change the workload, so results need caution.

Flame graphs show stack frequency and help separate hot leaf methods from expensive call paths. Async-profiler concepts are useful for CPU, allocation, lock, and wall-clock views. Java Flight Recorder records structured JVM and application events with relatively low overhead when configured carefully. Java Mission Control helps inspect JFR recordings. Native memory tracking helps when process memory grows beyond heap evidence.

Production-safe profiling requires explicit limits, owner approval, retention controls, sanitized artifacts, and rollback. A short bounded recording during a known workload is usually safer than a long open-ended capture.
