# Heap-Dump Cautions

Heap dumps may contain sensitive data and can be large. Generate them only in local practice, keep workloads bounded, and delete artifacts after analysis.

## Purpose

Heap dumps answer memory-retention questions: which objects are reachable, what dominates retained size, and whether expected short-lived objects are accidentally held.

## Sensitive-Data Risk

Heap dumps can contain tokens, passwords, request payloads, customer data, environment values, and internal identifiers. Treat them as sensitive artifacts. Do not commit them, attach them casually, or share them without review.

## Safe Local Practice

- Use synthetic fixtures only.
- Keep allocation and retained-object sizes bounded.
- Store dumps outside the repository or in ignored paths.
- Delete dumps after analysis.
- Record conclusions in Markdown rather than preserving the dump.

## Interpretation Limits

A heap dump is a snapshot. It can show reachability at one moment, but it does not prove growth rate or leak cause by itself. Compare snapshots, allocation evidence, GC logs, and code ownership before concluding.
