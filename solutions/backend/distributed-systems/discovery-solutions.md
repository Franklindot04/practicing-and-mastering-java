# Discovery Solutions

## Exercise 1

Users may experience slow requests or intermittent failures when routed to the unhealthy instance. Safeguards include timeouts, retrying another healthy instance when safe, short cache lifetimes, and metrics for failed calls by instance.

## Exercise 2

For optional dependencies, degraded mode or cached endpoints may be acceptable with clear limits. For critical dependencies, failing fast may be safer. Cached endpoints need expiration because stale routing can extend an outage.

