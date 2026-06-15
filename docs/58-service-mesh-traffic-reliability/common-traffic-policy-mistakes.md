# Common Traffic Policy Mistakes

Traffic policy can make distributed systems more reliable, but unsafe defaults can also turn a small failure into a larger incident.

## Mistakes To Avoid

- Adding retries without timeouts.
- Retrying non-idempotent operations.
- Setting mesh timeouts that conflict with application timeouts.
- Splitting traffic without metrics or rollback criteria.
- Treating canary success as only HTTP `200` responses.
- Ignoring resource cost from sidecars and retries.
- Using one policy for every service regardless of latency or business risk.
- Assuming the mesh can fix poor service boundaries.

## Review Questions

- What is the caller's total deadline?
- Which operations are safe to retry?
- What signal triggers rollback?
- Where will errors and traces be visible?
- Who owns policy changes during an incident?

## Practical Guideline

Start with simple, explicit policies. Add sophistication only when the team can observe, explain, and reverse the behavior.

