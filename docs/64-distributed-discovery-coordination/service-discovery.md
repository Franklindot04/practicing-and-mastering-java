# Service Discovery

Service discovery is how one component finds another component it needs to call.

In small systems, a service might use a fixed URL. In dynamic systems, instances can be added, removed, replaced, or moved, so callers need a way to discover current healthy endpoints.

## Basic Flow

```text
Service B instance starts
        |
        v
Registers address and health
        |
        v
Service A asks where Service B is
        |
        v
Service A calls a healthy instance
```

## Client-Side And Server-Side Discovery

Client-side discovery means the caller chooses a target from discovered instances.

Server-side discovery means the caller sends traffic to a stable entry point, such as a load balancer, which chooses the target.

Both approaches need health signals and stale-entry handling.

## Common Mistakes

- Treating registration as proof that an instance is healthy.
- Keeping stale addresses too long.
- Forgetting timeouts around discovery calls.
- Assuming every failure is a discovery problem.
- Hiding ownership boundaries behind automatic routing.

## Design Questions

- How does an instance join?
- How does an unhealthy instance leave?
- How quickly should callers react to changes?
- What happens if discovery itself is unavailable?
- How will calls be traced after routing decisions?

