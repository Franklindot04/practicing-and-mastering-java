# Logging And Observability

Logging and observability help you understand what an application is doing after it leaves your laptop. Tests tell you whether known behavior works. Observability helps you investigate behavior in a running system.

For this repository, observability means learning the basics:

- Logs: event records written by the application.
- Metrics: numeric measurements over time.
- Health checks: small endpoints that report application status.
- Traces: request flow across code or services.

These notes introduce the ideas without requiring external monitoring tools.

## Monitoring Versus Observability

Monitoring asks, "Is something wrong?"

Observability asks, "Why is it wrong, and where should I look next?"

Both matter. A beginner backend can start with useful logs, a health endpoint, and clear error responses.

## What To Study

1. [Logging Basics](logging-basics.md)
2. [Metrics And Health Checks](metrics-health-checks.md)
3. [Tracing And Observability Basics](tracing-observability-basics.md)

## Guiding Rule

Log enough to understand important behavior. Do not log secrets, passwords, raw tokens, private keys, or sensitive personal data.
