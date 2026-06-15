# Passwords, Tokens, And JWT Quiz

## Multiple Choice

1. Why should passwords be hashed?
   - A. Hashing is reversible.
   - B. The original password should not be stored.
   - C. Hashing makes passwords public.
   - D. Hashing removes the need for authorization.

2. Which algorithm is commonly used for password hashing in Spring Security?
   - A. BCrypt
   - B. Plain SHA-1 without salt
   - C. Base64
   - D. ROT13

3. A JWT payload is:
   - A. Always encrypted
   - B. Encoded and should not contain secrets
   - C. A database migration
   - D. A password hash

4. What is an access token usually used for?
   - A. Calling protected APIs
   - B. Storing plaintext passwords
   - C. Creating Docker images
   - D. Replacing validation

## Short Answer

5. Give one benefit and one tradeoff of server-side sessions.

6. Why do JWT expiration times matter?

## Design Reading

7. A developer wants to put a user's password in a JWT claim so the API can reuse it later. What is wrong?
