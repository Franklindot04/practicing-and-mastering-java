# Deployment And Cloud Readiness Quiz Answer Key

## Deployment Basics

1. B
2. A
3. B
4. Runtime configuration changes by environment and should not require code changes or committed secrets.
5. Examples include `/actuator/health`, `/api/info`, `/api/version`, and `/api/smoke`.
6. The team cannot easily identify what is running, verify the intended version, or recover quickly if the release fails.

## Cloud Readiness

1. B
2. B
3. A
4. Virtual machines, containers, and managed app platforms are valid examples.
5. Cloud keys can grant access to real resources and money-impacting operations, so exposing them can cause security and cost incidents.
6. Stop unused resources, review cost/quota limits, and reduce unnecessary log volume.

## Runbooks And Rollback

1. A
2. B
3. B
4. Failed health checks, failed smoke tests, high error rate, wrong version, or broken critical endpoint.
5. Environment, release version, failed endpoint, status code, time, safe logs, and owner/decision.
6. It needs an owner, exact steps, validation checks, rollback trigger, communication path, and safe configuration notes.
