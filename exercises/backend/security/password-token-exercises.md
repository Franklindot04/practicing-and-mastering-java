# Password, Session, Token, And JWT Exercises

## Exercise 1: Hashing Vs Encryption

Difficulty: Beginner

Concepts practiced: Password hashing, encryption, plaintext risk

Problem statement: Explain why passwords should be hashed instead of encrypted or stored as plaintext.

Hints:

- Encryption is reversible.
- Password verification does not need the original password.

Stretch challenge: Name a password hashing algorithm commonly used with Spring Security.

## Exercise 2: Session Or Token

Difficulty: Beginner

Concepts practiced: Sessions, tokens, tradeoffs

Problem statement: Compare server-side sessions and API tokens for a mobile app. List one benefit and one tradeoff for each.

Hints:

- Think about revocation.
- Think about what the client stores.

Stretch challenge: Explain why stolen tokens are dangerous.

## Exercise 3: JWT Claim Review

Difficulty: Beginner

Concepts practiced: JWT claims, sensitive data

Problem statement: Decide whether each claim belongs in a JWT payload: user id, expiration time, password, role, private API key.

Hints:

- JWT payloads are encoded, not automatically encrypted.
- Secrets do not belong in JWT claims.

Stretch challenge: Explain why expiration matters.
