# Integration, Contract, And End-To-End Testing

Broader tests check whether pieces work together. They are valuable because many production failures happen at boundaries: data shape, configuration, persistence, network assumptions, async timing, and environment drift.

## Study Order

1. [Integration And Component Testing](integration-and-component-testing.md)
2. [Contract Testing](contract-testing.md)
3. [End-To-End And Environment Testing](end-to-end-and-environment-testing.md)

## Layer Overview

| Layer | Focus | Example |
| --- | --- | --- |
| Component | A meaningful slice inside one application. | Order service with fake repository and fake notifier. |
| Integration | Real adapters or modules interacting. | Repository code against a controlled test database. |
| Contract | Compatibility between provider and consumer. | Consumer expects `orderId`, `status`, and `reservedAt` fields. |
| System | Whole application behavior. | API starts, accepts a request, writes state, returns a response. |
| End-to-end | High-value user journey across boundaries. | Customer reserves inventory and sees confirmation. |

These layers should complement unit tests. They should not become a slow duplicate of every small logic branch.

## Java Boundary Example

```java
interface InventoryClient {
    ReservationResponse reserve(ReservationRequest request);
}
```

A unit test can verify how a service reacts to a successful response. A contract test can verify that the request and response shape remains compatible. An integration test can verify the HTTP adapter serializes the request correctly. An end-to-end test can verify the most important reservation journey.

## Common Mistakes

- Using arbitrary sleeps for asynchronous workflows.
- Testing every edge case through the browser or full system.
- Relying on shared test environments without clear ownership.
- Treating in-memory substitutes as proof that production infrastructure will behave the same.
- Ignoring rollback, migration, and cleanup paths.
- Keeping contracts undocumented until consumers break.

## Review Questions

1. What boundary does an integration test check that a unit test usually does not?
2. How is a contract test different from an end-to-end test?
3. Why should eventual consistency tests use bounded waits?
4. When is an in-memory substitute helpful, and what can it hide?
5. What makes a user journey worth end-to-end coverage?

