# Architecture Decision Records

## ADR 1: Use a Modular Monolith Simulation

The capstone uses one Maven module with explicit internal boundaries. This keeps the learning environment local and deterministic while still requiring clear ownership decisions.

## ADR 2: Use In-Memory Durable-Style Boundaries

Maps and queues simulate persistence, cache, outbox, and projection boundaries. This supports scenario testing without external infrastructure.

## ADR 3: Treat Search as Eventually Consistent

Search projection updates only after event processing, making lag visible and testable.
