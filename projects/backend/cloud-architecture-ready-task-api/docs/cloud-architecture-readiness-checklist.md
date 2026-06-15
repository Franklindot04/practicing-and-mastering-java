# Cloud Architecture Readiness Checklist

## Application Readiness

- The API can run without storing session state in memory.
- Configuration comes from environment or approved runtime settings.
- Health endpoints are understood.
- Database connection limits are planned.
- Logs do not expose secrets or sensitive data.

## Architecture Readiness

- Traffic flow is diagrammed.
- Stateful components are identified.
- Single points of failure are named.
- Scaling risks are documented.
- Cost risks are reviewed.
- Failure modes have response notes.

## Safety Boundaries

- No real domains, URLs, account IDs, credentials, or provider-specific identifiers are committed.
- No cloud resources are created from this folder.
- Architecture notes are reviewed before any real deployment design.

