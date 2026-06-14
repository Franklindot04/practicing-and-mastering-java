# Date And Time API

Modern Java date and time code lives in `java.time`.

```java
LocalDate today = LocalDate.now();
LocalDate deadline = today.plusDays(7);
Duration studyTime = Duration.ofMinutes(90);
```

Use:

- `LocalDate` for dates without time.
- `LocalTime` for time without date.
- `LocalDateTime` for date and time without zone.
- `ZonedDateTime` when time zones matter.
- `Duration` for time-based amounts.
- `Period` for date-based amounts.

Common mistakes:

- Using old `Date` and `Calendar` APIs for new code.
- Ignoring time zones.
- Confusing `Duration` and `Period`.

Practice prompts:

- Calculate a date seven days from today.
- Format a date for display.
- Measure the duration between two times.
