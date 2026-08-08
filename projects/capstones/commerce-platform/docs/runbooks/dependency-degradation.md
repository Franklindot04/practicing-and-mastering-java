# Dependency Degradation Runbook

Confirm whether the dependency is optional or required for checkout. For optional dependencies, continue core checkout and record degraded status. For required dependencies, prefer bounded rejection over slow failure.

## Triage

1. Identify the affected dependency and whether it is on the critical checkout path.
2. Check load-shedding and failure counts.
3. Decide whether to continue in degraded mode, reject new work, or pause downstream processing.

## Monitoring Expectations

Production monitoring would track dependency latency, timeout rate, circuit-breaker state, fallback usage, and business impact. The capstone records only local degraded status and counters.
