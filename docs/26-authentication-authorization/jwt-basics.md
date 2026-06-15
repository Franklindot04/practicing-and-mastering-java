# JWT Basics

JWT means JSON Web Token. It is a compact token format often used to carry signed claims.

## Shape

A JWT has three parts:

```text
header.payload.signature
```

- Header: token metadata.
- Payload: claims such as subject, roles, and expiration.
- Signature: proves the token was signed by someone with the secret or private key.

## Common Claims

- `sub`: subject, often the user id or username.
- `exp`: expiration time.
- `iat`: issued-at time.
- roles or authorities: what the user may do.

## Important Warnings

- A JWT payload is encoded, not automatically encrypted.
- Do not put passwords, secrets, or private data in JWT claims.
- Always verify the signature and expiration.
- Never commit real JWT secrets.
- Avoid custom token code in production.

## When To Learn JWT

JWTs are useful, but beginners should first understand authentication, authorization, password hashing, sessions, and token tradeoffs.

The first secured project in this repository may use HTTP Basic for clarity and explain JWT as a future enhancement.
