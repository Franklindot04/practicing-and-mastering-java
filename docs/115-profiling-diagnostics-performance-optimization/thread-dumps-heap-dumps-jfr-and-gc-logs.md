# Thread Dumps, Heap Dumps, JFR, And GC Logs

Thread dumps show what Java threads are doing at a point in time. Important states include RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED. One dump can mislead; several dumps across time reveal whether a thread is stuck, progressing, or repeatedly blocked. Deadlocks, pool exhaustion, long synchronized sections, blocked I/O, and starvation often appear in thread evidence.

Heap dumps show object graphs. Dominator trees and retained size help identify why memory cannot be reclaimed. A large object is less interesting than the object retaining an entire graph. Heap dumps can contain sensitive data and should be handled carefully.

GC logs reveal pause timing, heap occupancy, allocation pressure, promotion, collector activity, and sometimes humongous allocation behavior. Interpret them with latency, throughput, CPU, and allocation evidence. Garbage collection cannot be optimized responsibly without measuring allocation and pause behavior.

JFR events can correlate CPU, allocation, locks, garbage collection, exceptions, file I/O, socket I/O, and thread activity. The limitation is still causation: events show what happened, not automatically why it happened.
