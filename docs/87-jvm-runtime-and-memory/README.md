# JVM Runtime And Memory

The JVM is both an execution engine and a managed runtime. It loads bytecode, interprets and compiles hot code, manages memory, coordinates garbage collection, and exposes runtime controls through flags and diagnostics.

Performance work on Java applications is safer when you understand what the JVM can optimize, what it cannot prove, and which memory areas are involved.

## Study Order

1. [Execution, JIT, And Warm-Up](execution-jit-warm-up.md)
2. [JVM Memory Areas](jvm-memory-areas.md)
3. [Allocation, Reachability, And GC](allocation-reachability-gc.md)

## Runtime Overview

```text
.java source -> javac -> .class bytecode -> class loading -> interpreter
                                                      |
                                                      v
                                            JIT-compiled machine code
```

The interpreter starts quickly and collects execution information. The just-in-time compiler uses that information to optimize hot paths. This is why a Java program may behave differently during warm-up than after steady state.

## Core Ideas

| Idea | Meaning |
| --- | --- |
| Bytecode | Portable JVM instruction format produced by `javac`. |
| Interpreter | Executes bytecode before the JVM decides compilation is worthwhile. |
| JIT compilation | Compiles hot bytecode paths to native machine code at runtime. |
| Tiered compilation | Uses multiple compilation levels to balance startup and peak performance. |
| Hotspot detection | Runtime observation that identifies frequently executed code. |
| Deoptimization | Falling back when an optimization assumption stops being valid. |
| GC root | Starting point used to determine which objects are reachable. |
| Stop-the-world pause | A period when application threads are paused for runtime work. |

## Review Questions

1. Why can a benchmark measured during startup misrepresent steady-state performance?
2. How can dead-code elimination make a benchmark meaningless?
3. Why can managed Java applications still have memory leaks?
4. What is the difference between allocation rate and retained memory?
5. Why is copying JVM flags from another service unsafe?
