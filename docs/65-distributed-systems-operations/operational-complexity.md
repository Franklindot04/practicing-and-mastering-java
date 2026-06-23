# Operational Complexity

Distributed systems increase operational complexity because there are more components, more failure modes, and more ownership boundaries.

## Sources Of Complexity

- More deployable units.
- More network calls.
- More configuration.
- More dependency health checks.
- More logs and metrics.
- More partial failure states.
- More coordination between teams.

## Complexity Budget

Every new service should justify its operational cost.

Ask:

- Who owns it?
- Who is alerted when it fails?
- How is it deployed?
- How is it rolled back?
- How is data repaired?
- How is the request path observed?

## Healthy Operations

Healthy distributed operations include:

- Clear service ownership.
- Small, understandable failure domains.
- Documented runbooks.
- Safe defaults for timeouts and retries.
- Dashboards that match user workflows.
- Post-incident learning.

