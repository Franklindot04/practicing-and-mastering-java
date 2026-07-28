# Readable Test Structure

Readable tests make the expected behavior obvious. A future maintainer should not need to mentally execute the production code to understand the assertion.

## Arrange, Act, Assert

```java
@Test
void rejectsEmptyTransferAmount() {
    Account source = new Account("source", new BigDecimal("10.00"));
    Account target = new Account("target", BigDecimal.ZERO);
    TransferService service = new TransferService();

    IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> service.transfer(source, target, BigDecimal.ZERO)
    );

    assertEquals("amount must be positive", exception.getMessage());
}
```

Arrange creates the test world. Act performs the behavior. Assert checks the observable result.

## Given, When, Then

Given-when-then is often useful when a scenario reads like a requirement:

```java
@Test
void givenExistingReservationWhenSameRequestIsRepeatedThenExistingResultIsReturned() {
    ReservationService service = new ReservationService(new InMemoryReservationRepository());
    ReservationRequest request = new ReservationRequest("request-1", "sku-1", 2);

    Reservation first = service.reserve(request);
    Reservation second = service.reserve(request);

    assertEquals(first.id(), second.id());
}
```

## Naming

Prefer names that describe behavior:

| Vague name | Better name |
| --- | --- |
| `testTotal` | `calculatesTotalForMultipleLineItems` |
| `invalidInput` | `rejectsNegativeQuantity` |
| `repositoryWorks` | `returnsEmptyWhenOrderDoesNotExist` |
| `retryTest` | `stopsRetryingAfterMaxAttempts` |

## Assertions

Use assertions that communicate intent:

- `assertEquals` for exact values.
- `assertTrue` or `assertFalse` only when the condition reads clearly.
- `assertThrows` for exception behavior.
- `assertIterableEquals` for ordered collections.
- Compare sets when order should not matter.
- Use a delta or comparator for floating-point values.

```java
assertEquals(0.3, 0.1 + 0.2, 0.000001);
```

For money, prefer `BigDecimal` or integer cents over `double`.

## Parameterized Tests

Parameterized tests are useful when the same behavior should hold for several inputs.

```java
@ParameterizedTest
@CsvSource({
        "0, false",
        "1, true",
        "99, true",
        "100, false"
})
void acceptsQuantitiesInsidePolicyLimit(int quantity, boolean expected) {
    InventoryPolicy policy = new InventoryPolicy(1, 99);

    assertEquals(expected, policy.canReserve(quantity));
}
```

Do not use parameterized tests to hide unrelated scenarios in one large table.

