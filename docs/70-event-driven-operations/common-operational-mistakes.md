# Common Operational Mistakes

Event-driven operations fail when teams only design the happy path.

## Mistakes

- Treating published events as proof that consumers succeeded.
- Logging without event IDs or correlation IDs.
- Retrying forever without isolating poison events.
- Ignoring duplicate delivery.
- Making event payloads impossible to inspect safely.
- Adding consumers without ownership or alerts.
- Changing event contracts without compatibility review.
- Measuring only producer success.

## Better Habits

- Track producer and consumer outcomes separately.
- Use stable identifiers.
- Define retry and review behavior.
- Keep event examples for compatibility checks.
- Document consumer ownership.
- Monitor age of unprocessed work, not only total failure count.

## Review Question

If a user says, "My notification never arrived," can the team prove whether the event was missing, delayed, failed, duplicated, or successfully processed?
