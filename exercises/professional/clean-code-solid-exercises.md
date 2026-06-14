# Clean Code And SOLID Exercises

## Exercise 1: Extract A Long Method

Difficulty: Professional foundation

Concepts practiced: method extraction, naming, readability

Problem statement: take a method that validates input, calculates a total, and prints output. Split it into small methods with clear names.

Hints:

- Extract validation first.
- Extract calculation second.
- Keep output separate from business logic.

Stretch challenge: add tests for the calculation method.

## Exercise 2: Single Responsibility Refactor

Difficulty: Professional foundation

Concepts practiced: SRP, class boundaries

Problem statement: split a class that reads orders, calculates totals, and writes reports into separate collaborators.

Hints:

- Name each class by its responsibility.
- Avoid moving every line into a tiny class.

Stretch challenge: make the calculation class testable without file IO.

## Exercise 3: Dependency Inversion

Difficulty: Professional foundation

Concepts practiced: interfaces, dependency injection

Problem statement: refactor a service that directly creates an email sender so it accepts a `MessageSender` interface.

Hints:

- Pass dependencies through the constructor.
- Keep the interface small.

Stretch challenge: add a fake sender for tests.
