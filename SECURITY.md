# Security Policy

## Reporting Security Issues

If you find a security concern in this repository, please do not publish sensitive details in a public issue. Contact the maintainer privately when possible, or open a minimal issue that says a security review is needed without including exploit details.

## Learning Repository Scope

This is primarily a Java learning repository. Some examples may intentionally simplify production security concerns so learners can focus on one concept at a time.

## Vulnerable Demo Code

If vulnerable code is included for educational purposes, it must be clearly marked as vulnerable and must explain why it is unsafe.

## Secrets Policy

Never commit secrets, credentials, tokens, passwords, API keys, private keys, or real production configuration.

Example environment files must use placeholders only, such as:

```text
API_KEY=replace-with-your-own-value
DB_PASSWORD=replace-with-your-own-password
```

## Dependencies

Review dependencies before adding them. Prefer well-maintained libraries, pin versions intentionally, and remove dependencies that are not needed.
