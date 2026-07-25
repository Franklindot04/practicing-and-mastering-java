# Kafka Architecture

Kafka stores records in topics split into partitions.

```text
Producer
  |
  v
Kafka cluster
  |
  +-- Broker 1: topic orders partition 0
  +-- Broker 2: topic orders partition 1
  +-- Broker 3: topic orders partition 2
  |
  v
Consumer group
```

## Brokers

A broker is a Kafka server. A Kafka cluster has one or more brokers.

Producers write records to brokers. Consumers read records from brokers.

## Topics

A topic is a named stream of records, such as `orders`, `payments`, or `task-events`.

## Partitions

Partitions allow a topic to scale.

Each partition is ordered, but the topic as a whole is not globally ordered across all partitions.

## Java-Oriented Example

A Java service might publish a record when a task is created:

```text
topic: task-events
key: task-123
value: TaskCreated payload
```

The key helps Kafka choose a partition, which helps related records stay ordered within that partition.

## Review Questions

1. What does a Kafka broker do?
2. Why are topics split into partitions?
3. Why is global ordering across a topic difficult?
