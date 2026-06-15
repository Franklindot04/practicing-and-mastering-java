# Authentication Vs Authorization

Authentication and authorization are related but separate.

## Authentication

Authentication verifies identity.

Examples:

- A user submits username and password.
- An API validates a session cookie.
- A service validates a signed token.

Authentication failure usually returns `401 Unauthorized`.

## Authorization

Authorization checks permission.

Examples:

- Only admins can read `/api/admin`.
- Users can update only their own resources.
- A service account can read reports but cannot delete users.

Authorization failure usually returns `403 Forbidden`.

## Roles And Permissions

Roles group permissions:

- `USER`: manage own tasks.
- `ADMIN`: read demo admin endpoint.

Permissions are more specific:

- `task:read`
- `task:update`
- `admin:read`

Beginner projects often start with roles, then learn finer-grained permissions later.

## Common Mistake

Checking that a user is logged in is not enough. You must also check whether that user is allowed to perform the requested action.
