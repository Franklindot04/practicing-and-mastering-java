# JVM Runtime, Memory, And JIT

A Java program runs inside a JVM process. Source code compiles to bytecode, classes are loaded, verified, linked, and initialized, and methods begin in interpreted execution before hot paths may be compiled by the JIT. Tiered compilation gathers profiling data, compiles increasingly optimized code, and may deoptimize when assumptions become invalid.

Inlining removes call overhead and exposes new optimization opportunities. Escape analysis can prove an object does not escape a scope, enabling scalar replacement or stack-like allocation effects. These optimizations are workload-sensitive, so benchmark structure can accidentally measure compiler behavior instead of application behavior.

Important memory areas include the heap, thread stacks, metaspace, code cache, direct memory, native memory, and memory used by the operating system and libraries. The heap stores most objects. Thread stacks store frames and local execution state. Metaspace stores class metadata. The code cache stores compiled code. Direct memory supports off-heap buffers.

Allocation is usually cheap, but allocation rate matters. Object lifetime, reachability, retention, reference types, and collection pressure determine memory behavior. Memory leaks in Java are usually unwanted retention, not unreachable objects. OutOfMemoryError can come from heap exhaustion, metaspace, direct buffers, native memory, thread creation, or GC overhead limits.

Compressed references reduce pointer size for many heaps, but the effective behavior depends on heap size and JVM ergonomics. Native memory tracking can help when RSS grows but heap metrics do not explain it.

## Evidence Warnings

Benchmark and runtime data are evidence, not prophecy. Results depend on workload, hardware, JVM version, flags, data shape, warm-up, neighboring processes, and production architecture. Do not copy JVM flags or treat a microbenchmark as whole-system truth without workload evidence.
