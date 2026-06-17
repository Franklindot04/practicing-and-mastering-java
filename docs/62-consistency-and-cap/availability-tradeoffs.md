# Availability Tradeoffs

Availability means the system continues responding to requests. In distributed systems, availability is not only about uptime. It is also about which operations remain possible during dependency failures.

## Availability Is Not Always Success

A response can be available but limited:

- "Your request was accepted and will process later."
- "Showing cached data from a few minutes ago."
- "This feature is temporarily read-only."
- "We cannot complete this operation safely right now."

These responses are design choices.

## When To Prefer Consistency

Prefer consistency when accepting stale or conflicting data would be dangerous.

Examples:

- Charging a customer.
- Changing access permissions.
- Reserving scarce inventory.
- Marking a legal or financial record complete.

## When To Prefer Availability

Prefer availability when temporary staleness is acceptable and the system can recover.

Examples:

- Showing recommendations.
- Counting views.
- Sending non-critical notifications.
- Displaying cached catalog data.

## Design Checklist

For each operation, document:

- The required consistency expectation.
- Whether stale reads are acceptable.
- Whether duplicate writes are acceptable.
- What users see during degraded behavior.
- How reconciliation or repair happens.
- Which metrics and logs show the tradeoff in action.

