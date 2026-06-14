# Concurrency And Multithreading

Concurrency lets programs handle multiple tasks during overlapping time periods. Java provides threads, executors, futures, locks, and concurrent collections.

Key topics:

- `Thread` and `Runnable`
- `ExecutorService`
- `CompletableFuture`
- `synchronized`
- `Lock` and `ReentrantLock`
- Concurrent collections
- Race conditions
- Deadlocks

Common mistakes:

- Sharing mutable state without protection.
- Creating raw threads for every task.
- Forgetting to shut down executors.
- Blocking inside asynchronous pipelines.

Practice prompts:

- Process a list of filenames with an executor.
- Demonstrate a race condition with a counter.
- Fix the counter with synchronization.
- Compare `HashMap` and `ConcurrentHashMap`.

Before moving on, you should understand why concurrent code can fail even when each line looks correct by itself.
