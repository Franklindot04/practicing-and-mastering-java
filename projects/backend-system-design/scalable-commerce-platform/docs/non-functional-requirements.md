# Non Functional Requirements

The reference architecture models latency budgets, capacity pressure, availability assumptions, durability of accepted orders, inventory consistency, projection freshness, dead-letter handling, security boundaries, tenant isolation, observability, rollback awareness, and cost visibility.

The simulator is deterministic and infrastructure-independent. It validates architecture decisions through Java tests, not through Docker, cloud services, databases, brokers, or external APIs.
