# Concurrency Exercises

## Exercise 1: Thread Lifecycle Logger

Difficulty: Advanced

Concepts practiced: `Thread`, `Runnable`, `join`

Problem statement: create two worker threads that print their names and finish before the main thread prints `All done`.

Hints:

- Use `Thread.currentThread().getName()`.
- Call `join` on both worker threads.

Stretch challenge: measure how long both workers take together.

## Exercise 2: Executor Task Queue

Difficulty: Advanced

Concepts practiced: `ExecutorService`, task submission, shutdown

Problem statement: submit five tasks to a fixed thread pool and print which thread runs each task.

Hints:

- Use `Executors.newFixedThreadPool`.
- Always call `shutdown`.

Stretch challenge: use `awaitTermination` and print a timeout message if tasks do not finish.

## Exercise 3: CompletableFuture Pipeline

Difficulty: Advanced

Concepts practiced: `CompletableFuture`, asynchronous pipelines

Problem statement: fetch or create a username asynchronously, convert it to uppercase, and print a greeting.

Hints:

- Use `supplyAsync`.
- Chain with `thenApply`.
- End with `join`.

Stretch challenge: add `exceptionally` to return a fallback greeting.

## Exercise 4: Safe Shared Counter

Difficulty: Advanced

Concepts practiced: synchronization, locks, shared state

Problem statement: increment a shared counter from two threads and make sure the final result is correct.

Hints:

- Try the unsafe version first.
- Fix it with `synchronized` or `ReentrantLock`.

Stretch challenge: compare the solution with `AtomicInteger`.

## Exercise 5: Concurrent Word Count

Difficulty: Advanced

Concepts practiced: concurrent collections, `ConcurrentHashMap`

Problem statement: count repeated words using `ConcurrentHashMap` and `merge`.

Hints:

- Use `map.merge(word, 1, Integer::sum)`.
- Try processing words with a parallel stream.

Stretch challenge: sort the final counts by highest frequency.
