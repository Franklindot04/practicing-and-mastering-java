# Service Mesh-Ready Task API Notes

These notes describe how the existing task API learning path could be assessed before introducing service mesh concepts. They build conceptually on the simple REST API, persistent API, secured API, production-ready API, deployment-ready API, Kubernetes-ready notes, Helm-ready notes, IaC-ready notes, and cloud architecture-ready notes.

This is not a service mesh implementation. It contains no mesh manifests, certificates, private keys, provider automation, real domains, account IDs, kubeconfig files, or production policy.

## Study Order

1. Read [Service mesh readiness checklist](docs/service-mesh-readiness-checklist.md).
2. Read [Service boundaries and communication](docs/service-boundaries-and-communication.md).
3. Read [Traffic policy planning](docs/traffic-policy-planning.md).
4. Read [Observability and tracing planning](docs/observability-and-tracing-planning.md).
5. Read [mTLS and identity planning](docs/mtls-and-identity-planning.md).
6. Read [When not to add service mesh](docs/when-not-to-add-service-mesh.md).
7. Review [Task API service mesh flow](diagrams/task-api-service-mesh-flow.md).
8. Review [Task API canary flow](diagrams/task-api-canary-flow.md).

## Goal

The goal is to decide whether mesh adoption would solve real communication problems or merely add platform complexity to a learning API.

