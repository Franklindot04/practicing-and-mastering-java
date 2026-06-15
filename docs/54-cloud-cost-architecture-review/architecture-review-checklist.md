# Architecture Review Checklist

Use this checklist before treating a cloud design as ready.

## Scope

- What problem does the architecture solve?
- What is intentionally out of scope?
- Is this a learning design, test design, or production design?

## Traffic And Data

- How does traffic reach the backend?
- Which components are stateful?
- Where does persistent data live?
- What is cached, queued, or stored as an object?

## Availability And Reliability

- What are the single points of failure?
- What happens if one backend instance fails?
- What happens if the database is slow or unavailable?
- Are health checks and rollback paths defined?

## Cost

- Which components scale with traffic?
- Which logs, metrics, storage, and data transfer paths may grow unexpectedly?
- Are quotas, budgets, or alerts needed?

