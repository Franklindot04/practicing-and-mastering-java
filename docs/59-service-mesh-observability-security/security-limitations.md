# Security Limitations

A service mesh can improve transport security and policy consistency, but it does not make a system secure by itself.

## Limits To Remember

- mTLS does not validate business input.
- Workload identity does not authenticate end users.
- Traffic policy does not fix broken authorization.
- Encryption in transit does not replace encryption or controls for stored data.
- Mesh policy does not remove the need for secret management.
- Proxy logs can still leak sensitive data if configured carelessly.
- A compromised service can still make valid-looking calls if policy is too broad.

## Application Responsibilities

Applications still need:

- Strong validation.
- Safe error handling.
- Authentication and authorization.
- Least-privilege domain checks.
- Audit-friendly logging.
- Secure persistence and data handling.

## Platform Responsibilities

Platform teams still need safe upgrades, certificate rotation, policy review, monitoring, incident response, and clear ownership.

## Practical Advice

Treat mesh security as one layer. It is strongest when combined with secure application design, least privilege, clear service boundaries, and careful operations.

