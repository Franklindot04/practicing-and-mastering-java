# Operations Readiness Solutions

## Exercise 1: Debug A Routing Problem

A useful checklist is to confirm the intended policy was reviewed, verify conceptual labels or version selectors, check whether v2 is healthy, compare mesh telemetry with application logs, inspect recent deployment or policy changes, and pause rollout until the route is understood.

If v2 receives too much traffic, shift traffic back to v1 or roll back the policy change while preserving logs for review.

## Exercise 2: Incident Response With Mesh Signals

First steps: identify the policy change, stop further rollout, compare error rates by source and destination, check retry and timeout volume, verify whether mTLS or authorization policy is rejecting calls, and compare application logs with mesh telemetry.

A post-incident follow-up could add a policy review checklist, clearer dashboards, or a rollback drill.

