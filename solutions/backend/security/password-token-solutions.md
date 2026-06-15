# Password, Session, Token, And JWT Solutions

## Exercise 1

Passwords should be hashed because the application only needs to verify a candidate password, not recover the original. Plaintext exposes users immediately. Encryption is reversible, so key exposure can reveal passwords.

BCrypt is commonly used with Spring Security.

## Exercise 2

Sessions:

- Benefit: server can revoke session state.
- Tradeoff: server must store session state.

Tokens:

- Benefit: common for APIs and mobile apps.
- Tradeoff: stolen tokens can be used until they expire or are revoked.

## Exercise 3

Reasonable JWT claims:

- User id: yes.
- Expiration time: yes.
- Password: no.
- Role: sometimes yes, if verified and kept current enough for the system.
- Private API key: no.

Expiration limits how long a stolen or stale token remains useful.
