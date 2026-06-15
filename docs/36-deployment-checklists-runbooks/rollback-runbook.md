# Rollback Runbook

Rollback returns an environment to a previous known-good version or configuration.

## Trigger Examples

- Health checks fail.
- Smoke tests fail.
- Error rate rises after release.
- Critical endpoint is unavailable.
- Required runtime configuration is wrong.

## Steps

1. Confirm the problem and affected environment.
2. Stop further rollout.
3. Notify the release owner.
4. Identify the previous known-good version.
5. Restore the previous artifact or runtime configuration.
6. Run health checks and smoke tests.
7. Watch logs.
8. Record what happened.

## Database Warning

Database migrations can make rollback hard. If schema changes are not backward compatible, rolling back code may not be enough.

This stage does not add real migration tooling. It teaches the caution.
