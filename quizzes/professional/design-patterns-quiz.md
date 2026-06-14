# Design Patterns Quiz

## Multiple Choice

1. Which pattern lets behavior vary without changing the caller?
   - A. Strategy
   - B. Singleton
   - C. Comment
   - D. Primitive obsession

2. Which pattern is often overused as global state?
   - A. Builder
   - B. Singleton
   - C. Adapter
   - D. Repository

3. What is the Repository pattern usually used for?
   - A. Hiding storage details behind an interface
   - B. Formatting strings only
   - C. Starting threads
   - D. Replacing all services

## Short Answer

4. When can a factory method be helpful?
5. Why should patterns not be applied mechanically?
6. What problem does an adapter solve?

## Code Reading

7. What pattern is suggested by this interface?

```java
interface PricingStrategy {
    double priceFor(double subtotal);
}
```
