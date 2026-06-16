# Service Mesh Basics Solutions

## Exercise 1: Explain The Mesh Boundary

A service mesh can manage internal service-to-service traffic concerns such as telemetry, mTLS, traffic splitting, retries, timeouts, and policy enforcement. Application code still owns validation, domain authorization, business rules, persistence decisions, and meaningful error responses.

An API gateway is a better fit for public entry traffic, external authentication, client rate limiting, and public route exposure.

## Exercise 2: Decide Whether A Mesh Is Needed

A mesh is not justified for one simple Spring Boot API with no internal service calls. There is no repeated service-to-service policy to centralize, and the team would inherit proxy, policy, identity, and control-plane complexity without a real communication problem.

Better next steps include improving health checks, logs, metrics, deployment notes, API contracts, and readiness documentation.

