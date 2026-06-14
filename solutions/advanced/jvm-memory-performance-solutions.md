# JVM Memory And Performance Solutions

## Stack Or Heap

Each method call has a stack frame for local variables and parameters. Objects live on the heap, and local variables may hold references to those objects.

## Garbage Collection Candidate

An object becomes eligible for garbage collection when it is no longer reachable from running code. The JVM decides when collection actually happens.

## Measure Before Optimizing

Prefer the clearest correct code first. Measure with realistic data before optimizing, because small examples may not reflect real application behavior.

## Memory-Friendly Processing

Reading all lines is fine for small files. For large files, process line by line and keep only summaries, such as counts or top results, in memory.
