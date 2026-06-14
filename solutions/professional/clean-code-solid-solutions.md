# Clean Code And SOLID Solutions

## Extract A Long Method

Separate validation, calculation, and output. The calculation method should accept simple inputs and return a value so it can be tested directly.

## Single Responsibility Refactor

Use classes such as `OrderReader`, `OrderCalculator`, and `ReportWriter`. Each class should have one clear reason to change.

## Dependency Inversion

Create a `MessageSender` interface and inject it through the constructor. The service should not decide whether email, SMS, or a fake sender is used.
