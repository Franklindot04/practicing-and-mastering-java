# Spring Security Core Concepts

Spring Security protects requests before they reach most controller logic.

## Security Filter Chain

`SecurityFilterChain` defines how HTTP requests are secured.

It can configure:

- Public routes
- Protected routes
- Login/authentication mechanism
- CSRF settings
- HTTP Basic for simple API demos
- Authorization rules

## Authentication Provider

An authentication provider checks credentials and creates an authenticated identity when the credentials are valid.

For beginners, this often means checking a username and password through Spring Security infrastructure.

## UserDetails And UserDetailsService

`UserDetails` represents a user known to Spring Security.

`UserDetailsService` loads a user by username.

In real applications, this often queries a database. In early demos, in-memory users can teach the flow.

## Public Vs Protected Routes

Public route examples:

- `/api/health`
- `/api/info`

Protected route examples:

- `/api/tasks`
- `/api/admin/demo`

Only make routes public when there is a clear reason.

## Method-Level Authorization

`@EnableMethodSecurity` enables method security annotations.

`@PreAuthorize("hasRole('ADMIN')")` can protect a controller or service method.

This is useful when endpoint-level rules are not enough.
