# JVM Tuning Goals And Memory Controls

Tuning changes runtime behavior. It should be treated like any other production-relevant code change: measured, reviewed, tested, documented, and reversible.

## Memory Controls

| Area | Planning concern |
| --- | --- |
| Minimum heap | Startup allocation and early GC behavior. |
| Maximum heap | Upper bound for Java object memory. |
| Elastic heap | Can adapt to demand but may vary measurements. |
| Fixed heap | More predictable sizing but less flexible. |
| Young generation | Affects short-lived object collection and promotion pressure. |
| Metaspace | Class metadata and classloader behavior. |
| Direct memory | Off-heap buffers, often around I/O. |
| Thread stacks | Native memory used by each thread. |
| Code cache | Space for JIT-compiled code. |

Heap size is not process size. Native memory headroom must include stacks, direct buffers, metaspace, code cache, JVM overhead, and native libraries.

## Common Failure Modes

| Symptom | Possible investigation |
| --- | --- |
| `OutOfMemoryError: Java heap space` | retained objects, live set, heap sizing, leaks. |
| Stack overflow | recursion, stack size, call depth. |
| Direct buffer exhaustion | direct-memory limits and retained buffers. |
| Container killed | process exceeded memory limit, including non-heap memory. |
| High GC overhead | allocation rate, live set size, collector behavior. |

## Safe Flag Management

- Start with a stated goal.
- Record Java version and default behavior.
- Change one coherent setting group at a time.
- Test with representative workload and data.
- Compare throughput, pauses, memory, errors, startup, and CPU.
- Keep rollback instructions.
- Avoid embedding unreviewed flags in deployment files.

Educational examples of flag categories include heap sizing, GC logging, heap dumps on failure, collector selection, stack sizing, metaspace limits, and direct-memory limits. The exact values belong to measured experiments, not generic notes.
