# IaC-Ready Task API Notes

This folder explains how the backend task API learning path can prepare for Infrastructure as Code without creating real infrastructure.

It references the earlier backend stages conceptually:

- `projects/backend/deployment-ready-task-api/` for runtime configuration and deployment readiness.
- `projects/backend/kubernetes-ready-task-api/` for Kubernetes-oriented planning.
- `projects/backend/helm-ready-task-api/` for chart and values design.

This is not a Spring Boot application and does not duplicate the API source code. It is a planning folder for learners who are ready to describe infrastructure boundaries safely.

## Contents

- [IaC readiness checklist](docs/iac-readiness-checklist.md)
- [Runtime infrastructure needs](docs/runtime-infrastructure-needs.md)
- [Database and network planning](docs/database-and-network-planning.md)
- [State and secrets safety](docs/state-and-secrets-safety.md)
- [Pseudo main configuration](examples/pseudo-main.tf)
- [Pseudo variables](examples/pseudo-variables.tf)
- [Pseudo outputs](examples/pseudo-outputs.tf)

The pseudo-HCL uses placeholder values only. Do not run apply or destroy from this folder.

