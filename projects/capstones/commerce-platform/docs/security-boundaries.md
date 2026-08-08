# Security Boundaries

Identity and authorization are represented as a boundary rather than a full authentication system. The capstone assumes caller identity has been established before checkout. A real service would require token validation, role checks, audit retention, rate limits, secret management, and abuse monitoring.

## Purpose

The security boundary keeps the capstone honest: checkout should not silently assume every caller can perform every action, but the project also should not pretend to implement a full identity platform. The docs identify where authentication and authorization would integrate with the business workflow.

## Assumptions

- Caller identity is already established before domain methods are invoked.
- Customer identifiers in tests are fixtures, not real personal data.
- Payment processing is represented by local results and no real card data is stored.
- No secrets are required for default tests.

## Required Production Controls

A deployed commerce API would need token validation, role and ownership checks, tenant isolation, rate limits, fraud and abuse controls, secrets management, structured audit retention, safe error responses, dependency review, and incident procedures for suspected account or payment abuse.

## Trade-Offs

Implementing full authentication in this capstone would add framework configuration and test setup that distract from checkout, idempotency, and failure handling. Leaving the boundary undocumented would be worse because it would make security look solved by omission. The chosen approach documents the gap and keeps the simulation focused.

## Monitoring Expectations

Security-relevant telemetry would include rejected authorization attempts, suspicious duplicate checkout keys, high payment rejection rates, rate-limit activity, audit write failures, and administrative access. The capstone exposes only local audit counts, so production security monitoring remains a future implementation concern.
