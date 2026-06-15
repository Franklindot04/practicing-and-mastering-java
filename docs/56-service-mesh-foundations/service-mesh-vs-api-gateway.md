# Service Mesh Vs API Gateway

API gateways and service meshes both influence network traffic, but they usually solve different problems.

## API Gateway

An API gateway is commonly placed at the edge of a system. It handles north-south traffic: clients entering the backend platform.

Gateway responsibilities often include:

- Public route exposure.
- Request authentication at the edge.
- Rate limiting for external clients.
- Request shaping for public APIs.
- TLS termination for public entry points.

## Service Mesh

A service mesh usually focuses on east-west traffic: services calling other services inside the platform.

Mesh responsibilities often include:

- Internal service identity.
- Mutual TLS between workloads.
- Internal traffic splitting.
- Service-to-service telemetry.
- Retries, timeouts, and circuit breaking between services.

## How They Work Together

A gateway can route external traffic into the platform, then the mesh can govern communication between internal services. They can overlap in some products, but the design question should start with traffic direction and ownership.

## Design Question

Ask: is this concern about public API entry, or about internal service-to-service behavior? That answer usually points to gateway, mesh, application code, or ordinary platform configuration.

