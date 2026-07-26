# Operational Ownership And Incidents

A system without owners is fragile even if the architecture looks elegant.

## Operational Ownership

Ownership answers:

- Who receives alerts?
- Who can make a rollback decision?
- Who owns runbook updates?
- Who reviews recurring incidents?
- Who understands the data recovery path?

## Runbooks

A runbook is a practical guide for handling known operational situations.

Include:

- Symptoms.
- First checks.
- Dashboards or logs to inspect.
- Safe mitigations.
- Escalation path.
- Rollback or recovery limits.

Runbooks should be tested and updated after incidents.

## Error Budgets

An error budget is the amount of unreliability a service can tolerate while still meeting its objective.

If the budget is being burned quickly, teams may pause risky changes and focus on reliability work.

## Incident Learning

Incident reviews should ask:

- What surprised us?
- Which signal was missing?
- Which assumption was wrong?
- Which manual step can be simplified?
- Which alert was noisy?
- Which design decision needs revisiting?

Blame does not improve architecture. Learning does.

## Failure Scenarios

| Failure | Useful Design Response |
| --- | --- |
| Database saturation | Backpressure, query review, connection pool limits |
| Queue backlog | Consumer scaling, poison message handling, lag metrics |
| Bad deployment | Rollback plan, canary, feature flag |
| Dependency timeout | Timeout, fallback, circuit breaker, clear user response |
| Cost spike | Usage dashboards, limits, ownership review |
