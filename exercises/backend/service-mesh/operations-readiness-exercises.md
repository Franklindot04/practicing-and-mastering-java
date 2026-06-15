# Operations Readiness Exercises

## Exercise 1: Debug A Routing Problem

Difficulty: Intermediate

Concepts practiced: routing, canary, telemetry, rollback thinking.

Problem statement: Ten percent of internal requests should reach v2, but all traffic is still reaching v1. Write a conceptual debugging checklist.

Hints:

- Check labels or selectors conceptually.
- Compare mesh telemetry with application logs.
- Look for recent policy changes.

Stretch: Add one rollback decision if v2 suddenly receives too much traffic.

## Exercise 2: Incident Response With Mesh Signals

Difficulty: Intermediate

Concepts practiced: incident response, retries, timeouts, mTLS, operations ownership.

Problem statement: Error rate rises after a mesh policy change. Write the first five investigation steps.

Hints:

- Identify what changed.
- Stabilize user impact first.
- Compare mesh and application signals.

Stretch: Add one post-incident follow-up item.

