# Date, Time, Maven, And JUnit Solutions

## Days Until Deadline

Use `ChronoUnit.DAYS.between(today, deadline)`. Accept `today` as a parameter in tests so the result is predictable.

## Format A Meeting Time

Create a `DateTimeFormatter` with a pattern such as `dd MMM yyyy, HH:mm`, then call `dateTime.format(formatter)`.

## Add JUnit Tests

Put the test class under `src/test/java`, annotate methods with `@Test`, and assert both passing and review scores with `assertEquals`.
