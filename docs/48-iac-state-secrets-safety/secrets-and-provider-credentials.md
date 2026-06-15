# Secrets And Provider Credentials

Variables and secrets are related, but they are not the same thing.

## Variables Are Inputs

Variables make IaC configurable. A variable can hold a port number, environment name, runtime size, or image tag. Some variables may be sensitive, but variable files are not secret managers.

Do not put real passwords, access keys, tokens, private keys, database URLs, or JWT secrets into committed variable files.

## Secrets Need Protected Storage

Real secrets belong in approved secret systems, encrypted CI/CD settings, cloud secret managers, or other organization-approved storage. IaC may reference secrets, but it should not leak them into Git.

Marking a variable as `sensitive` can reduce display in command output. It does not make state safe to commit, and it does not replace access control.

## Provider Credentials

Providers often authenticate through environment variables, local profiles, workload identity, or CI/CD identity. Keep provider credentials out of repository files.

Safe beginner rules:

- Use placeholder values in examples.
- Never commit real credentials.
- Avoid provider-specific automation that requires secrets.
- Use least privilege for any future real IaC identity.
- Keep examples separate from real environments.

## Least Privilege

IaC identities should have only the permissions needed for the approved task. A broad administrator token is easier to set up, but a mistake with that token can be much more damaging.

