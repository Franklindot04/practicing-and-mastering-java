# Backend Projects

Backend projects turn Java design skills into HTTP APIs and service applications.

## Projects

- [Simple Spring Boot REST API](simple-rest-api/README.md): an in-memory task API with controller, service, repository, DTOs, validation, and error responses.
- [Persistent Spring Boot Task API](persistent-task-api/README.md): a task API backed by Spring Data JPA and an H2 in-memory database.
- [Secured Spring Boot Task API](secured-task-api/README.md): an educational protected task API skeleton with Spring Security, demo users, roles, validation, and layered service logic.
- [Production-Ready Task API Skeleton](production-ready-task-api/README.md): an educational production-readiness skeleton with profiles, logging, Actuator health, safe errors, security, and persistence.
- [Deployment-Ready Task API Skeleton](deployment-ready-task-api/README.md): an educational deployment-readiness skeleton with runtime metadata, deployment-demo profile, smoke-test endpoint, health, security, and persistence.
- [Kubernetes-Ready Task API Notes](kubernetes-ready-task-api/README.md): educational Kubernetes readiness notes and local-only manifests for the deployment-ready API concept.
- [Helm-Ready Task API Notes](helm-ready-task-api/README.md): educational Helm packaging notes and a local-only chart for the deployment-ready/Kubernetes-ready API concept.
- [IaC-Ready Task API Notes](iac-ready-task-api/README.md): educational infrastructure planning notes and pseudo-HCL for describing backend runtime, database, network, state, and secrets concerns safely.
- [Cloud Architecture-Ready Task API Notes](cloud-architecture-ready-task-api/README.md): educational architecture planning notes for task API traffic flow, reliability, scaling, cost, and failure modes.
- [Service Mesh-Ready Task API Notes](service-mesh-ready-task-api/README.md): educational service mesh readiness notes for service boundaries, traffic policy, observability, mTLS identity, operations, and when not to add a mesh.

## Suggested Order

1. Review [Backend Java Foundations](../../docs/19-backend-java-foundations/README.md).
2. Study [HTTP REST API Basics](../../docs/20-http-rest-api-basics/README.md).
3. Study [Spring Boot Introduction](../../docs/21-spring-boot-introduction/README.md).
4. Run backend examples before starting the project.
5. Build the simple REST API and explain each layer.
6. Study SQL and persistence foundations.
7. Build the persistent task API and explain entity, repository, service, and DTO boundaries.
8. Study backend security, authentication, authorization, and Spring Security fundamentals.
9. Build the secured task API and explain which routes are public, which routes are protected, and which checks belong in configuration or service logic.
10. Study production readiness, external configuration, profiles, logging, health checks, Docker basics, and CI/CD fundamentals.
11. Build the production-readiness skeleton and explain what is still simplified for learning.
12. Study deployment lifecycle, release/rollback basics, runbooks, platform concepts, and cloud readiness.
13. Build the deployment-ready skeleton and explain how health, version, runtime config, and smoke-test endpoints support deployment checks.
14. Study Kubernetes and container orchestration foundations.
15. Review the Kubernetes-ready notes and explain how manifests, Services, probes, resources, and rollback notes relate to the deployment-ready API.
16. Study Helm packaging and chart values.
17. Review the Helm-ready notes and explain how values render Deployments, Services, ConfigMaps, Secret examples, probes, and resources.
18. Study Infrastructure as Code foundations, Terraform/OpenTofu basics, state and secrets safety, modules, environments, and operations basics.
19. Review the IaC-ready notes and explain how runtime, database, network, state, and secrets planning relate to the deployment-ready, Kubernetes-ready, and Helm-ready API concepts.
20. Study cloud architecture foundations, availability, reliability, traffic/data patterns, cost-aware review, and operations basics.
21. Review the cloud architecture-ready notes and explain task API traffic flow, stateful dependencies, scaling risks, failure modes, and cost tradeoffs.
22. Study service mesh foundations, sidecar communication, traffic policy, observability, security boundaries, and operations basics.
23. Review the service mesh-ready notes and explain whether mesh adoption solves real task API communication problems or adds premature complexity.

Do not add real cloud resources, production database configuration, production Kubernetes automation, Terraform/OpenTofu apply or destroy workflows, real service mesh installation, distributed systems implementation, kubeconfig files, Helm release state, IaC state files, plan files, mesh certificates, provider credentials, real domains, account IDs, or real secrets in these learning projects. Demo credentials, placeholder manifests, local-only charts, pseudo-HCL, and vendor-neutral diagrams are not production credentials or production infrastructure.
