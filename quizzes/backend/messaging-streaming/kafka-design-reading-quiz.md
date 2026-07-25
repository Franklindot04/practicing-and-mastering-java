# Kafka Design Reading Quiz

Read this design:

```text
Topic: task-events
Partitions: 4
Producer key: random UUID for every record
Consumer group: task-projection
Handler: updates one read model by task id
Offset commit: before database write
Replay plan: replay last 24 hours if projection is broken
```

Questions:

1. What ordering issue can random keys create for one task?
2. Why is committing before the database write risky?
3. What does consumer lag indicate?
4. What should be true before replaying the last 24 hours?
5. What key would you choose for task lifecycle records?
