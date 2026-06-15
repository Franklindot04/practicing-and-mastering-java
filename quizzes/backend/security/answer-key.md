# Backend Security Quiz Answer Key

## Authentication And Security Basics Quiz

1. B
2. C
3. B
4. A
5. It can help attackers enumerate valid accounts.
6. A role is a group such as `USER` or `ADMIN`; a permission is a specific allowed action such as `task:update`.
7. Authorization applies because the user is known but lacks permission.

## Passwords, Tokens, And JWT Quiz

1. B
2. A
3. B
4. A
5. Benefit: server can revoke session state. Tradeoff: server must store session state and cookie-based browser flows need CSRF awareness.
6. Expiration limits how long stolen or stale tokens remain useful.
7. JWT payloads should not contain secrets, and passwords should not be reused or exposed after login.

## Spring Security Quiz

1. A
2. A
3. A
4. A
5. It loads user details by username for Spring Security authentication.
6. It teaches the request protection flow clearly, but production systems need stronger design, TLS, secret management, monitoring, and often token or federated identity flows.
7. Protected data and actions may become public because all routes are allowed.
