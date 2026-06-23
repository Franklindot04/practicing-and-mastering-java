# Service Discovery Example

This example shows how an API might find healthy instances of another service.

```text
Service B instance 1 --registers health--+
Service B instance 2 --registers health--+--> Discovery Registry
Service B instance 3 --unhealthy---------+

Task API asks registry for healthy Service B instances
Task API calls instance 1 or 2
```

## What To Notice

- Registration does not prove long-term health.
- The caller needs timeouts even after discovery succeeds.
- The registry itself is a dependency.
- Stale entries can route traffic to dead instances.

## Review Questions

- How quickly should unhealthy instances be removed?
- What should the caller do if discovery is unavailable?
- Should the caller cache discovered instances?
- How would tracing show the selected instance?

