# Common IaC Safety Mistakes

IaC mistakes can be more serious than ordinary documentation mistakes because the files may eventually drive real infrastructure changes.

## State Mistakes

- Committing `.tfstate` files.
- Sharing state through chat or email.
- Letting multiple people modify state without locking.
- Treating state as harmless because the code looks simple.

## Secret Mistakes

- Putting credentials in `terraform.tfvars`.
- Using real database URLs in examples.
- Printing sensitive outputs for convenience.
- Assuming `sensitive = true` fully protects a value.
- Reusing production credentials in a learning project.

## Environment Mistakes

- Mixing demo configuration with real environments.
- Using production-like names in local examples.
- Running commands from the wrong directory.
- Applying before reviewing the plan.

## Safer Habits

- Keep examples local-only.
- Use placeholder values.
- Review every plan before real applies.
- Prefer least-privilege identities.
- Keep state and credentials out of Git.

