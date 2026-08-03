# Code Review

1. CR-01: A method catches `Exception`, logs `"failed"`, and returns an empty list. Identify two risks and one safer alternative.
2. CR-02: A service creates a new `Thread` for every request. Identify the operational risk and propose a Java concurrency alternative.
3. CR-03: A controller returns raw exception messages to API clients. Explain the security and usability problem.
4. CR-04: A payment retry endpoint lacks an idempotency key. Identify the failure mode.
5. CR-05: A test uses `Thread.sleep(5000)` to wait for asynchronous behavior. Propose a deterministic alternative.
6. CR-06: A benchmark compares two methods without warm-up or fixed input data. Explain why the conclusion is weak.
7. CR-07: A repository stores secrets in `application.properties`. Explain the risk and safer handling.
8. CR-08: A message consumer mutates state before validating event schema. Identify the compatibility risk.
