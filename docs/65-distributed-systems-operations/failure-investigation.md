# Failure Investigation

Distributed failure investigation starts by naming the symptom, then narrowing the failing boundary.

## Investigation Flow

```text
Symptom: users cannot create tasks
        |
        v
Check API errors and latency
        |
        v
Check database dependency
        |
        v
Check recent deploy/config changes
        |
        v
Check retry and timeout behavior
```

## Separate Symptom From Cause

Examples:

- A 500 response is a symptom.
- A database connection pool exhaustion may be a cause.
- Slow notifications are a symptom.
- A retry storm against an unavailable provider may be a cause.

## Evidence To Gather

- Start and end time of the incident.
- Affected endpoints or workflows.
- Error rate and latency changes.
- Dependency health.
- Recent releases or configuration changes.
- Whether retries increased load.
- Whether data repair is needed.

## After The Incident

Write down what happened, what users saw, how the system recovered, and what would make the next investigation easier.

