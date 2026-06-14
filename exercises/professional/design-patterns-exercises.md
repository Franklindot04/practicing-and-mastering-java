# Design Patterns Exercises

## Exercise 1: Strategy For Pricing

Difficulty: Professional foundation

Concepts practiced: Strategy pattern, polymorphism

Problem statement: implement different pricing rules for regular, student, and seasonal discounts without changing checkout logic.

Hints:

- Create a `PricingStrategy` interface.
- Put each rule in its own class.

Stretch challenge: choose the strategy from user input.

## Exercise 2: Factory For Notifications

Difficulty: Professional foundation

Concepts practiced: Factory method, object creation

Problem statement: create a factory that returns email or SMS notification senders.

Hints:

- Return an interface type.
- Keep construction details in one place.

Stretch challenge: reject unknown notification types with a clear exception.

## Exercise 3: Repository Boundary

Difficulty: Professional foundation

Concepts practiced: Repository pattern, architecture boundaries

Problem statement: define a repository interface for storing customers and create an in-memory implementation.

Hints:

- Use `Optional<Customer>` for lookup.
- Keep storage details out of service code.

Stretch challenge: add a second fake implementation for tests.
