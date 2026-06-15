# State, Plan, Apply, And Destroy

IaC tools need to remember what they manage. That memory is called state.

## State

State maps configuration to known infrastructure objects and stored values. State can contain sensitive data, generated identifiers, connection details, or values derived from provider responses.

Never commit `.tfstate` files to this repository.

## Plans

A plan previews what the tool would change. It may show creations, updates, replacements, or deletions. Plans should be reviewed before any real apply.

Never commit saved plan files to this repository.

## Apply

Apply is the action that makes planned changes in a real environment. Applying against a real provider can create resources, change security posture, incur costs, or destroy data if the configuration is wrong.

Do not run `terraform apply`, `tofu apply`, or similar real apply commands for these repo examples.

## Destroy

Destroy removes managed resources. In real environments, destroy can delete important infrastructure and data.

Do not run `terraform destroy` or `tofu destroy` for these repo examples.

## Safe Beginner Practice

For this repository, safe practice means reading files, formatting files, validating only when no credentials or provider downloads are required, and discussing what a plan would mean without applying it.

