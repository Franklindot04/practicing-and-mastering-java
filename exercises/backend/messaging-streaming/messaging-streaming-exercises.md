# Messaging And Streaming Exercises

## Exercise 1: Choose Queue Topic Or Direct Call

Difficulty: Beginner

Concepts: queues, topics, request/response, producers, consumers

Problem: For each scenario, choose direct request/response, a queue, or publish/subscribe topic: password reset email, payment authorization, audit logging, image thumbnail generation, and dashboard projection update.

Constraints: Explain who produces, who consumes, and what failure should do to the original request.

Hints: Immediate answers often fit direct calls. Independent reactions often fit topics. Background work often fits queues.

Stretch challenge: Add one case where messaging is unnecessary.

## Exercise 2: Delivery Semantics Design

Difficulty: Intermediate

Concepts: at-most-once, at-least-once, exactly-once concepts, acknowledgements

Problem: Design handling for `InvoicePaid` messages where duplicate receipts must be avoided.

Constraints: Describe acknowledgement timing, storage checks, duplicate behavior, and what happens after a crash.

Hints: Think about the database write and acknowledgement as separate steps.

Stretch challenge: Explain why a broker setting alone cannot guarantee the receipt is sent exactly once.

## Exercise 3: Retry And Dead-Letter Policy

Difficulty: Intermediate

Concepts: retry limits, backoff, dead-letter handling, poison messages

Problem: A consumer calls a slow notification dependency. Design retry behavior for timeouts and malformed payloads.

Constraints: Separate transient failures from permanent failures.

Hints: Timeouts may benefit from backoff. Malformed payloads usually should not retry forever.

Stretch challenge: List the fields operators need in the dead-letter record.

## Exercise 4: Idempotent Consumer

Difficulty: Intermediate

Concepts: idempotent consumers, duplicate message handling, message identifiers

Problem: Sketch a Java consumer that receives `TaskClosed` messages and updates a read model only once per message.

Constraints: Include a processed-message check, a durable mark-as-processed step, and an acknowledgement decision.

Hints: The message id and the business key are not always the same thing.

Stretch challenge: Explain when a unique database constraint helps.

## Exercise 5: Ordering And Keys

Difficulty: Intermediate

Concepts: message ordering, message keys, Kafka partitions

Problem: A task service emits `TaskCreated`, `TaskAssigned`, and `TaskClosed`. Choose a key and explain ordering limits.

Constraints: Explain what happens if records use random keys.

Hints: Ordering is strongest when related records share a stable key.

Stretch challenge: Explain why retries can still make effects appear out of order.

## Exercise 6: Kafka Consumer Group Reading

Difficulty: Intermediate

Concepts: Kafka topics, partitions, consumer groups, offsets, lag

Problem: A topic has three partitions and a consumer group has two consumers. Describe partition assignment, offset progress, and lag.

Constraints: Do not assume global ordering across all partitions.

Hints: One consumer may own more than one partition.

Stretch challenge: Explain what changes when a third consumer joins.

## Exercise 7: RabbitMQ Routing

Difficulty: Intermediate

Concepts: RabbitMQ exchanges, bindings, routing keys

Problem: Design routing for `task.created`, `task.closed`, and `task.failed` messages so audit receives all task messages and email receives only created and failed messages.

Constraints: Choose exchange type and binding patterns.

Hints: Topic exchange patterns can match groups of routing keys.

Stretch challenge: Add a queue for failures only.

## Exercise 8: Serialization Boundary

Difficulty: Intermediate

Concepts: serialization, message contracts, Java abstraction design

Problem: Define a Java interface that keeps serialization separate from domain event handling.

Constraints: Include how schema/version changes would be tested.

Hints: Domain handlers should not need broker record APIs.

Stretch challenge: Add a compatibility test idea.

## Exercise 9: Operational Troubleshooting

Difficulty: Advanced

Concepts: correlation identifiers, consumer lag, queue depth, dead-letter volume, troubleshooting

Problem: Users report delayed emails. Describe how you investigate producer metrics, queue depth or lag, consumer throughput, retry counts, and dead letters.

Constraints: Include logs or traces keyed by correlation id.

Hints: A healthy broker does not prove a healthy consumer.

Stretch challenge: Identify one mitigation that does not require code changes.

## Exercise 10: Messaging Abstraction Review

Difficulty: Advanced

Concepts: producer boundary, consumer boundary, retry design, dead-letter handling, idempotency storage

Problem: Review a proposed Java service that directly imports broker APIs inside domain services. Recommend a cleaner design.

Constraints: Keep future Kafka or RabbitMQ adapters possible without rewriting domain logic.

Hints: Ports and adapters are useful when they protect the domain from infrastructure details.

Stretch challenge: Sketch package names for the design.
