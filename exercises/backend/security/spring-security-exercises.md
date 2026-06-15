# Spring Security Exercises

## Exercise 1: Public And Protected Routes

Difficulty: Beginner

Concepts practiced: Security filter chain, route planning

Problem statement: Plan route rules for an API with `/api/health`, `/api/tasks`, and `/api/admin/demo`.

Hints:

- Health can be public.
- Tasks should require login.
- Admin routes should require admin role.

Stretch challenge: Explain what should happen for an unauthenticated request to `/api/tasks`.

## Exercise 2: PasswordEncoder Flow

Difficulty: Beginner

Concepts practiced: PasswordEncoder, BCrypt

Problem statement: Describe how `PasswordEncoder.encode` and `PasswordEncoder.matches` are used during user creation and login.

Hints:

- Store the encoded password.
- Compare raw login input with the stored encoded value.

Stretch challenge: Explain why you should not log either value.

## Exercise 3: Common Mistake Review

Difficulty: Beginner to Intermediate

Concepts practiced: Secure errors, demo users, custom crypto

Problem statement: Review these choices and explain what is wrong:

- Returning `email not found` during login.
- Committing a JWT secret to git.
- Writing a custom password hashing algorithm.
- Letting a client send its own role.

Hints:

- Think about information leaks.
- Think about trust boundaries.

Stretch challenge: Rewrite one choice safely.
