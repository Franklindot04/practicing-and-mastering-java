# Security And Trust Boundaries

Security design starts by identifying assets, actors, trust boundaries, and abuse paths. A backend architecture is incomplete when it shows services and databases but not who can call them, what data crosses each boundary, and how misuse is detected.

## Threat Modelling

Threat modelling asks:

- what are we protecting?
- who can attack or misuse it?
- where do trust boundaries exist?
- what can go wrong?
- what mitigations exist?
- what residual risks remain?

Important assets include credentials, sessions, tokens, payment references, customer addresses, tenant data, admin actions, audit logs, secrets, encryption keys, event payloads, exports, backups, and operational dashboards.

## Identity And Access

Authentication proves identity. Authorization decides whether that identity can perform an action. Service identities let workloads authenticate to each other without sharing user credentials.

Least privilege means users, services, workers, and operators receive only the access required. Tenant isolation must be enforced in APIs, queries, cache keys, event consumers, logs, exports, and administrative tools.

Token handling must avoid leaking tokens into logs, URLs, analytics, traces, or client-visible errors. Session expiry, refresh, revocation, audience, issuer, and clock skew should be explicit.

## Secrets And Encryption

Secrets should come from managed configuration or secret stores, not source code. Rotate credentials and keys deliberately. Separate development, test, and production credentials.

Encryption in transit protects traffic between clients, services, brokers, databases, and operators. Encryption at rest protects stored data, backups, snapshots, object storage, and logs. Key rotation and access logging are part of the design.

## Input Data And Abuse

Input validation should happen at boundaries and again where domain invariants require it. Treat events and messages as untrusted input, even when produced internally.

Abuse prevention may include rate limits, anomaly detection, fraud signals, bot protection, request size limits, idempotency controls, audit trails, and alerting on suspicious behaviour.

Supply-chain security includes dependency review, build provenance concepts, artifact integrity, vulnerability scanning, least-privilege CI credentials, and review of generated artifacts.

## Privacy By Design

Classify data before storage and transmission. Collect only what is needed, define retention, support deletion where legally required, protect exports, and avoid putting sensitive data into caches, search indexes, logs, or dead-letter queues without review.

Incident response needs owners, escalation paths, evidence collection, communication plans, and post-incident learning. Security events should be auditable without exposing private data unnecessarily.
