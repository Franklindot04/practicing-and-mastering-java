# fmt, validate, And plan

IaC tools provide commands that help teams catch problems before changing infrastructure.

## fmt

`fmt` rewrites configuration into a standard style. Formatting makes diffs easier to review.

Example:

```bash
terraform fmt -check -recursive examples/backend/iac-local-only
```

## validate

`validate` checks whether configuration is structurally valid. It can catch syntax mistakes, missing variables, and incorrect references.

Validation is not a security review and does not prove that a real change is safe.

## plan

`plan` previews what would change. A plan may show resources being created, updated, replaced, or deleted.

A plan should be reviewed before any real apply. A surprising plan is a stop sign, not a puzzle to rush through.

## apply And destroy Concepts

`apply` makes changes in a real environment. `destroy` removes managed resources. Both can affect cost, availability, data, and security.

Do not run apply or destroy for the educational examples in this repository.

