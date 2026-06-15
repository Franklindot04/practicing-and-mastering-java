# Monitoring, SLOs, And Capacity

## Monitoring Architecture Health

Monitoring should show whether the system is healthy from the user's point of view and from the operator's point of view.

Useful signals include:

- Request rate.
- Error rate.
- Latency.
- Saturation such as CPU, memory, queue depth, or database connections.

## SLO, SLA, And Error Budget Concepts

An SLO is an internal reliability target. An SLA is an external promise or contract. An error budget is the allowed amount of unreliability before the team should slow risky changes and improve reliability.

## Capacity Planning

Capacity planning estimates the resources needed for expected traffic. It should include backend instances, database limits, queue depth, cache size, storage growth, and observability volume.

