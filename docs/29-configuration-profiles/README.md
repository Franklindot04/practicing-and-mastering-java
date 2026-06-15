# Configuration And Profiles

Configuration is the set of values that changes between environments. Examples include server ports, database URLs, feature flags, logging levels, and credentials.

Good configuration design lets the same application code run locally, in tests, in staging, and eventually in production-like environments.

## Key Ideas

- Keep code and configuration separate.
- Commit only safe defaults and learning values.
- Provide secrets from outside the repository.
- Use profiles to group environment-specific settings.
- Document every required value.
- Make missing critical configuration fail clearly.

## Safe To Commit

- Local H2 database URLs for learning.
- Demo usernames marked as demo-only.
- Non-secret feature flags.
- Local logging levels.
- Test profile settings.

## Not Safe To Commit

- Real database passwords.
- Real API keys.
- Real JWT signing secrets.
- Private keys.
- Production connection strings.
- `.env` files containing secrets.

## Files In This Section

- [External Configuration](external-configuration.md)
- [Spring Profiles](spring-profiles.md)
- [Secrets And Environment Variables](secrets-and-environment-variables.md)
