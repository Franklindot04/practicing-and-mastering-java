# Profiling Methods And Views

Profilers collect evidence in different ways. The right profiler view depends on the question.

## Collection Styles

| Style | How it works | Strength | Caution |
| --- | --- | --- | --- |
| Sampling | Periodically records stacks. | Lower overhead and good hot-path signal. | May miss very short or rare methods. |
| Instrumentation | Adds measurement hooks. | Precise counts or timings for selected code. | Higher overhead and can change behavior. |
| Method timing | Measures chosen methods. | Useful around known boundaries. | Can hide nested costs and call frequency effects. |

## Profile Types

| Profile | Looks for |
| --- | --- |
| CPU profile | Where threads spend CPU time doing computation. |
| Wall-clock profile | Where elapsed time goes, including blocking and waiting. |
| Allocation profile | Which code creates objects and at what rate. |
| Memory profile | Which objects remain live and why. |
| Lock profile | Where threads contend for monitors or locks. |
| Thread profile | Thread states, pools, runnable work, and blocking patterns. |
| I/O profile | Disk, socket, database, or remote wait behavior. |

## Reading Views

| View | Useful question |
| --- | --- |
| Call tree | Which callers lead to the expensive work? |
| Flat profile | Which methods consume the most total samples? |
| Flame graph | Which stack paths dominate time or allocation? |
| Hot methods | Which methods appear frequently in samples? |
| Inclusive time | Time in a method plus its callees. |
| Exclusive time | Time in only that method body. |

Flame graphs are wide where many samples share a stack frame. A wide box is not automatically bad; it is a place to ask whether the work is expected and whether it is on the critical path.

## Native And Java Stacks

Some profilers show only Java frames; others can include JVM and native frames. Native visibility matters when time is spent in networking, compression, cryptography, garbage collection, or operating-system calls.

## Profiling Overhead

Every profile changes the observed system at least slightly. Long captures can consume storage and make sensitive artifacts. Short captures may miss rare behavior. Pick a window that matches the symptom, and repeat when evidence is ambiguous.
