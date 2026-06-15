# Modules Basics

A module is a group of IaC files used together. Every Terraform/OpenTofu working directory is a module. The directory you run commands from is often called the root module.

## Root Modules

A root module represents an environment or stack entry point. It usually declares provider configuration, calls child modules, sets environment-specific values, and exposes outputs needed by the team.

## Child Modules

A child module is called by another module. It packages repeated infrastructure shape behind inputs and outputs.

For example, a Java backend runtime module might accept:

- Application name.
- Runtime port.
- Health check path.
- Environment name.
- Image reference.

It might output:

- Runtime identifier.
- Service name.
- Non-secret connection hints.

## Why Modules Exist

Modules help when several environments or services share the same infrastructure pattern. They reduce copy-paste and make changes easier to review in one place.

## When Not To Create A Module

Do not create a module only because a file is getting long. A module is useful when there is a stable concept with clear inputs and outputs. Early in learning, direct configuration may be clearer.

