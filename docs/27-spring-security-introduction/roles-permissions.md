# Roles And Permissions

Authorization decides what an authenticated user may do.

## Role-Based Authorization

Roles are broad groups of permissions.

Example roles:

- `USER`: can manage tasks.
- `ADMIN`: can access a demo admin endpoint.

Spring Security often represents roles as authorities such as `ROLE_USER` and `ROLE_ADMIN`.

## Endpoint Rules

Example idea:

```java
requestMatchers("/api/health").permitAll()
requestMatchers("/api/admin/**").hasRole("ADMIN")
anyRequest().authenticated()
```

This means health is public, admin routes require admin, and everything else requires login.

## Method Rules

With `@EnableMethodSecurity`, methods can use:

```java
@PreAuthorize("hasRole('ADMIN')")
```

Method-level rules can protect behavior even if a route changes later.

## Beginner Guidance

Start with simple roles. Add finer-grained permissions only when the application needs them.

Always test protected routes with users that should and should not have access.
