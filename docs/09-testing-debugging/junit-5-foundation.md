# JUnit 5 Foundation

JUnit 5 is the testing framework used in this repository.

## Common Assertions

```java
assertEquals(expected, actual);
assertTrue(condition);
assertFalse(condition);
assertThrows(ExceptionType.class, () -> codeThatThrows());
```

## Test Naming

Good test names describe behavior:

```java
void addReturnsSumOfTwoNumbers()
void withdrawRejectsNegativeAmount()
void averageReturnsZeroForEmptyScores()
```

## Running Tests

After the Maven foundation is merged:

```bash
mvn test
```

## Common Mistakes

- Testing private methods directly.
- Writing tests that depend on execution order.
- Using vague test names.
- Skipping edge cases.

## Practice Prompts

- Test a calculator.
- Test invalid input.
- Test an empty list.
- Test a boundary value.
