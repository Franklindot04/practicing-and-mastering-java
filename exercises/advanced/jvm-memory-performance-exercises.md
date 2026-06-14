# JVM Memory And Performance Exercises

## Exercise 1: Stack Or Heap

Difficulty: Advanced

Concepts practiced: stack, heap, references

Problem statement: label which parts of a small program live on the stack and which objects live on the heap.

Hints:

- Local variables are associated with stack frames.
- Objects created with `new` live on the heap.

Stretch challenge: explain what happens when a method returns an object reference.

## Exercise 2: Garbage Collection Candidate

Difficulty: Advanced

Concepts practiced: reachability, garbage collection basics

Problem statement: write a small example where an object becomes unreachable and explain why it can be collected.

Hints:

- Reassigning the last reference can make an object unreachable.
- Garbage collection timing is not guaranteed.

Stretch challenge: explain why calling `System.gc()` is only a request.

## Exercise 3: Measure Before Optimizing

Difficulty: Advanced

Concepts practiced: performance reasoning, measurement

Problem statement: compare two implementations of a simple string-building task and explain why measurement matters.

Hints:

- Use `StringBuilder` for repeated appends.
- Run each version more than once.

Stretch challenge: explain why microbenchmarks are easy to get wrong.

## Exercise 4: Memory-Friendly Processing

Difficulty: Advanced

Concepts practiced: memory usage, streaming data

Problem statement: explain when reading all file lines into memory is acceptable and when line-by-line processing is safer.

Hints:

- Think about file size.
- Think about whether all data is needed at once.

Stretch challenge: design a log-processing method that keeps only counts in memory.
