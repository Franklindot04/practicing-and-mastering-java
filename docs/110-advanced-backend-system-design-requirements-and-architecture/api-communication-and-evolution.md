# API Communication And Evolution

Backend systems communicate through contracts. The right protocol depends on caller needs, latency budget, failure tolerance, compatibility requirements, operational tooling, and team experience.

## Communication Options

REST over HTTP is a strong default for resource-oriented public and internal APIs. It works well with status codes, caching, pagination, filtering, idempotency keys, and common gateway tooling.

RPC and gRPC concepts fit low-latency service-to-service calls with strongly typed contracts. They require compatibility discipline, load balancing, deadlines, retries, and observability that understands the protocol.

GraphQL concepts fit client-driven selection and backend-for-frontend use cases. It can reduce over-fetching, but query complexity, authorization, caching, N+1 data fetching, and schema evolution require care.

Asynchronous messaging fits independent reactions, buffering, workflow events, and projection building. It introduces eventual consistency, duplicate delivery, ordering questions, retry policies, dead-letter handling, and replay operations.

API gateways can centralize routing, authentication integration, rate limiting, TLS termination, and coarse observability. They should not hide unclear ownership or become the only place business rules are enforced.

Backend-for-frontend services tailor APIs to specific clients. They can improve mobile and web ergonomics, but they add another contract and operational owner.

Service discovery and load balancing decide how callers find healthy instances. In Kubernetes, this may be service DNS and kube-proxy or mesh routing. In a simpler deployment, it may be a load balancer and static environment configuration.

## Request Path Risks

Synchronous fan-out can multiply failure probability and latency. If one request calls five required services, the request succeeds only when all required dependencies meet the deadline. Optional dependencies should have fallbacks, partial response semantics, or asynchronous handling.

Every remote call needs:

- timeout or deadline
- retry policy with budget and jitter
- idempotency analysis before retrying writes
- circuit breaker or load shedding where failure amplification is likely
- bulkhead or connection-pool isolation
- correlation ID propagation
- clear error contract
- metrics and traces

Retries are not free. They increase load exactly when a dependency is struggling. A retry after an uncertain write can create duplicate side effects unless the operation is idempotent.

## API Design Details

Versioning can be URI-based, header-based, media-type-based, or schema-based. The important part is compatibility. Additive optional fields are safer than renames and type changes. Removing fields requires compatibility windows and migration plans.

Pagination should be explicit. Offset pagination is simple but can become expensive and inconsistent on changing datasets. Cursor pagination is often better for large or frequently updated collections.

Filtering and sorting should match indexes and authorization rules. Do not expose arbitrary database queries as API parameters.

Idempotency keys are important for retried creates, payments, order submissions, and external side effects. Store request identity, response outcome, and expiry rules.

Rate limiting protects capacity and abuse boundaries. Limits should consider user, tenant, IP, API key, endpoint, and system priority.

Error contracts should distinguish validation errors, authentication failures, authorization failures, not found, conflicts, rate limits, dependency failures, and temporary unavailability. Avoid leaking secrets or internal stack traces.

## Protocol Selection Questions

- Is the caller human-facing or machine-facing?
- Is the workflow read-heavy, write-heavy, or event-driven?
- Is the caller allowed to receive stale or partial data?
- Does the operation require immediate confirmation?
- Can duplicate delivery occur?
- What is the latency budget?
- How will contracts be tested?
- How will correlation, metrics, logs, and traces cross the boundary?
- What happens when the dependency is slow, unavailable, or returns an ambiguous result?
