# Core Concepts

Terraform and OpenTofu use configuration files, usually with the `.tf` extension, to describe desired infrastructure.

## Terraform Vs OpenTofu

Terraform is a widely used IaC tool originally created by HashiCorp. OpenTofu is an open-source fork in the same ecosystem. Their basic beginner concepts are similar: configuration, providers, resources, variables, outputs, state, plans, and applies.

Teams choose between tools based on licensing, ecosystem, compatibility, governance, provider support, and organizational standards. Beginners should focus first on the shared mental model.

## Configuration Files

Common files include:

- `versions.tf` for required tool and provider versions.
- `main.tf` for the main resource definitions.
- `variables.tf` for configurable inputs.
- `outputs.tf` for values the configuration exposes after evaluation.
- `terraform.tfvars.example` for placeholder example values.

File names are conventions. The tool loads `.tf` files in the working directory together.

## Educational Scope

The IaC examples in this repo avoid real cloud providers. They are meant to teach structure and vocabulary before learners use real accounts, real credentials, or real cost-bearing resources.

