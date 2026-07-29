# Safe Logging And Operational Practices

Logs can become long-lived records. Treat them as potentially sensitive operational evidence.

## Sensitive Data

Never log secrets, private keys, authorization headers, session cookies, passwords, raw tokens, payment card data, or full personal records. Redact known sensitive keys case-insensitively, such as `password`, `token`, `authorization`, and `secret`.

Educational examples may mention those words to teach redaction, but should not include real values.

## Volume Controls

| Control | Use |
| --- | --- |
| Sampling | Keep representative high-volume events. |
| Rate limiting | Prevent repeated failures from flooding logs. |
| Deduplication | Collapse repeated identical events. |
| Severity choice | Keep normal outcomes out of warning and error channels. |

Asynchronous logging can reduce request-path cost, but buffering can lose events during crashes or shutdown. Rotation and retention policies should match operational and privacy needs.

## Local Versus Production Logs

Local development logs may be verbose and human-friendly. Production logs should be structured, bounded in volume, safe, and correlated. Startup and shutdown events should identify service name, version, profile, and important safe configuration choices without dumping the whole environment.

## Practical Review Checklist

- [ ] Are event names stable?
- [ ] Are timestamps in UTC?
- [ ] Are secrets and personal data absent or redacted?
- [ ] Are user-provided values escaped or field-encoded?
- [ ] Is severity proportional to operational impact?
- [ ] Is duplicate exception logging avoided?
- [ ] Are request, trace, span, and operation fields present where useful?
- [ ] Is high-volume logging sampled, rate-limited, or removed?
- [ ] Are audit logs separated from diagnostic logs?
- [ ] Do tests verify important logging decisions without brittle text matching?

## Anti-Patterns

- Logging everything at `error`.
- Logging raw request bodies by default.
- Using string concatenation with untrusted values.
- Logging and rethrowing at every layer.
- Storing request IDs as metric labels.
- Assuming logs are harmless because they are "internal."

