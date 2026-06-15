# Authentication And Authorization

Authentication answers: who are you?

Authorization answers: what are you allowed to do?

Both are needed in secure backend APIs.

## Core Terms

- Identity: the user or system making a request.
- Credential: evidence used to prove identity, such as a password.
- Role: a named group of permissions, such as `USER` or `ADMIN`.
- Permission: a specific allowed action.
- Session: server-side state that remembers an authenticated user.
- Token: client-held data used to represent authentication.

## Study Order

1. [Authentication Vs Authorization](authentication-vs-authorization.md)
2. [Password Hashing, Sessions, And Tokens](password-hashing-sessions-tokens.md)
3. [JWT Basics](jwt-basics.md)
4. [Spring Security Introduction](../27-spring-security-introduction/README.md)

## Beginner Rule

Use proven frameworks and libraries for real authentication. Do not build custom cryptography or custom token systems for production.
