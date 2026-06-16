# Service Mesh Traffic And Reliability

Service mesh traffic policy controls how requests move between internal services. Reliability policy controls how the system behaves when those requests are slow, broken, overloaded, or partially failing.

This section is conceptual only. It does not include mesh manifests, cluster setup, provider automation, or production policy files.

## Learning Goals

- Explain traffic splitting and canary release concepts.
- Compare retries, timeouts, and circuit breakers.
- Understand fault injection and outlier detection as testing and protection tools.
- Identify traffic policy mistakes that can make outages worse.

## Recommended Order

1. Read [Traffic splitting and canary](traffic-splitting-canary.md).
2. Read [Retries, timeouts, and circuit breaking](retries-timeouts-circuit-breaking.md).
3. Read [Fault injection and outlier detection](fault-injection-outlier-detection.md).
4. Read [Common traffic policy mistakes](common-traffic-policy-mistakes.md).

