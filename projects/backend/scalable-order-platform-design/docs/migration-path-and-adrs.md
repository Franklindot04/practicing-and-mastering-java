# Migration Path And ADRs

## Migration Path

A safe evolution path might look like this:

1. Build a modular order boundary inside one backend.
2. Add clear interfaces for inventory, payment, and notification.
3. Add idempotent order submission.
4. Add observability around failures and latency.
5. Add asynchronous notification handling when needed.
6. Add a read model when read traffic justifies it.
7. Extract a service only when ownership, scaling, or deployment pressure is proven.

## Architecture Decision Records

### ADR 1: Start With A Modular Boundary

Context: One team owns the order platform and domain boundaries are still evolving.

Decision: Keep order behavior in a modular backend boundary first.

Alternatives:

- Split order, inventory, payment, and notification services immediately.
- Build one unstructured CRUD application.

Consequences:

- Simpler deployment and easier local reasoning.
- Module boundaries must remain explicit.
- Extraction remains possible when pressure appears.

Revisit trigger: Independent teams or scaling pressure require separate deployment.

### ADR 2: Keep External Systems Behind Interfaces

Context: Payment, inventory, and notification behavior may change.

Decision: Model external systems as ports, not direct framework or provider calls.

Consequences:

- Tests can run without infrastructure.
- Real adapters can be added later.
- Boundary behavior remains visible in code.
