# Scaling And Capacity

Scalability is the ability to handle increased demand. Capacity is the amount of work a system can handle before it misses important targets such as latency, error rate, or queue delay.

## Vertical Scaling

Vertical scaling means giving one instance more resources.

Examples:

- More CPU.
- More memory.
- Faster disk.
- Larger database instance.

Benefits:

- Simple operational model.
- No distribution logic inside the application.
- Often useful early.

Limits:

- Hardware has a ceiling.
- Larger instances can be expensive.
- One large node may still be a failure point.

## Horizontal Scaling

Horizontal scaling means adding more instances.

```text
             +------------+
Request ---> | Load Split |
             +-----+------+
                   |
        +----------+----------+
        |          |          |
        v          v          v
     API 1      API 2      API 3
```

Horizontal scaling works best when service instances are stateless or share state through well-defined storage.

## Stateless And Stateful Services

A stateless service does not rely on local memory for durable user state. Any healthy instance can handle the next request.

A stateful service owns state that must be preserved or coordinated.

Examples:

| Service Type | Easier To Scale? | Design Concern |
| --- | --- | --- |
| Stateless API | Usually yes | Shared storage and session handling |
| Stateful cache | Sometimes | Eviction, replication, warmup |
| Primary database | Harder | Writes, locks, indexes, durability |
| Worker pool | Often yes | Queue depth, idempotency, retries |

## Load Distribution

Load distribution spreads requests across available capacity. It can happen through load balancers, queues, partition routing, or client-side selection.

Ask:

- Is load evenly distributed?
- Can one tenant or key become hot?
- What happens when one instance is slow?
- Can the system remove unhealthy instances?

## Bottlenecks

Common bottlenecks:

- Database writes.
- Expensive queries.
- Lock contention.
- Slow external APIs.
- Thread pool exhaustion.
- Queue backlog.
- Serialization and large payloads.
- Hot partitions.

Horizontal scaling does not automatically solve these. If every request waits on the same database lock, adding API instances may increase pressure instead of improving throughput.

## Capacity Limits

Capacity thinking starts with rough questions:

- Requests per second.
- Average and peak payload size.
- Read/write ratio.
- Expected concurrent users.
- Slowest dependency.
- Storage growth per day.
- Retention period.

These are estimates, not facts. Measure real systems and update the design.
