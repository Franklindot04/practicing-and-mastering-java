# Cloud Readiness Foundations

Cloud readiness means an application is prepared to run on shared infrastructure managed by a platform or provider.

This repository does not create real cloud resources. These notes are vendor-neutral and focus on concepts Java backend developers should understand before using any provider.

## Application Readiness Versus Infrastructure Readiness

Application readiness asks:

- Can the app read runtime configuration?
- Can it start with environment-specific settings?
- Does it expose safe health checks?
- Does it avoid committed secrets?
- Can it log useful information?

Infrastructure readiness asks:

- Where will the app run?
- How will traffic reach it?
- Where will data live?
- How are secrets stored?
- How are logs, metrics, cost, and access controlled?

## Cloud Readiness Checklist

- [ ] The app builds a known artifact.
- [ ] Runtime configuration is externalized.
- [ ] No real secrets are committed.
- [ ] Health and smoke-test endpoints are available.
- [ ] Logs avoid sensitive data.
- [ ] Database configuration can be supplied safely.
- [ ] HTTPS/domain needs are understood.
- [ ] Cost and quota risks are considered.

## What Not To Worry About Yet

Do not start with Kubernetes, infrastructure as code, service meshes, or advanced cloud networking. Learn deployment and cloud basics first.

## Files In This Section

- [Cloud Concepts For Java Apps](cloud-concepts-for-java-apps.md)
- [Managed Services And Databases](managed-services-and-databases.md)
- [Networking, Domains, And HTTPS](networking-domains-https.md)
- [Scaling And Cost Readiness](scaling-cost-readiness.md)
