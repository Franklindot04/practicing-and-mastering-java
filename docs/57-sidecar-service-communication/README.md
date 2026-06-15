# Sidecar Service Communication

Sidecar communication is the classic beginner model for understanding service mesh behavior. A proxy runs beside each service instance and handles inbound and outbound traffic for that service.

This section explains the concept without adding Kubernetes manifests, mesh resources, certificates, or cluster setup.

## Learning Goals

- Explain the sidecar proxy pattern.
- Trace service-to-service communication through local proxies.
- Understand how mutual TLS and service identity fit into a mesh.
- Identify the application responsibilities that remain outside the proxy.

## Recommended Order

1. Read [Sidecar proxy concept](sidecar-proxy-concept.md).
2. Read [Service-to-service communication](service-to-service-communication.md).
3. Read [mTLS and service identity](mtls-and-service-identity.md).
4. Continue to service mesh traffic and reliability topics.

