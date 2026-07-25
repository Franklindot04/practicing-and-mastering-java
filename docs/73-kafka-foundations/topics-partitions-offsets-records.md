# Topics Partitions Offsets And Records

Kafka records live in partitions.

```text
topic: task-events

partition 0: [offset 0] [offset 1] [offset 2]
partition 1: [offset 0] [offset 1] [offset 2]
```

## Records

A record commonly has:

- Key
- Value
- Timestamp
- Headers
- Topic
- Partition
- Offset

The value may contain JSON, Avro, Protobuf, or another serialized payload. The format should be treated as a contract.

## Offsets

An offset is a record position within one partition.

Consumers use offsets to know where they are in the stream.

## Retention

Kafka can retain records for a time or size limit.

Retention means records may remain after consumers read them. It does not mean payloads should contain secrets or unnecessary personal data.

## Commit Concepts

Committing an offset records consumer progress.

If a consumer processes a record and commits the offset, Kafka considers that group past the record. If a consumer crashes before committing, the record may be read again.

## Review Questions

1. What is an offset?
2. Why does retention make replay possible?
3. Why should serialized payloads be treated as contracts?
