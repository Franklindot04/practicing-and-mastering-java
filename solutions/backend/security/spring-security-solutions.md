# Spring Security Solutions

## Exercise 1

Possible route plan:

- `/api/health`: public.
- `/api/tasks`: authenticated users.
- `/api/admin/demo`: admin role only.

An unauthenticated request to `/api/tasks` should be rejected as unauthenticated, usually `401`.

## Exercise 2

During user creation, encode the raw password and store only the encoded value. During login, use `matches(rawPassword, storedEncodedPassword)` to verify.

Do not log raw passwords or password hashes because logs are often widely accessible and long-lived.

## Exercise 3

- `email not found` can help attackers enumerate accounts.
- A committed JWT secret can let attackers forge tokens.
- Custom password hashing is risky and unnecessary.
- Client-sent roles are untrusted and can be changed by the caller.

Safer login error: `Invalid credentials`.
