# Producers Consumers And Groups

Kafka applications are usually producers, consumers, or both.

## Producers

Producers write records to topics.

Producer responsibilities include:

- Choosing topic and key.
- Serializing the value.
- Handling publish errors.
- Avoiding unsafe duplicate side effects.
- Preserving useful correlation identifiers.

## Consumers

Consumers read records from topics.

Consumer responsibilities include:

- Deserializing records.
- Applying business logic.
- Handling duplicates.
- Committing progress at the right time.
- Logging useful context.

## Consumer Groups

A consumer group shares partitions among its members.

```text
topic orders
  partition 0 ---> consumer A
  partition 1 ---> consumer B
  partition 2 ---> consumer B
```

Each partition is assigned to one consumer in the group at a time.

Different groups can read the same topic independently.

## Partition Assignment

Kafka assigns partitions to consumers in a group. If consumers join or leave, assignment can change.

Consumers should be ready for rebalancing and duplicate processing.

## Delivery Semantics

Kafka delivery behavior depends on producer settings, broker persistence, offset commits, consumer processing, and external data stores.

## Review Questions

1. Why can two consumer groups read the same topic independently?
2. What happens when a consumer group has more consumers than partitions?
3. Why should consumers tolerate duplicate records?
