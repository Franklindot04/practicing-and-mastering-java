# Java Code Reading Quiz

## 1. Repeated Lookup

```java
for (String id : requestedIds) {
    if (allowedIds.contains(id)) {
        accepted.add(id);
    }
}
```

`allowedIds` is a large `List`. What performance issue might appear, and what tradeoff does an index introduce?

## 2. Retention

```java
private static final Map<String, byte[]> reports = new HashMap<>();
```

Reports are added for every request and never removed. What kind of memory problem is this?

## 3. Benchmark Trap

```java
for (int i = 0; i < 1_000_000; i++) {
    parse("A|B|C");
}
```

The parsed result is unused. Name two benchmark smells.
