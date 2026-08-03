# Test Doubles, Integration, And Contract Testing

Test doubles replace collaborators so a test can focus on one behavior. They are powerful, but every double creates a risk that the test no longer represents the real boundary.

## Types Of Doubles

A dummy is passed only because the method signature requires it. A stub returns canned answers. A fake is a working implementation with simplified infrastructure, such as an in-memory repository. A spy records calls for later inspection. A mock verifies expected interactions. A simulator models an external dependency with realistic behavior, latency, failures, or protocol rules.

Use a stub when a collaborator only supplies data. Use a fake when state and contract matter. Use a spy when the side effect is part of the behavior. Use a mock when the interaction itself is the contract. Use a simulator when failure modes, protocol timing, or compatibility matter.

## Over-Mocking

Over-mocking creates tests that know too much about internal collaboration. These tests often pass while behavior is wrong or fail during harmless refactoring. A fake repository plus state-based assertions can be safer than mocks for domain workflows because it proves the service can persist, query, and update realistic state.

## Boundaries

Java systems often cross boundaries through clocks, random generators, files, HTTP clients, database repositories, queues, serializers, and framework adapters. Unit and component tests can replace these boundaries, but at least some integration tests should prove the real adapter behaves as expected.

## Integration Tests

Repository tests verify SQL, mapping, constraints, transactions, pagination, and migration compatibility. API integration tests verify routing, serialization, validation, error shape, status codes, and security boundaries. Messaging tests verify schema evolution, idempotency, ordering expectations, dead-letter behavior, and retry classification.

Disposable infrastructure and Testcontainers concepts are useful when real infrastructure behavior matters. They should remain opt-in or isolated from default tests unless the project explicitly supports them. Default tests in this repository should not require Docker, databases, brokers, external APIs, or network access.

## Contract Testing

Provider contracts define what a service promises. Consumer-driven contracts define what a consumer depends on. Both reduce contract drift. They are useful for REST payloads, event schemas, validation rules, enum evolution, required fields, and backward compatibility.

A compatible change might add an optional field. A breaking change might rename a required field, change a type, remove an event, or narrow accepted values. Contract tests should fail with a message that tells the owner which expectation changed.

## Test Data And Diagnosis

Integration tests need isolated data, cleanup, and diagnostic output. Parallel execution requires unique identifiers and no shared mutable state. When a boundary test fails, logs, request bodies, response bodies, schema versions, and correlation IDs make the failure actionable.
