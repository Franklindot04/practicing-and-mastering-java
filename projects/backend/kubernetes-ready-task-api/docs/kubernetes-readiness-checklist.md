# Kubernetes Readiness Checklist

Use this checklist before treating a Java backend as Kubernetes-ready.

- [ ] The application can be configured through environment variables.
- [ ] Health endpoints exist and do not require authentication for probe paths.
- [ ] Readiness, liveness, and startup probe behavior is understood.
- [ ] Logs go to standard output instead of local files.
- [ ] The container image does not contain real secrets.
- [ ] The image tag identifies a specific build for release tracking.
- [ ] Memory settings are reviewed against the container memory limit.
- [ ] Configuration and Secret values are separated.
- [ ] Service selectors match Deployment Pod labels.
- [ ] Rollback and smoke-test steps are written down.

This checklist is a learning aid, not a production certification.
