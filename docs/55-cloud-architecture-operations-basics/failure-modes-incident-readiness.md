# Failure Modes And Incident Readiness

## Failure Mode Thinking

Ask what happens when each component fails:

- Backend instance down.
- Database slow.
- Cache unavailable.
- Queue backlog grows.
- Object storage rejects writes.
- Logs or metrics are delayed.

## Graceful Degradation

Graceful degradation keeps core features available when non-critical features fail. For example, a task API might keep creating tasks while disabling a report export feature.

## Incident Response Notes

Incident notes should explain how to detect, triage, mitigate, and recover from common failures. They should also name what not to do during pressure.

## Common Mistakes

- No owner for alerts.
- No runbook for known failures.
- Retrying without limits.
- Ignoring queue backlog.
- Scaling the backend while the database is saturated.

