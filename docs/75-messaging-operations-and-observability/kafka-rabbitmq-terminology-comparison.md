# Kafka And RabbitMQ Terminology Comparison

Kafka and RabbitMQ use different models. Similar words can hide important differences.

## Kafka Terms

| Signal | Meaning |
| --- | --- |
| Consumer lag | How far a consumer group is behind the latest records. |
| Offset | Position in a partition. |
| Partition | Ordered slice of a topic. |
| Retention | How long records remain available. |
| Replay | Reading earlier offsets again. |

## RabbitMQ Terms

| Signal | Meaning |
| --- | --- |
| Queue depth | Number of messages waiting in a queue. |
| Unacked messages | Delivered messages not yet acknowledged. |
| Prefetch | Limit for in-flight unacknowledged messages. |
| Dead-letter exchange | Exchange that receives rejected or expired messages. |
| Routing key | Value used by some exchanges to route messages. |

## Practical Difference

Kafka lag usually describes position in a retained log.

RabbitMQ queue depth usually describes pending messages waiting for consumers.

Neither number alone proves the business workflow is healthy.
