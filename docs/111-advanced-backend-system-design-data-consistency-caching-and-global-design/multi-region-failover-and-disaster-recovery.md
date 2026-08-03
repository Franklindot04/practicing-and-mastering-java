# Multi Region Failover And Disaster Recovery

Multi-region design should begin with recovery goals, not with a map. More regions can improve some failure scenarios while increasing cost, replication lag, operational complexity, and split-brain risk.

## Key Terms

Regions are geographically separate infrastructure locations. Availability zones are isolated locations inside a region. Control planes manage infrastructure. Data planes serve runtime traffic. A control-plane outage can block changes while the data plane keeps serving.

RPO, recovery point objective, states how much data loss is acceptable. RTO, recovery time objective, states how long recovery may take.

Active-passive keeps one primary serving writes while another region stands by. It is simpler than active-active but may have slower failover and colder capacity.

Active-active serves traffic from multiple regions. It can reduce latency and improve regional survivability, but it requires conflict resolution, data locality, routing, and operational maturity.

## Design Questions

- Which workflows must survive regional failure?
- Which data can be lost within the RPO?
- Which data must never be accepted in two conflicting regions?
- Is failover manual, automated, or semi-automated?
- How is traffic routed?
- How are writes replicated?
- How is replication lag measured?
- How does failback work?
- What happens to in-flight payments, messages, and inventory reservations?
- What data residency rules limit movement?

## Failure Modes

Cross-region replication lag can make a newly failed-over region stale. Split brain can let two regions accept conflicting writes. Dependency locality can break failover if identity, payment, DNS, secrets, or message brokers remain region-bound.

Regional evacuation must consider capacity. A secondary region that normally serves 10 percent of traffic may not safely absorb 100 percent without pre-warmed capacity or load shedding.

Conflict resolution needs domain rules. Last-write-wins may be acceptable for a profile display name and unacceptable for inventory, payment, or security roles.

## Disaster Recovery Practices

Backup, restore, and failover drills are evidence. A written runbook without testing is an assumption. DR plans should include:

- restore frequency and restore verification
- backup encryption and access control
- failover trigger criteria
- operator roles
- communication plan
- data validation after failover
- failback criteria
- audit record of decisions

Testing regional failure should avoid harming users. Use simulations, game days, isolated environments, and controlled failover tests. A deterministic Java model can teach RPO/RTO trade-offs, but it cannot prove that cloud routing, DNS, credentials, or managed databases will behave correctly in production.
