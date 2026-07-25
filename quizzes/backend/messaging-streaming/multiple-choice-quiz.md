# Multiple Choice Quiz

1. Which statement best describes a queue?
   - A. Every subscriber receives every message
   - B. Messages are usually distributed to one worker from a group
   - C. Messages are always globally ordered
   - D. Messages never need acknowledgements

2. At-least-once delivery means:
   - A. A message can never be duplicated
   - B. A message may be delivered more than once
   - C. A consumer never fails
   - D. A broker controls the database transaction

3. Which responsibility belongs to an idempotent consumer?
   - A. Applying duplicate side effects
   - B. Skipping work already applied for the same logical message
   - C. Removing all retries
   - D. Replacing message contracts

4. Kafka ordering is strongest:
   - A. Across all partitions in all topics
   - B. Within one partition
   - C. Across all consumer groups
   - D. Only after retention expires

5. RabbitMQ routes messages through:
   - A. Exchanges and bindings
   - B. Offsets and partitions
   - C. Consumer lag only
   - D. Cloud credentials

6. A dead-letter queue is mainly:
   - A. A place to hide errors
   - B. A visibility and containment mechanism for failed messages
   - C. A guarantee that a message was fixed
   - D. A replacement for observability
