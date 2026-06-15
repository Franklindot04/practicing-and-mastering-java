# Runbook And Rollback Solutions

## Exercise 1

Possible smoke tests:

- `GET /actuator/health`
- `GET /api/info`
- `GET /api/version`
- `GET /api/smoke`

If the version endpoint is wrong, pause and verify whether the intended artifact was deployed.

## Exercise 2

Rollback or pause triggers can include failed health checks, failed smoke tests, a sharp error-rate increase, critical endpoint failure, or wrong runtime configuration. Database changes require extra caution because code rollback may not reverse schema changes.

## Exercise 3

Good release notes include version, commit, environment, owner, summary, validation performed, runtime configuration notes, database notes, rollback plan, and incident handoff contact or channel. Do not paste secrets into release notes.
