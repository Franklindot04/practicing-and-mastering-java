# Failure Analysis

1. FA-01: Checkout reserves inventory, payment times out, and the retry arrives with the same key. What should happen?
2. FA-02: A worker leases a task and crashes before completion. What state transitions are safe?
3. FA-03: A consumer receives a poison event repeatedly. How should the system prevent repeated harm?
4. FA-04: A Kubernetes rollout causes rising error rates. What evidence do you collect before rollback?
5. FA-05: A database migration succeeds locally but fails in deployment. What assumptions should be reviewed?
6. FA-06: p95 latency regresses but average latency is stable. What does that suggest?
7. FA-07: A service dependency is degraded but optional. How should the core workflow behave?
8. FA-08: A release note claims a tag exists, but no tag was created. What should be corrected?
