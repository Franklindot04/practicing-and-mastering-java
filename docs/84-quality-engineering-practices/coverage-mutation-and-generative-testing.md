# Coverage, Mutation, And Generative Testing

Coverage describes what code was exercised. It does not describe whether assertions were meaningful.

## Coverage Types

| Type | What it measures | Limitation |
| --- | --- | --- |
| Line coverage | Which lines ran. | A line can run without any useful assertion. |
| Branch coverage | Which decision outcomes ran. | Conditions inside a branch may still be weakly checked. |
| Condition coverage | Whether boolean subexpressions were exercised. | More detailed, but still not proof of correctness. |

Coverage is a signal. Low coverage can reveal untested areas. High coverage can still miss wrong requirements, missing assertions, and integration defects.

## Mutation Testing

Mutation testing changes code in small ways and checks whether tests fail. A surviving mutant means the test suite did not notice a changed behavior.

Example mutants:

- `>` changed to `>=`.
- `+` changed to `-`.
- A conditional negated.
- A return value replaced with a default.

Surviving mutants are prompts for investigation. Some reveal weak tests. Some are equivalent behavior. Some are not worth fixing because the risk is low.

## Property-Based And Generative Testing

Property-based testing checks invariants across generated inputs.

Example properties:

- Sorting keeps the same elements.
- Reserving inventory never makes available stock negative.
- Applying a discount never increases a price.
- Serializing then deserializing a value preserves important fields.

Use generated tests carefully:

- Make failures reproducible with seeds.
- Keep invariants simple and meaningful.
- Print the smallest failing case when possible.
- Combine generated checks with example-based tests.

## Metamorphic And Fuzz Testing Concepts

Metamorphic testing checks how output should change when input changes. For example, adding an item to an order should not reduce the subtotal unless a documented discount applies.

Fuzz testing sends many unusual inputs to discover crashes, parser failures, or validation gaps. It is especially useful around parsing and boundary handling, but it still needs safe limits and reproducible failures.

