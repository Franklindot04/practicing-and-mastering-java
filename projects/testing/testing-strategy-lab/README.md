# Testing Strategy Lab

This project demonstrates a layered testing strategy for a small inventory reservation and order-fulfillment application. It uses plain Java and JUnit 5, with hand-written fakes instead of external infrastructure.

## System Under Test

The system reserves inventory for an order request. The application service coordinates:

- `InventoryRepository` for stock and reservation state.
- `NotificationPort` for fulfillment notifications.
- `InventoryPolicy` for validation boundaries.
- `IdGenerator` and `Clock` for deterministic IDs and timestamps.

## Test Strategy

| Test purpose | Files | Boundary |
| --- | --- | --- |
| Unit tests | `InventoryPolicyTest`, `RetryPolicyTest` | Single class behavior. |
| Component tests | `ReservationServiceTest` | Service with in-memory repository and recording notification. |
| Integration-style tests | `InMemoryInventoryRepositoryTest` | Real in-memory implementation behavior. |
| Contract-style tests | `InventoryRepositoryContractTest` | Repository interface expectations. |
| Concurrency tests | `ReservationConcurrencyTest` | Bounded synchronization around shared stock. |

## Test Matrix

| Risk | Test evidence |
| --- | --- |
| Invalid quantities | Boundary and parameterized policy tests. |
| Overselling inventory | Service tests and concurrency test. |
| Duplicate requests | Idempotency component test. |
| Lost notification | Recording port behavior verification. |
| Retry behavior | Deterministic retry policy test. |
| Eventual state | Bounded polling test. |

## What Is Faked, Stubbed, Or Mocked

- Repository behavior uses a real in-memory fake because persistence concepts are not the focus.
- Notification behavior uses a recording fake so tests can inspect messages without external services.
- Time and IDs are deterministic collaborators.
- No mocking framework is used.

## Why Real Infrastructure Is Not Included

The goal is testing strategy, not deployment or infrastructure provisioning. Real databases, brokers, external APIs, containers, cloud resources, and credentials are intentionally out of scope.

## Quality Gates

- Run `mvn test`.
- Keep tests deterministic and independent.
- Do not commit `target` directories or `.class` files.
- Treat coverage as a signal, not proof of correctness.

## Release-Confidence Checklist

- Important happy, edge, negative, and failure paths are covered.
- Idempotency and concurrency risks have focused tests.
- Test boundaries are documented.
- Known limitation: in-memory storage does not prove production database behavior.

