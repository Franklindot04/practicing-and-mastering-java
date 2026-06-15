# Secure Validation And Error Handling

Validation and error handling are security features as well as usability features.

## Validate Before Trusting

Validate request data before authentication workflows, persistence, or business logic uses it.

Check:

- Required fields
- String length
- Allowed values
- Numeric ranges
- Email or username shape
- Object ownership when ids are involved

## Sanitize Carefully

Sanitization means cleaning or transforming data for a specific context. Validation decides whether data is acceptable. Do not treat sanitization as a substitute for validation.

## Secure Error Responses

Good API errors are useful but not revealing.

Prefer:

```json
{
  "status": 401,
  "message": "Invalid credentials"
}
```

Avoid:

```json
{
  "message": "Password for user alice@example.com did not match hash in users.password_hash"
}
```

## Login Errors

For login, avoid saying whether the username or password was wrong. A generic message makes account enumeration harder.

## Internal Errors

Do not return stack traces, SQL details, server paths, secrets, token claims, or framework internals to clients.

## Least Privilege

The principle of least privilege means users, services, and database accounts should have only the permissions they need.

Before moving on, you should be able to explain how validation, safe errors, and least privilege reduce risk.
