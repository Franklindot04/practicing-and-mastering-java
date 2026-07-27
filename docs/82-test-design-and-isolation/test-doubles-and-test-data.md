# Test Doubles And Test Data

Test doubles replace collaborators for a reason. The goal is controlled evidence, not maximum replacement.

## Double Types

| Double | What it does | Example use |
| --- | --- | --- |
| Dummy | Passed because a parameter is required but not used. | A notification object in a test that never sends. |
| Stub | Returns prepared answers. | A tax-rate service returns `0.07`. |
| Spy | Records how it was used. | A notifier records messages sent. |
| Mock | Verifies expected interactions. | A payment gateway must be called once with a specific request. |
| Fake | Working lightweight implementation. | An in-memory repository. |

## State And Behavior Verification

State verification checks the result after behavior:

```java
service.reserve("sku-1", 2);

assertEquals(8, repository.stockFor("sku-1"));
```

Behavior verification checks an interaction:

```java
RecordingNotifier notifier = new RecordingNotifier();
service.reserve("sku-1", 2);

assertEquals(List.of("reserved sku-1"), notifier.messages());
```

Use behavior verification when the interaction is the behavior. Avoid verifying every internal call just because it is possible.

## Fixture Builders

Builders keep important test data visible while hiding irrelevant defaults.

```java
Order order = OrderBuilder.anOrder()
        .withLine("sku-1", 2)
        .withPreferredCustomer()
        .build();
```

Object mothers can be useful for broad named fixtures, such as `Orders.validSingleItemOrder()`, but they can become a dumping ground. Builders usually scale better when tests need small variations.

## Dependency Injection For Testability

Inject collaborators that represent boundaries:

- `Clock` for time.
- `IdGenerator` for IDs.
- `Random` or a custom random source for probabilistic behavior.
- Repository interfaces for persistence.
- Notification or payment ports for external effects.

This makes tests simpler and also makes production behavior easier to reason about.

## Testing Private Methods

Private methods are implementation details. Test them through public behavior when possible. If a private method is complex enough to need direct tests, it may be a separate concept that deserves its own class with a public behavior.

## Over-Mocking

Over-mocking happens when tests are coupled to how code is organized rather than what it promises. Symptoms include:

- Tests fail after harmless refactors.
- Mocks assert a long chain of internal calls.
- The real collaboration is never tested anywhere.
- Tests pass even though the fake interaction differs from production behavior.

Prefer the smallest double that gives useful evidence.

