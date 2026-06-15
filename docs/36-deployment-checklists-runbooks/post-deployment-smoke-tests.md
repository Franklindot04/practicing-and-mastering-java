# Post-Deployment Smoke Tests

Smoke tests confirm that a deployment is basically usable.

## Post-Deployment Checklist

- [ ] Application process started.
- [ ] Health endpoint returns success.
- [ ] Version endpoint shows the expected release.
- [ ] Public info endpoint works.
- [ ] One protected endpoint works in the target learning environment.
- [ ] Logs do not show repeated startup errors.
- [ ] No secrets appear in logs or responses.

## Smoke Test Checklist

- [ ] `GET /actuator/health`
- [ ] `GET /api/info`
- [ ] `GET /api/version`
- [ ] `GET /api/smoke`
- [ ] One safe create/read flow if the environment allows test data.

## Incident Handoff Basics

If smoke tests fail, capture:

- Release version.
- Environment.
- Failed endpoint.
- Status code.
- Time of failure.
- Relevant safe log lines.
- Decision: fix forward, pause, or roll back.
