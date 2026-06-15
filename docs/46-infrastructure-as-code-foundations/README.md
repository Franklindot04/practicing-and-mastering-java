# Infrastructure as Code Foundations

Infrastructure as Code, often shortened to IaC, means describing infrastructure in files that can be reviewed, versioned, tested, and reused. Instead of clicking through dashboards to create servers, databases, networks, queues, or deployment settings, a team writes configuration that explains the desired infrastructure.

This section is a beginner introduction. The examples in this repository are educational and local-only. They are not production templates and should not be used to create real cloud resources without deeper review, provider setup, security review, cost controls, and team approval.

## Learning Goals

- Explain why teams use IaC.
- Compare manual infrastructure changes with reviewable infrastructure changes.
- Understand declarative infrastructure and desired state.
- Recognize drift between code and real infrastructure.
- Identify why plans, reviews, and state safety matter.

## Recommended Order

1. Read [IaC overview](iac-overview.md).
2. Read [Declarative infrastructure and drift](declarative-infrastructure-drift.md).
3. Read [Reviewable infrastructure changes](reviewable-infrastructure-changes.md).
4. Continue to [Terraform and OpenTofu foundations](../47-terraform-opentofu-foundations/README.md).

## Why This Comes After Kubernetes And Helm

Kubernetes and Helm teach how Java backend workloads can be packaged and described for a cluster. IaC broadens the picture: it asks how infrastructure around the application could be described, reviewed, changed, and recovered over time.

