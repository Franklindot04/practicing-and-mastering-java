# Security And Authentication Design Basics Examples

These examples teach security and authentication shapes without Spring Security or a real identity provider.

They are educational only. Real applications should use proven security libraries and frameworks.

## Examples

- `AuthDtoShapeDemo.java`: request and response DTO shapes for login.
- `RolePermissionDemo.java`: roles and permission checks.
- `PasswordHashingConceptDemo.java`: an educational salted hash concept using Java standard library APIs.
- `TokenSessionConceptDemo.java`: compares a server-side session id with a token-like value.
- `SecureErrorResponseDemo.java`: returns safe errors without leaking sensitive details.
- `ValidationBeforeAuthenticationDemo.java`: validates request shape before authentication.
- `UserResponseMappingDemo.java`: avoids leaking password hashes in responses.

## Compile

```bash
javac examples/backend/security-auth-design-basics/*.java
```

## Run

```bash
java -cp examples/backend/security-auth-design-basics RolePermissionDemo
java -cp examples/backend/security-auth-design-basics UserResponseMappingDemo
```

## Key Ideas

Authentication verifies identity. Authorization checks what that identity may do. Password hashing protects stored credentials when done with proven password-hashing algorithms. These examples prepare you to recognize the same shapes in Spring Security.
