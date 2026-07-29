# Circuit Breakers, Bulkheads, And Fallbacks

Circuit Breakers, Bulkheads, And Fallbacks introduces the vocabulary and engineering habits needed to reason about failure before it becomes an incident.

Stage 24 builds on Stage 23 observability by asking what to do after telemetry shows that something is slow, failing, saturated, or recovering. Observability supplies evidence; reliability engineering uses that evidence to design bounded failure, safer recovery, and clearer tradeoffs.

## Scope

This section is framework-light and Java-oriented. It focuses on terms, design questions, and deterministic examples you can reason about without external infrastructure.

## Recommended Reading Order

1. [Circuit Breaker Pattern](circuit-breaker-pattern.md)
2. [Bulkheads And Resource Isolation](bulkheads-and-resource-isolation.md)
3. [Fallbacks And Graceful Degradation](fallbacks-and-graceful-degradation.md)

## Files

- [Circuit Breaker Pattern](circuit-breaker-pattern.md)
- [Bulkheads And Resource Isolation](bulkheads-and-resource-isolation.md)
- [Fallbacks And Graceful Degradation](fallbacks-and-graceful-degradation.md)
