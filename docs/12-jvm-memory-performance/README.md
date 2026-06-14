# JVM, Memory, Garbage Collection, And Performance

Java runs on the JVM, which manages memory, executes bytecode, and performs garbage collection.

Key topics:

- Stack vs heap
- Object references
- Garbage collection basics
- JVM memory model basics
- Profiling before optimizing
- Throughput, latency, and allocation pressure

Common mistakes:

- Optimizing without measuring.
- Confusing object references with objects.
- Assuming garbage collection means memory does not matter.
- Treating JVM flags as magic fixes.

Practice prompts:

- Explain where local variables and objects live.
- Create many objects and observe memory use.
- Compare string concatenation in a loop with `StringBuilder`.
- Use a profiler on a small slow program.

Before moving on, you should be able to explain stack, heap, references, and why profiling matters.

## Next Practice

- [ ] Complete JVM memory and performance exercises in [Advanced Exercises](../../exercises/advanced/).
- [ ] Explain how a file-processing project can avoid loading unnecessary data into memory.
- [ ] Measure a small program before trying to optimize it.
