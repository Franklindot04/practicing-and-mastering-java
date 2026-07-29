# Java Performance Patterns

This standalone Maven project demonstrates performance-related Java patterns while keeping correctness as the test oracle. The examples do not make wall-clock promises because local timing is affected by JVM warm-up, CPU scheduling, garbage collection, and background work.

## Topics

- repeated lookup versus indexed lookup;
- unnecessary allocation versus object reuse tradeoffs;
- loop string concatenation versus `StringBuilder`;
- boxing overhead and primitive-array alternatives;
- defensive copying tradeoffs;
- bounded cache and memoization examples;
- batch and chunk processing;
- avoiding repeated parsing;
- immutable object tradeoffs;
- synchronization contention and lock granularity;
- concurrent counters;
- bounded executor usage and thread-pool sizing discussion;
- lazy initialization and precomputation;
- short-circuiting and algorithmic improvement;
- careful elapsed-time measurement with `System.nanoTime`;
- benchmark traps such as warm-up, dead-code elimination, and fragile performance assertions.

## Run

```bash
mvn test
```

The tests verify equivalence and invariants. They intentionally avoid fixed timing thresholds.
