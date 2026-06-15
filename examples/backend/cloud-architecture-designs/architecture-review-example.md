# Architecture Review Example

Review this simple design:

```text
Client -> DNS -> Load Balancer -> Backend Service Instances -> Managed Database
```

## Review Questions

- Are backend instances stateless?
- How many database connections can each instance open?
- What happens if the database is unavailable?
- Are health checks defined?
- What metrics show saturation?
- What is the rollback path after a bad deployment?
- Which parts scale with traffic and cost?

## Example Findings

- The load balancer helps with instance failures, but the database is still a critical dependency.
- More backend instances may overload the database if connection pools are not limited.
- The diagram does not show secrets, logging, backups, or incident response.

## Next Improvement

Add explicit notes for health checks, database connection limits, backup/restore expectations, and cost alerts before calling the design production-ready.

