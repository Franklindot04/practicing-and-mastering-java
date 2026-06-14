# Concurrency Solutions

## Thread Lifecycle Logger

Create `Runnable` tasks, start them with `Thread`, and call `join` before printing the final message. Without `join`, the main thread may print before workers finish.

## Executor Task Queue

Use a fixed thread pool for a small, bounded number of workers. Submit tasks, call `shutdown`, then optionally wait with `awaitTermination`.

## CompletableFuture Pipeline

Start with `CompletableFuture.supplyAsync`, chain transformations with `thenApply`, and use `join` at the boundary where the result is needed.

## Safe Shared Counter

Protect increments with `synchronized`, `ReentrantLock`, or an atomic class. The key idea is that read-modify-write operations must not overlap unsafely.

## Concurrent Word Count

Use `ConcurrentHashMap` and `merge` so updates for the same key are coordinated safely. This is clearer than manually checking and updating counts.
