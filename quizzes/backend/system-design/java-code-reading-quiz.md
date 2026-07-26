# Java Code Reading Quiz

Read this simplified code:

```java
public String submit(String key, Supplier<String> action) {
    return results.computeIfAbsent(key, ignored -> action.get());
}
```

1. What pattern does this resemble?
2. What behavior happens when the same key is submitted twice?
3. What limitation does this in-memory implementation have?

Read this simplified code:

```java
if (!permits.tryAcquire()) {
    return Optional.empty();
}
try {
    return Optional.of(action.get());
} finally {
    permits.release();
}
```

4. What pattern does this resemble?
5. What failure mode does it help isolate?
6. Why is the `finally` block important?

Read this simplified code:

```java
if (failures >= threshold) {
    state = OPEN;
}
```

7. What pattern does this resemble?
8. What should callers usually receive while the state is open?
