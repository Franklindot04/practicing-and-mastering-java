# Concurrency And JVM Quiz

## Multiple Choice

1. Why should shared mutable data be protected in concurrent code?
   - A. Threads always run in alphabetical order
   - B. Multiple threads can read and write at overlapping times
   - C. Shared data cannot be stored on the heap
   - D. Java disables concurrency by default

2. What does an `ExecutorService` help manage?
   - A. SQL tables
   - B. Thread creation and task execution
   - C. Package imports
   - D. Garbage collector selection only

3. What does `Future.get()` do?
   - A. Starts the JVM
   - B. Waits for and returns a task result
   - C. Deletes a task
   - D. Converts a thread to a process

4. Which collection is designed for concurrent map updates?
   - A. `ArrayList`
   - B. `HashMap`
   - C. `ConcurrentHashMap`
   - D. `TreeSet`

5. What is the heap mainly used for?
   - A. Objects
   - B. Source files
   - C. Terminal commands
   - D. Git commits

## Short Answer

6. Why should an `ExecutorService` be shut down?
7. What problem does `synchronized` solve?
8. Why is garbage collection timing not something application code should rely on?
9. What is one reason performance should be measured before optimizing?

## Code Reading

10. What is the final expected count?

```java
AtomicInteger count = new AtomicInteger();
count.incrementAndGet();
count.incrementAndGet();
System.out.println(count.get());
```

11. What does this pipeline print?

```java
CompletableFuture<String> value = CompletableFuture
        .supplyAsync(() -> "java")
        .thenApply(String::toUpperCase);
System.out.println(value.join());
```
