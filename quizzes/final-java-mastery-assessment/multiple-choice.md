# Multiple Choice

Choose the best answer and explain why alternatives are weaker.

1. MC-01: Which collection best preserves insertion order while preventing duplicates?
   - A. `ArrayList`
   - B. `HashSet`
   - C. `LinkedHashSet`
   - D. `PriorityQueue`
2. MC-02: Which statement about Java generics is safest?
   - A. Generic type information is fully available at runtime.
   - B. Wildcards can express producer and consumer variance.
   - C. Raw types improve type safety.
   - D. Arrays and generics have identical runtime behavior.
3. MC-03: What is the main risk of catching `Exception` and ignoring it?
   - A. The program always stops immediately.
   - B. Failures become hidden and state may become misleading.
   - C. Compilation fails.
   - D. Checked exceptions become unchecked automatically.
4. MC-04: Which concurrency tool is best for a bounded pool of reusable worker threads?
   - A. `ExecutorService`
   - B. `Thread.sleep`
   - C. `StringBuilder`
   - D. `System.gc`
5. MC-05: What does a high p99 latency usually indicate?
   - A. Median users are always slow.
   - B. Tail behavior needs investigation.
   - C. Throughput is zero.
   - D. Garbage collection is always the cause.
6. MC-06: Which API design choice improves validation clarity?
   - A. Accept all fields as strings and parse later.
   - B. Use typed request records and explicit validation.
   - C. Hide validation in logs only.
   - D. Return stack traces to clients.
7. MC-07: What is the safest statement about local in-memory capstone tests?
   - A. They prove deployment readiness.
   - B. They prove behavior under modeled assumptions.
   - C. They prove cloud reliability.
   - D. They replace security review.
8. MC-08: What does idempotency primarily reduce?
   - A. Duplicate side effects from retries.
   - B. The need for tests.
   - C. All latency.
   - D. Java compilation time.
9. MC-09: Which Kubernetes concept exposes a stable network endpoint for pods?
   - A. ConfigMap
   - B. Service
   - C. Namespace
   - D. Secret
10. MC-10: Which artifact should not be committed?
   - A. Markdown guide
   - B. Java source file
   - C. Heap dump
   - D. Maven POM
11. MC-11: What is a circuit breaker meant to prevent?
   - A. Repeated calls to a dependency already failing.
   - B. All authentication checks.
   - C. Source-code formatting.
   - D. Database schema design.
12. MC-12: Which statement about event-driven systems is most accurate?
   - A. Events remove all consistency concerns.
   - B. Consumers should be safe under redelivery.
   - C. Brokers guarantee business correctness.
   - D. Event schemas never evolve.
