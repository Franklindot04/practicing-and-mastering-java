# Lambdas, Streams, And Optional Solutions

## Filter Long Names

Stream the list, filter with `name -> name.length() > 5`, and collect the result with `toList()`.

## Total Approved Orders

Filter orders by approved status before summing amounts. If using money, prefer `BigDecimal` and reduce with `BigDecimal::add`.

## Optional Email Lookup

Return `Optional.of(email)` when a user is found and `Optional.empty()` otherwise. Let callers decide whether to use `orElse`, `ifPresent`, or `orElseThrow`.
