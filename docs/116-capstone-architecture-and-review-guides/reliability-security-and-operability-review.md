# Reliability, Security, and Operability Review

Reliability review should focus on failure modes, bounded behavior, recovery paths, and operational signals. Security review should identify trust boundaries and missing controls without pretending the capstones include full production hardening.

## Reliability

- Timeouts, retries, backoff, duplicate protection, load shedding, dead-letter handling, and reconciliation are covered where relevant.
- Recovery is deterministic in tests and honest about durability limits.
- Runbooks start from symptoms and lead to evidence, not guesses.

## Security

- Identity and authorization boundaries are identified.
- Secrets, credentials, personal data, tenant isolation, and audit retention are treated as deployment concerns unless actually implemented.
- Threat considerations are explicit and proportional to the simulation.

## Operability

- Metrics, audit trails, reports, and diagnostic outputs are connected to scenarios.
- Alerting and SLO language is reserved for documented operational models.
