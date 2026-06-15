# Traffic Reliability Quiz

## Multiple Choice

1. What is a canary release?
   - A. Sending a small portion of traffic to a new version before full rollout
   - B. Deleting old logs after deployment
   - C. Running tests without dependencies
   - D. Replacing authentication with mTLS

2. Why can retries be dangerous?
   - A. They always make requests slower but safer
   - B. They can duplicate side effects or amplify load
   - C. They disable all metrics
   - D. They remove the need for timeouts

3. What does a circuit breaker help prevent?
   - A. Java compilation
   - B. Sending unlimited traffic to an unhealthy dependency
   - C. Writing unit tests
   - D. Version control conflicts

## Short Answer

4. Why should timeout policy be coordinated between application code and mesh policy?

5. Name three signals to watch during a canary.

## Design Reading

6. A mesh retries task creation twice after timeout. What risk should you raise, and what would make the operation safer?

