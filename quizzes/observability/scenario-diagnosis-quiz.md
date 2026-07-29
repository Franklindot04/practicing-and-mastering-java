# Scenario Diagnosis Quiz

## Scenario 1: Slow Checkout

Evidence:

- Request traffic is normal.
- p95 checkout latency rose from 300ms to 1400ms.
- Payment dependency timeout count increased.
- Retries increased.
- Errors are still below the paging threshold.

Questions:

1. What is the symptom?
2. What are two cause candidates?
3. What evidence is missing?
4. What mitigation would you consider?
5. What should not be concluded yet?

## Scenario 2: Stale Reports

Evidence:

- Report freshness SLO is burning slowly.
- Queue depth is high.
- Database error count is normal.
- A deployment changed worker concurrency.
- Logs show jobs accepted but delayed.

Questions:

1. Which evidence points to queue saturation?
2. Which evidence points away from database failure?
3. What trace-like records would help?
4. What alert response is appropriate: page, ticket, or informational?
5. What post-incident corrective action might be useful?

