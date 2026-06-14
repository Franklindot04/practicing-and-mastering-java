# Lambdas, Streams, And Optional Exercises

## Exercise 1: Filter Long Names

Difficulty: Intermediate

Concepts practiced: lambdas, predicates, streams

Problem statement: given a list of names, return only names longer than five characters.

Example output:

```text
[Franklin, Deborah]
```

Hints:

- Use `stream()`.
- Use `filter`.

Stretch challenge: sort the results alphabetically.

## Exercise 2: Total Approved Orders

Difficulty: Intermediate

Concepts practiced: records, streams, mapping, reducing

Problem statement: create an `Order` record with `status` and `amount`, then calculate the total amount for approved orders.

Hints:

- Filter by status first.
- Use `map` or `mapToDouble`.

Stretch challenge: use `BigDecimal` for the amount.

## Exercise 3: Optional Email Lookup

Difficulty: Intermediate

Concepts practiced: `Optional`, null avoidance

Problem statement: write a method that searches for a user by username and returns `Optional<String>` containing the user's email.

Hints:

- Return `Optional.empty()` when no user matches.
- Avoid returning `null`.

Stretch challenge: print a fallback message with `orElse`.
