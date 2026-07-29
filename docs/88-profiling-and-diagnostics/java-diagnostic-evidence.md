# Java Diagnostic Evidence

Java ships with useful diagnostic concepts and commands. These examples are for learning and incident playbooks; they do not require installing system-level profilers.

## Common Evidence Sources

| Evidence | Helps answer |
| --- | --- |
| Stack trace | What path produced this error or blocked point? |
| Thread dump | What are threads doing right now? |
| Heap dump | Which objects are live and what retains them? |
| Heap histogram | Which classes have many live instances or bytes? |
| GC logs | How often collection happens and how long pauses last. |
| Java Flight Recorder | Low-overhead runtime events over a capture window. |
| Java Mission Control | UI for inspecting JFR recordings. |

## Tool Concepts

| Tool concept | Typical use |
| --- | --- |
| `jcmd` | Ask a local JVM for diagnostics such as VM info, JFR control, or heap summaries. |
| `jstack` | Capture thread stacks for a Java process. |
| `jmap` | Capture heap histograms or heap dumps. |
| `jstat` | Observe JVM statistics such as GC activity. |
| async-profiler | Sampling profiler often used for CPU, allocation, lock, and wall-clock profiles. |
| IDE profiler | Convenient local profiling while developing and learning. |

Example-only commands:

```bash
jcmd <pid> VM.version
jcmd <pid> Thread.print
jcmd <pid> GC.class_histogram
```

Run diagnostics only against processes you own and in environments where collection is approved.

## Thread States

| State | Meaning | Performance clue |
| --- | --- | --- |
| RUNNABLE | Running or ready to run. | CPU work or native wait can appear here. |
| BLOCKED | Waiting to enter a synchronized block or method. | Monitor contention. |
| WAITING | Waiting indefinitely for another action. | Idle pool, latch wait, or missed notification. |
| TIMED_WAITING | Waiting with a timeout. | Sleep, timed poll, scheduled work, or socket timeout. |

Thread dumps are snapshots. Capture more than one when diagnosing changing behavior.

## Heap Dumps And Histograms

A histogram shows classes by live instance count and bytes. A heap dump can show retained objects and dominator trees. Retained size is often more useful than shallow size when diagnosing leaks because it points to objects that keep larger graphs alive.

Heap dumps may contain user data, secrets, and machine-specific details. Do not commit them.
