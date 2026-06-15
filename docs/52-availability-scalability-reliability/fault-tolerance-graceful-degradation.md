# Fault Tolerance And Graceful Degradation

Fault-tolerant systems expect some components to fail.

## Fault Tolerance

Fault tolerance can include redundant instances, retries with limits, fallback behavior, queues, backups, and health checks. It does not mean the system never fails. It means expected failures are planned for.

## Graceful Degradation

Graceful degradation means a system provides reduced functionality instead of failing completely.

Examples:

- Show cached read-only data when a recommendation service is unavailable.
- Accept a request and queue work when a slow external service is down.
- Disable a non-critical feature while keeping core task creation available.

## Tradeoffs

Fallbacks can hide real problems if they are not monitored. Retries can make an outage worse if every client retries too aggressively. Graceful degradation still needs clear alerts and recovery steps.

