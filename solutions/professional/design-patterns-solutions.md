# Design Patterns Solutions

## Strategy For Pricing

Put each pricing rule behind the same interface. Checkout code calls the interface and does not need to know the concrete rule.

## Factory For Notifications

Return `NotificationSender` from a factory method. Keep the `switch` or conditional creation logic out of business code.

## Repository Boundary

Define repository operations such as `save` and `findById`. The service depends on the interface, while in-memory or database implementations handle storage details.
