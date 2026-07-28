# Test Design And Isolation

Good tests communicate behavior clearly. They also isolate the state, time, randomness, files, environment variables, and collaborators that could make results depend on execution order or machine conditions.

## Study Order

1. [Readable Test Structure](readable-test-structure.md)
2. [Isolation And Determinism](isolation-and-determinism.md)
3. [Test Doubles And Test Data](test-doubles-and-test-data.md)

## Test Shape

Two common naming styles are arrange-act-assert and given-when-then. They describe the same habit: prepare a situation, perform one behavior, and check the result.

```java
@Test
void calculatesDiscountForPreferredCustomer() {
    Customer customer = new Customer("Asha", CustomerType.PREFERRED);
    Order order = new Order(List.of(new LineItem("book", 2, new BigDecimal("15.00"))));

    BigDecimal total = new PricingService().totalFor(customer, order);

    assertEquals(new BigDecimal("27.00"), total);
}
```

The test name says the behavior. The body shows one reason to expect the result.

## Design Principles

| Principle | Why it matters |
| --- | --- |
| One behavior per test | Failures point to a specific broken rule. |
| Descriptive names | The suite reads like executable documentation. |
| Clear fixtures | Readers can see why the expected value follows. |
| Independent tests | Order does not affect results. |
| Hermetic boundaries | Local machine state does not decide pass or fail. |
| Deterministic inputs | Time, randomness, and IDs are controlled. |

## Test Smells

- Assertions mirror implementation details instead of business behavior.
- Shared setup hides important test data.
- Tests pass when run alone but fail in the full suite.
- Random inputs are not seeded or printed on failure.
- Mocks verify internal call order that users do not care about.
- Private methods are tested directly because public behavior is hard to test.
- A test waits for a fixed time instead of waiting for a condition with a timeout.

## Review Questions

1. Why is one behavior per test easier to maintain?
2. When does shared setup help, and when does it hide meaning?
3. Why should tests control clocks and ID generation?
4. What is the difference between state verification and behavior verification?
5. How can over-mocking make a refactor look like a regression?

