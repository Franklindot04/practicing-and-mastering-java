# Authentication And Authorization Solutions

## Exercise 1

- No credentials: authentication problem, likely `401`.
- Logged-in user opens admin endpoint: authorization problem, likely `403`.
- Wrong password: authentication problem, likely `401`.
- User changes an id to read someone else's task: authorization problem, likely `403` or `404` depending on API design.

## Exercise 2

Possible roles:

- `USER`: create own tasks, update own tasks.
- `ADMIN`: access admin demo endpoint, review system-level task data.

Specific permissions could be `task:create`, `task:update-own`, or `admin:read`.

## Exercise 3

Possible DTO:

```java
record UserResponse(long id, String email, String role) {}
```

Do not include `passwordHash`. `createdAt` can be included if clients need it, but it is not required for every response.
