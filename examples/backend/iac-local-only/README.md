# Local-Only IaC Basics

This example introduces Terraform/OpenTofu-style file structure without creating real cloud resources. It uses placeholder values and Terraform's built-in `terraform_data` resource so learners can inspect variables, module inputs, outputs, and safe naming patterns.

Do not adapt this directly for production. Real infrastructure needs provider credentials, remote state, review gates, cost controls, security review, and environment-specific design.

## Files

- `versions.tf` declares the required Terraform version range.
- `variables.tf` defines beginner-friendly inputs.
- `main.tf` passes inputs into a local module.
- `outputs.tf` exposes safe, non-secret summary values.
- `terraform.tfvars.example` shows placeholder input values.
- `modules/java-backend-runtime/` demonstrates a child module.

## Why No Real Cloud Resources Are Created

This example does not configure AWS, Azure, GCP, Kubernetes, or any deployment platform provider. It does not include provider credentials and it does not create servers, databases, networks, DNS records, or container registries.

The `terraform_data` resource stores demonstration input in state. That still teaches an important lesson: even harmless-looking examples can generate state files, and state files should not be committed.

## Safe Commands

Formatting is safe:

```bash
terraform fmt -check -recursive examples/backend/iac-local-only
```

Validation may require initialization. If you initialize for local validation, remove `.terraform/`, state files, plan files, and provider lock files before committing. Do not run apply or destroy for this repository example.

## What This Prepares You For

After this example, learners should understand how root modules pass values into child modules, how outputs summarize useful values, and why state safety matters before real provider work begins.

