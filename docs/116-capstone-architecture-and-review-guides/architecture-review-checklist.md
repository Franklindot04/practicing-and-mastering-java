# Architecture Review Checklist

Use this checklist to inspect boundaries, contracts, ownership, consistency, and change safety.

## Boundaries

- Packages and modules reveal business boundaries.
- Public interfaces are smaller than implementation details.
- Shared-kernel records are deliberately limited.
- Data ownership is clear for source-of-truth state and projections.

## Contracts

- API request and response shapes are typed.
- Event contracts include identifiers, aggregate references, and schema compatibility rules.
- Validation failures are explicit and testable.
- Backward-compatible and incompatible changes are distinguishable.

## Consistency

- Strong consistency, eventual consistency, cache staleness, retries, idempotency, and compensation are named where they apply.
- The design avoids exactly-once claims unless it can explain the mechanism and evidence.
