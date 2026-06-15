# Password Hashing, Sessions, And Tokens

Passwords and login state must be handled carefully.

## Hashing Vs Encryption

Encryption is reversible with a key.

Hashing is one-way. Password storage should use a slow, salted password hashing algorithm, not reversible encryption.

## Password Hashing

A password hash stores a one-way result, not the password itself. A salt helps protect against precomputed attacks.

BCrypt is a common password hashing algorithm supported by Spring Security.

Never store plaintext passwords.

## Sessions

A session stores login state on the server. The client usually receives a session cookie that identifies the session.

Session benefits:

- Server can revoke session state.
- Works well for browser apps.

Session tradeoffs:

- Requires server-side session storage.
- CSRF protections matter for cookie-based browser requests.

## Tokens

A token is client-held authentication data sent with requests, often in an `Authorization` header.

Token benefits:

- Common for APIs and mobile clients.
- Can avoid server-side session storage.

Token tradeoffs:

- Revocation can be harder.
- Token theft can be serious.
- Secrets and signing keys must be protected.

## Access Tokens And Refresh Tokens

Access tokens are short-lived and used to call APIs.

Refresh tokens are longer-lived and used to get new access tokens. They need stronger protection.

Do not implement refresh-token systems until the basic authentication flow is understood.
