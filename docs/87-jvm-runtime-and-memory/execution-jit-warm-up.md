# Execution, JIT, And Warm-Up

The JVM does not simply run Java source code. Source is compiled to bytecode, bytecode is loaded and verified, and then the runtime decides how to execute it.

## Interpreter And JIT

| Runtime behavior | Strength | Tradeoff |
| --- | --- | --- |
| Interpreting bytecode | Starts quickly and gathers profile data. | Slower peak execution. |
| JIT compiling hot code | Can specialize and optimize real hot paths. | Compilation costs CPU and depends on warm-up. |
| Tiered compilation | Balances startup with later optimization. | Measurements can shift as tiers change. |

The JVM can inline methods, remove unused work, fold constants, optimize branches, and sometimes replace object allocation with scalar values. These optimizations are powerful, but they also create benchmark traps.

## Warm-Up

Warm-up is the period where class loading, interpretation, profiling, compilation, caches, and data structures settle. A short run may mostly measure startup effects rather than the operation you care about.

```text
startup -> class loading -> interpretation -> profiling -> JIT compilation -> steadier measurements
```

Warm-up does not guarantee perfectly stable performance. Garbage collection, OS scheduling, data shape, dependency latency, and CPU behavior can still move results.

## Hotspot Detection And Deoptimization

The JVM optimizes based on observations, such as which methods are hot and which implementations are usually seen at a call site. If reality changes, the JVM can deoptimize and choose a safer execution path. This is one reason performance can change after new code paths or data patterns appear.

## Benchmark Traps

| Trap | Why it matters |
| --- | --- |
| Dead-code elimination | If a computed result is unused, the JVM may remove the work. |
| Constant folding | Work using compile-time constants may be precomputed. |
| Unrealistic input | Perfectly repeated data can favor optimizations not present in production. |
| Measuring cold code | Startup behavior can hide steady-state costs. |
| Single run confidence | One result may reflect noise, compilation timing, or GC. |

## Java Example

```java
long sum = 0;
for (int i = 0; i < values.length; i++) {
    sum += values[i] * 31L;
}
return sum;
```

Returning or otherwise observing `sum` matters. If the loop result is never used, a microbenchmark may measure removed work rather than real work.
