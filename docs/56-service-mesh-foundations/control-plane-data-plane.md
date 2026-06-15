# Control Plane And Data Plane

Service mesh architecture is often described with two planes.

## Data Plane

The data plane handles actual application traffic. In a sidecar-based mesh, this usually means proxy processes running beside application containers. In sidecarless designs, traffic handling may happen in node-level agents or other infrastructure.

The data plane may:

- Receive outbound requests from the service.
- Receive inbound requests before they reach the service.
- Apply routing, retries, timeouts, and security policy.
- Emit metrics, traces, and access logs.

## Control Plane

The control plane configures and coordinates the data plane. It distributes policies, service discovery information, identity material, and routing rules to proxies or agents.

The control plane may:

- Track services and endpoints.
- Push traffic policy.
- Manage workload identity metadata.
- Coordinate certificates or trust configuration.
- Expose administrative status and diagnostics.

## Why The Split Matters

The split lets application teams keep business code focused while platform infrastructure handles repeated communication concerns. It also means a mesh introduces new moving parts that must be monitored, upgraded, secured, and debugged.

## Common Mistake

Do not assume the control plane is always on the request path. In many designs, application requests flow through data plane proxies while the control plane configures those proxies separately.

