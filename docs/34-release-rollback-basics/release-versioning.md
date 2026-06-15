# Release Versioning

Release versioning identifies what is running.

Useful identifiers:

- Semantic version, such as `1.2.0`.
- Git commit hash.
- Build number.
- Release date.
- Environment name.

## Why Versioning Matters

When an issue appears after deployment, you need to know exactly what changed.

Version information can appear in:

- Release notes.
- Build logs.
- Deployment records.
- Safe `/api/info` response.

Do not expose sensitive build environment data.

## Common Mistakes

- Deploying a version without recording the commit.
- Reusing the same version label for different builds.
- Depending on local file names instead of a clear release identifier.
- Putting secrets into release notes.
