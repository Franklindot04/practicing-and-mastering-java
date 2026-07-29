# JVM Memory Areas

JVM memory is not only "the heap." A Java process uses heap memory, thread stacks, metaspace, code cache, direct buffers, and native memory used by the JVM and libraries.

## Main Areas

| Area | Stores | Common risk |
| --- | --- | --- |
| Heap | Java objects and arrays. | High allocation, retained objects, `OutOfMemoryError`. |
| Young generation | Recently allocated objects. | Frequent minor collections under high allocation rate. |
| Old generation | Longer-lived objects. | Promotion pressure and long-lived retention. |
| Survivor regions | Objects that survived young collections. | Tuning tradeoffs depend on lifetime patterns. |
| Thread stacks | Stack frames for each Java thread. | Many threads consume native memory; deep recursion can overflow. |
| Program counter | Current execution position for each thread. | Mostly diagnostic concept for learners. |
| Metaspace | Class metadata. | Excessive class loading or classloader retention. |
| Code cache | JIT-compiled machine code. | Too little space can limit compilation. |
| Direct buffers | Off-heap buffers often used for I/O. | Direct-memory exhaustion outside the Java heap. |
| Native memory | JVM, threads, libraries, and operating-system allocations. | Process may fail even when heap looks acceptable. |

## Stack Frames

Each active method call has a stack frame containing local variables, operand-stack state, and return information. Primitive local variables can live directly in frames. Object local variables hold references to heap objects, not the full object contents.

```text
thread stack                         heap
------------                         ----
frame: handle -> reference --------> Order object
frame: count  -> int value
```

Deep recursion can produce `StackOverflowError`. Very high thread counts can exhaust native memory because each thread needs stack space.

## Objects, References, And Values

Objects have headers used by the JVM for metadata such as identity, locking, and GC information. References point to objects. Primitive fields store values directly inside objects or stack frames.

Boxing turns primitives into objects:

```java
Integer boxed = Integer.valueOf(42);
```

Boxing is often harmless, but heavy boxing in hot paths can increase allocations, cache misses, and GC pressure.

## Heap And Non-Heap Failures

| Failure | Possible cause |
| --- | --- |
| Heap out of memory | Too many live objects or too small a heap for workload. |
| Stack overflow | Recursion or deep call chains exceed stack size. |
| Direct-buffer exhaustion | Off-heap buffers are retained or direct memory limit is too small. |
| Native-memory exhaustion | Too many threads, native libraries, buffers, or JVM overhead. |
| Metaspace pressure | Many loaded classes or classloader retention. |
