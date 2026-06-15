# Service Mesh Foundations

A service mesh is infrastructure that manages service-to-service communication inside a distributed application. It usually focuses on traffic policy, service identity, mutual TLS, telemetry, and reliability behavior between services.

This section is vendor-neutral and educational. It does not install Istio, Linkerd, Consul, Kuma, or any other mesh, and it does not provide production-ready mesh configuration.

## Learning Goals

- Explain what a service mesh is.
- Separate mesh responsibilities from application code, Kubernetes, and API gateways.
- Describe control plane and data plane responsibilities.
- Recognize when a service mesh is useful and when it is premature.
- Keep secrets, certificates, kubeconfig files, and provider credentials out of learning material.

## Recommended Order

1. Read [Service mesh overview](service-mesh-overview.md).
2. Read [Control plane and data plane](control-plane-data-plane.md).
3. Read [Service mesh vs API gateway](service-mesh-vs-api-gateway.md).
4. Read [When not to use service mesh](when-not-to-use-service-mesh.md).
5. Continue to [Sidecar service communication](../57-sidecar-service-communication/README.md).

