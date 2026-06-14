# Interfaces, Records, Enums, Packages, And Modifiers

## Interfaces

An interface defines a role a class can perform.

```java
interface Printable {
    void print();
}
```

Classes can implement multiple interfaces, which makes interfaces useful for capabilities like printable, comparable, or persistable.

## Records

Records are concise classes for immutable data carriers.

```java
public record Point(int x, int y) {
}
```

## Enums

Enums represent a fixed set of values.

```java
public enum OrderStatus {
    NEW, PAID, SHIPPED, CANCELLED
}
```

## Static And Final

- `static` belongs to the class rather than one object.
- `final` prevents reassignment, overriding, or inheritance depending on where it is used.

## Packages And Access Modifiers

Packages organize code. Access modifiers control visibility:

- `public`: accessible everywhere.
- `private`: accessible only inside the class.
- `protected`: accessible in subclasses and same package.
- no modifier: package-private.

## Composition vs Inheritance

Prefer composition when one class has another thing. Use inheritance when one class truly is a specialized version of another.

Example: a `Car` has an `Engine`; it is not an `Engine`.

## Common Mistakes

- Using inheritance for "has-a" relationships.
- Making everything `public`.
- Using strings for fixed states that should be enums.
- Writing records for mutable objects with complex behavior.

## Before Moving On

You should be able to choose between class, interface, record, enum, composition, and inheritance for a small design.
