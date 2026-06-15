# Service Mesh Design Examples

These examples are vendor-neutral design notes for learning service mesh concepts. They are not Istio, Linkerd, Consul, Kuma, Kubernetes, Helm, Terraform, or cloud provider configuration.

Use them to practice tracing communication paths before writing real platform configuration.

## Examples

- [Simple service-to-service flow](simple-service-to-service-flow.md)
- [Sidecar proxy flow](sidecar-proxy-flow.md)
- [Canary traffic splitting flow](canary-traffic-splitting-flow.md)
- [Retry, timeout, and circuit breaker flow](retry-timeout-circuit-breaker-flow.md)
- [mTLS service identity flow](mtls-service-identity-flow.md)
- [Observability tracing flow](observability-tracing-flow.md)
- [API gateway vs service mesh flow](api-gateway-vs-service-mesh-flow.md)

## Safety Notes

Do not turn these files into real mesh manifests. Do not add certificates, private keys, kubeconfig files, real service account tokens, cluster names, provider account IDs, domains, or production policies.

