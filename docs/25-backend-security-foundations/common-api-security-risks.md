# Common API Security Risks

Security work starts by recognizing common failure patterns.

## Plaintext Passwords

Plaintext passwords are dangerous because anyone who reads the database can immediately use them. Store salted password hashes with a proven password hashing algorithm such as BCrypt.

## Broken Authentication

Authentication breaks when an API trusts a user without properly checking credentials, sessions, or tokens.

Examples:

- Accepting any token shape without verification.
- Letting demo credentials survive in real environments.
- Returning too much information for failed login attempts.

## Broken Authorization

Authorization breaks when a valid user can access data or actions they should not.

Examples:

- A normal user can access an admin endpoint.
- A user can update another user's resource by changing an id.
- A service account has more permissions than it needs.

## Input Problems

Unsafe input can cause data corruption, crashes, injection vulnerabilities, or unexpected behavior.

Validate shape, size, required fields, and allowed values before using input.

## Secrets In Source Code

Secrets committed to git can spread quickly and are difficult to remove fully. Use safe local examples only, and load real secrets from protected configuration outside the repository.

## Missing Rate Limits

Rate limiting restricts how many requests a client can make in a time window. It can reduce brute-force login attempts and abusive traffic.

## CORS And CSRF Confusion

CORS controls which browser origins may read responses. CSRF tricks a browser into sending an unwanted authenticated request. They solve different problems.

Learn the basics before changing defaults.
