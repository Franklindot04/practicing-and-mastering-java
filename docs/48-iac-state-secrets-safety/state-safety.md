# State Safety

State is the record an IaC tool uses to connect configuration with infrastructure it knows about. Without state, the tool cannot reliably decide what already exists, what changed, or what should be updated.

## Why State Matters

State can include:

- Resource identifiers.
- Generated names.
- Provider-returned attributes.
- Values copied from variables or resources.
- Data that may be sensitive in a real environment.

Because state can be sensitive, it should not be treated like ordinary source code.

## Local State Vs Remote State

Local state is stored on the developer machine. It is easy for learning, but it can be lost, copied accidentally, or committed by mistake.

Remote state is stored in a shared backend. Real teams often use remote state so approved automation and collaborators have one source of truth. Remote state needs access control, encryption, backup, and state locking.

## State Locking

State locking prevents two operations from changing the same state at the same time. Without locking, two people could apply overlapping changes and corrupt the record of what exists.

This repository does not configure real remote state. Learn the concept here, then study team-specific backend rules before touching production IaC.

## Files To Keep Out Of Git

Do not commit:

- `.tfstate`
- `.tfstate.backup`
- Saved `.tfplan` files
- `.terraform/`
- `.terraform.lock.hcl` when it exposes local provider resolution for demo-only exercises
- Generated local output files

