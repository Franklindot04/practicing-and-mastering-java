# Helm Readiness Checklist

- [ ] The raw Kubernetes objects are understood before templating them.
- [ ] The chart renders with safe local placeholder values.
- [ ] Real secrets are not stored in chart files or values files.
- [ ] Image repository and tag values are placeholders in this repo.
- [ ] Service selectors match Deployment labels.
- [ ] Probe paths match application health endpoints.
- [ ] Resource requests and limits are visible for review.
- [ ] Override files are clearly marked by environment.
- [ ] Rendered manifests are inspected before any cluster use.

This checklist is educational and not a production approval process.
