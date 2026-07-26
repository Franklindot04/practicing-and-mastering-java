# Scalability Availability And Reliability

Scalability, availability, and reliability are related, but they are not the same thing. A backend can scale to more traffic and still be unreliable. It can be reliable for a small workload and still fail under sudden growth.

This section stays vendor-neutral and design-focused. It explains the concepts a Java backend developer needs before choosing infrastructure or frameworks.

## Topics

- [Scaling And Capacity](scaling-and-capacity.md)
- [Availability Reliability And Durability](availability-reliability-durability.md)
- [Resilience And Fault Isolation](resilience-and-fault-isolation.md)
- [SLOs Recovery And Tradeoffs](slos-recovery-and-tradeoffs.md)

## Key Distinctions

| Term | Meaning | Common Confusion |
| --- | --- | --- |
| Scalability | Ability to handle growth by adding or changing resources | Not the same as current performance |
| Performance | How quickly work completes for a given workload | Fast today does not mean scalable tomorrow |
| Availability | Whether the system can respond when needed | Not the same as always returning correct results |
| Reliability | Whether the system behaves correctly over time | Not the same as durability |
| Durability | Whether committed data survives failures | Not the same as uptime |
| Redundancy | Extra capacity or copies | Not the same as resilience |
| Resilience | Ability to absorb and recover from failure | Requires behavior, not just extra parts |

## Review Questions

- What is the expected normal workload?
- What is the expected peak workload?
- Which resource becomes the bottleneck first?
- Which path must remain available during partial failure?
- Which data must be durable?
- Which failures should degrade gracefully?
- Which reliability target is worth the cost?
