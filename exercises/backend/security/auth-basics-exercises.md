# Authentication And Authorization Exercises

## Exercise 1: Separate Authentication And Authorization

Difficulty: Beginner

Concepts practiced: Authentication, authorization, 401 vs 403

Problem statement: For each scenario, decide whether it is an authentication problem or an authorization problem.

- A request has no credentials.
- A logged-in user tries to open an admin endpoint.
- A login request has the wrong password.
- A user changes an id to read someone else's task.

Hints:

- Authentication asks who the user is.
- Authorization asks what the user may do.

Stretch challenge: Pick a likely HTTP status for each scenario.

## Exercise 2: Design Roles

Difficulty: Beginner

Concepts practiced: Roles, permissions, least privilege

Problem statement: Design `USER` and `ADMIN` roles for a task API. List two actions each role can perform.

Hints:

- Keep roles simple at first.
- Avoid giving admin access when normal user access is enough.

Stretch challenge: Convert one role action into a more specific permission name.

## Exercise 3: Safe User Response

Difficulty: Beginner

Concepts practiced: DTO design, sensitive data

Problem statement: A `UserEntity` has `id`, `email`, `passwordHash`, `role`, and `createdAt`. Design a `UserResponse` DTO that avoids leaking sensitive fields.

Hints:

- Clients do not need password hashes.
- Public response shape can differ from entity shape.

Stretch challenge: Explain whether `createdAt` should be included.
