# API Contracts

Public contracts are represented by records on `CommercePlatform`: `Cart`, `CartLine`, `CheckoutResult`, `PaymentResult`, `Order`, `OperationalReport`, `FitnessReport`, and `ReconciliationReport`.

Validation rejects blank identifiers, missing carts, inactive catalogue entries, non-positive prices, negative stock, and invalid quantities.
