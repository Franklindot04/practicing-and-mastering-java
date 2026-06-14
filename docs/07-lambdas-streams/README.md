# Lambdas, Functional Interfaces, Streams, And Optional

## Lambdas

A lambda is a compact way to pass behavior.

```java
names.forEach(name -> System.out.println(name));
```

## Functional Interfaces

A functional interface has one abstract method. Examples include `Predicate<T>`, `Function<T, R>`, `Consumer<T>`, and `Supplier<T>`.

## Streams

Streams help transform collections.

```java
List<String> upper = names.stream()
    .filter(name -> !name.isBlank())
    .map(String::toUpperCase)
    .toList();
```

## Optional

`Optional<T>` represents a value that may or may not exist.

```java
Optional<String> result = findName();
result.ifPresent(System.out::println);
```

Common mistakes:

- Using streams when a loop is clearer.
- Calling `Optional.get()` without checking.
- Mutating external state inside stream operations.
- Making lambdas so long they become hard to read.

Before moving on, you should understand `filter`, `map`, `toList`, method references, and safe `Optional` handling.

## Next Practice

- [ ] Run the lambdas, streams, and Optional example from [Intermediate Java Examples](../../examples/intermediate/java/README.md).
- [ ] Complete functional Java exercises in [Intermediate Exercises](../../exercises/intermediate/).
- [ ] Refactor one loop into a stream only when the stream remains readable.
