# SLIs, SLOs, Error Budgets, And Burn Rates

Service-level indicators are measurements. Service-level objectives are targets for those measurements. Service-level agreements are external commitments, often contractual. Keep those terms separate.

## Choosing SLIs

Good SLIs describe user-visible outcomes:

- Availability: successful requests divided by valid requests.
- Latency: percentage of requests under a threshold.
- Correctness: percentage of operations producing the expected result.
- Freshness: percentage of data updates visible within a time objective.
- Durability concepts: whether accepted data remains safely recoverable.

Request-based measurements count eligible events. Time-based measurements count good and bad time windows. Both need clear definitions.

## Error Budgets

If an SLO allows 0.1 percent failure over a window, that 0.1 percent is the error budget. Spending it too quickly means the service is burning reliability faster than planned.

| Burn pattern | Meaning |
| --- | --- |
| Fast burn | Severe current impact; page-worthy when tied to user outcomes. |
| Slow burn | Lower rate but sustained risk; often ticket or daytime response. |
| Multi-window burn | Confirms both immediate impact and sustained pattern. |

Do not call it a failure budget without defining the measurement assumptions. Error budgets are tied to SLO math, eligible traffic, windows, and user impact.

## Practical SLO Questions

- Which users or workflows does this SLO represent?
- Which requests are eligible?
- Are client errors counted?
- Are retries counted once or per attempt?
- Does latency use distributions rather than averages?
- How does partial availability appear?
- What happens when telemetry is missing?

