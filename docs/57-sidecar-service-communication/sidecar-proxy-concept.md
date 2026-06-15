# Sidecar Proxy Concept

A sidecar proxy is a helper process deployed beside an application service. The application keeps running its own code, while the proxy handles communication concerns around it.

## What The Proxy Does

The proxy can:

- Intercept outbound calls from the application.
- Receive inbound calls before forwarding them to the application.
- Apply routing policy.
- Add telemetry.
- Enforce mutual TLS policy.
- Retry or fail requests according to configuration.

## What The Application Still Owns

The application still owns business logic, validation, authorization decisions inside the domain, database transactions, error semantics, and API contracts. The proxy does not know whether a task title is valid or whether a user may complete a task.

## Why It Helps

Without sidecars, every service may implement communication behavior differently. With sidecars, common behavior can be configured consistently by platform teams, while application teams keep code focused on the domain.

## Tradeoff

The sidecar model adds another process per service instance. That means extra CPU, memory, logs, configuration, versioning, and failure modes to understand.

