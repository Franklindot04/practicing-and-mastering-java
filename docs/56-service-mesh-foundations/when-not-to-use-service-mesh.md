# When Not To Use Service Mesh

A service mesh is powerful, but it is not automatically the next step after Kubernetes or cloud architecture.

## Avoid A Mesh When

- The application has one deployable service.
- Service-to-service traffic is simple and already understandable.
- The team cannot operate the control plane safely.
- Observability basics are missing.
- Timeouts, health checks, logs, and runbooks are not mature.
- The main problem is API design, database performance, or application bugs.
- The team wants a mesh mostly because it sounds advanced.

## Better First Steps

Before adding a mesh, make sure the system has:

- Clear service boundaries.
- Health and readiness checks.
- Useful logs, metrics, and traces.
- Safe deployment and rollback habits.
- Known timeout and retry behavior.
- Incident response notes.

## Cost Of Premature Mesh Adoption

A premature mesh can make debugging harder. Traffic may pass through proxies, policies may override application assumptions, mTLS may fail for identity reasons, and upgrades may require platform-level coordination.

## Rule Of Thumb

Add a service mesh when the communication problems are real, repeated, and worth centralizing. Do not add it just to decorate a simple backend.

