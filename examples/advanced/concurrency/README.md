# Advanced Concurrency Examples

These examples introduce common Java concurrency building blocks. They are intentionally small so each file can be compiled and studied on its own.

## Examples

- `ThreadBasicsDemo.java`: creates and starts a `Thread`.
- `RunnableDemo.java`: separates a task from the thread that runs it.
- `ExecutorServiceDemo.java`: runs tasks with a fixed thread pool.
- `CallableFutureDemo.java`: returns a value from background work.
- `CompletableFutureDemo.java`: chains asynchronous steps.
- `SynchronizedCounterDemo.java`: protects shared state with `synchronized`.
- `ReentrantLockDemo.java`: protects shared state with an explicit lock.
- `ConcurrentHashMapDemo.java`: safely updates a map from multiple tasks.

## Compile

From the repository root:

```bash
javac examples/advanced/concurrency/*.java
```

## Run

```bash
java -cp examples/advanced/concurrency ThreadBasicsDemo
java -cp examples/advanced/concurrency ExecutorServiceDemo
```

## Common Mistakes

- Starting a thread and assuming it has finished immediately.
- Sharing mutable data without synchronization.
- Forgetting to shut down an `ExecutorService`.
- Blocking on `Future.get()` too early and losing concurrency benefits.
- Making asynchronous code harder to read than a simple loop.

Concurrency needs careful design because timing can change from run to run. Prefer clear ownership of shared data, small tasks, timeouts where practical, and tests around the logic that does not depend on thread timing.
