# Capacity Scaling And Cost Notes

Capacity planning in this case study is estimate-driven. Real systems should measure traffic and revise assumptions.

## Example Estimate Inputs

| Input | Example Assumption | Why It Matters |
| --- | --- | --- |
| Create-order requests | 20 per second average | Write path capacity |
| Status reads | 200 per second average | Read model and cache pressure |
| Peak multiplier | 5x during campaigns | Burst planning |
| Average line items | 3 per order | Payload and storage size |
| Retention | Years for order history | Storage and archival cost |

## Scaling Approach

Start simple:

- Stateless API instances.
- Local order module with explicit boundaries.
- Durable order storage in a future adapter.
- Read model only when query pressure requires it.
- Rate limiting at the API boundary for abusive or accidental spikes.

Scale when evidence appears:

- Add caching for safe, frequently read order summaries.
- Add asynchronous notification processing.
- Add partitioning only when data volume or write throughput requires it.
- Add replicas only for read-heavy pressure and known stale-read tolerance.

## Caching

Order status caching may help reads, but it can show stale state. Cache only fields where short staleness is acceptable and define invalidation behavior.

## Rate Limiting

Rate limits protect the write path from runaway clients. Per-customer or per-client limits are often more useful than one global limit.

## Cost Awareness

- Every replica costs money and operational attention.
- Every async boundary needs monitoring and replay or dead-letter handling.
- Every extra service needs deployment, logs, traces, and ownership.
- Multi-region designs are powerful but expensive and complex.

Choose complexity only when requirements justify it.
