# Messaging And Streaming Multiple Choice Quiz

## MC1. What does broker acknowledgement to a producer prove?

A. Every consumer completed business work
B. The broker accepted the message according to the configured policy
C. External side effects are exactly once
D. The message can never be duplicated

## MC2. Which Kafka key best preserves per-order workflow order?

A. Random UUID per send
B. Order ID
C. Event type only
D. Current minute

## MC3. What is consumer lag?

A. Number of producers in a topic
B. Distance between produced offsets and consumed or committed offsets
C. RabbitMQ prefetch count
D. JMS selector syntax

## MC4. Which RabbitMQ exchange broadcasts to all bound queues?

A. Direct
B. Headers
C. Fanout
D. Single

## MC5. What does RabbitMQ prefetch control?

A. Number of unacknowledged deliveries a consumer can hold
B. Kafka retention duration
C. JMS provider name
D. Schema compatibility mode

## MC6. Which statement about JMS is safest?

A. JMS makes all providers operationally identical
B. Kafka directly implements JMS queue and topic semantics
C. JMS defines a Java API implemented by providers
D. JMS removes the need for acknowledgements

## MC7. Which schema change is usually safest?

A. Add an optional field with a default
B. Remove a required field
C. Rename a field in place
D. Change cents to dollars without versioning

## MC8. Why do consumers need idempotency?

A. Acknowledgements eliminate duplicates
B. Redelivery can happen after side effects
C. Brokers never persist messages
D. Serialization always fails

## MC9. What is a poison message?

A. A message that likely cannot succeed through retry without repair
B. A compressed Kafka batch
C. A durable queue
D. A JMS selector

## MC10. What should replay avoid by default?

A. Rebuilding projections
B. Reading retained messages
C. Repeating protected external side effects
D. Recording audit history

## MC11. What is event time?

A. When the business event happened
B. When a processor handled the record
C. When a broker was installed
D. When a consumer group was created

## MC12. Which metric best indicates RabbitMQ consumers have deliveries in flight?

A. Unacknowledged message count
B. Kafka compaction ratio
C. JVM source version
D. Schema registry subject name
