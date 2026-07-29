# Incident Diagnostics And Learning

Incident response balances mitigation and diagnosis. Mitigation reduces impact. Diagnosis explains contributing factors with evidence. They often happen in parallel, but they are not the same activity.

## Evidence Preservation

Useful evidence may include:

- Incident timeline with absolute times.
- Log snapshots around the impact window.
- Metrics snapshots for traffic, errors, latency, saturation, and dependencies.
- Trace-like examples for affected requests.
- Thread dumps for blocked or stuck Java processes.
- Heap dumps only when needed and handled as sensitive data.
- JFR recordings when CPU, allocation, lock, or runtime behavior matters.
- Deployment, configuration, and feature-flag changes.

Diagnostic commands can be risky. Thread dumps may reveal class names, paths, and request details. Heap dumps can contain secrets and personal data. JFR files can include sensitive runtime context. Capture the minimum useful evidence and store it safely.

## Incident Roles And Decisions

Common roles include incident lead, communications lead, subject-matter investigator, and scribe. The team should distinguish rollback decisions, traffic shaping, dependency mitigation, data correction, and evidence capture.

Rollback decision factors:

- Is the current version strongly linked to impact?
- Is rollback safer than forward fix?
- Is data migration involved?
- Will rollback increase queue pressure or retries?
- What evidence will be lost if processes restart?

## Post-Incident Learning

Prefer contributing factors over a single root-cause story. Good reviews identify what happened, why it made sense at the time, where observability was missing, what mitigated impact, and which corrective actions are concrete.

## Diagnostic Checklist

- [ ] State evidence separately from speculation.
- [ ] Preserve relevant logs, metrics, traces, profiles, and dumps safely.
- [ ] Track mitigation actions with timestamps.
- [ ] Note uncertainty and instrumentation gaps.
- [ ] Avoid blame-focused wording.
- [ ] Convert learning into small corrective actions.
- [ ] Review alert quality after the incident.

