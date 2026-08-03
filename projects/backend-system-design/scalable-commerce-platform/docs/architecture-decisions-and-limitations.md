# Architecture Decisions And Limitations

ADRs in this project favour a modular architecture first, event-driven projections for non-critical read models, idempotent payment boundaries, explicit inventory invariants, and deterministic fitness tests.

Limitations: this is an educational simulator. It does not deploy services, run a real database, process real payments, implement cloud routing, or prove production readiness. Production comparison requires infrastructure, telemetry, security review, load testing, incident drills, and operational ownership.
