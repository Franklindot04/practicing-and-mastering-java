# Availability, Scalability, And Reliability

## Availability

Availability is the ability of a system to respond when users need it. High availability usually means removing or reducing single points of failure and running enough redundancy to survive common failures.

## Scalability

Scalability is the ability to handle more load. Vertical scaling means giving one instance more CPU or memory. Horizontal scaling means running more instances.

Horizontal scaling is common for stateless backend services, but it still depends on databases, caches, queues, and network limits.

## Reliability

Reliability is the ability to behave correctly over time. A service that responds quickly but loses data is not reliable.

## Resilience And Fault Tolerance

Resilience is the ability to recover or continue when something goes wrong. Fault tolerance means the system can absorb some failures without full outage.

## Single Points Of Failure

A single point of failure is one component whose failure can break the system. Examples include one backend instance, one database with no backup plan, one load balancer without redundancy, or one untested deployment path.

