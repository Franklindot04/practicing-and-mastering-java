# Password Encoding

Password encoding means storing a password hash instead of a plaintext password.

## PasswordEncoder

`PasswordEncoder` is the Spring Security interface for hashing and checking passwords.

Typical methods:

- `encode(rawPassword)`
- `matches(rawPassword, encodedPassword)`

## BCrypt Basics

BCrypt is designed for password hashing. It is intentionally slower than a normal hash function, which makes large password-guessing attacks more expensive.

In Spring Security:

```java
@Bean
PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

## Demo Users

Demo users and demo passwords can help learners test authentication locally, but they must be clearly labeled as demo-only.

Do not commit real user passwords, real password hashes from production, real tokens, or real secrets.

## Common Mistakes

- Storing plaintext passwords.
- Using fast general-purpose hashes for passwords.
- Logging passwords or hashes.
- Treating demo credentials as safe for production.
- Writing custom cryptography.
