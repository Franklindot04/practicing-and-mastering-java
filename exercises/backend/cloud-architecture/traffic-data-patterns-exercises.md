# Traffic And Data Patterns Exercises

## Exercise 1: Request Flow

Difficulty: Beginner

Concepts practiced: DNS, load balancer, backend service, managed database

Problem statement: Draw a text diagram for a task API request that flows through DNS, a load balancer, backend instances, and a managed database.

Hints: Use arrows and keep the diagram readable.

Stretch challenge: Add where health checks influence the flow.

## Exercise 2: Cache Or Queue

Difficulty: Intermediate

Concepts practiced: caching, queues, async processing

Problem statement: A report export takes 30 seconds. Decide whether a cache, queue, or neither is the better first architecture pattern. Explain why.

Hints: Think about user wait time, repeated reads, and background work.

Stretch challenge: Describe one failure mode introduced by your choice.

## Exercise 3: Object Storage Planning

Difficulty: Beginner

Concepts practiced: object storage, metadata, managed database

Problem statement: Explain how a task API could store generated report files using object storage and database metadata.

Hints: Store file-like content separately from searchable metadata.

Stretch challenge: Add one cleanup concern.

