# Circuit Breakers, Bulkheads, And Fallbacks

Circuit breakers fail fast when a dependency is unhealthy, bulkheads isolate resources, and fallbacks provide honest degraded behavior when full success is not available.

Stage 24 follows the observability stage: telemetry helps you notice symptoms, and reliability engineering helps you decide what failure means, how far it can spread, and how recovery should work.

## Scope

This section stays framework-light and Java-oriented. It focuses on concepts that apply whether the Java code later runs in a CLI, web service, batch job, or message consumer.

## Recommended Reading Order

1. [Circuit Breaker Pattern](circuit-breaker-pattern.md)
2. [Bulkheads And Resource Isolation](bulkheads-and-resource-isolation.md)
3. [Fallbacks And Graceful Degradation](fallbacks-and-graceful-degradation.md)

## Files

- [Circuit Breaker Pattern](circuit-breaker-pattern.md)
- [Bulkheads And Resource Isolation](bulkheads-and-resource-isolation.md)
- [Fallbacks And Graceful Degradation](fallbacks-and-graceful-degradation.md)
