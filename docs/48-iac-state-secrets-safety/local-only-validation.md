# Local-Only Validation

Local validation checks file structure and basic syntax before any real infrastructure work. It is useful, but it is not the same as proving production safety.

## Safe Checks

For this repository, safe checks include:

```bash
terraform fmt -check -recursive examples/backend/iac-local-only
```

Validation may be safe when it does not require provider downloads, credentials, remote backends, or real infrastructure access. If a command tries to initialize real providers or contact cloud APIs, stop and review.

## Commands Not To Run Here

Do not run these commands against repo examples:

```bash
terraform apply
terraform destroy
tofu apply
tofu destroy
```

Apply and destroy are real environment operations. They can create resources, change network exposure, incur costs, or remove data.

## Clean Up Generated Files

After any local tooling, check for generated files before committing:

```bash
find . -name ".terraform" -type d
find . -name "*.tfstate*"
find . -name "*.tfplan"
```

Remove generated local artifacts and commit only intentional learning files.

