# Saga Solutions

## Exercise 1

A task import saga might create an import record, reserve processing capacity, parse the file, store task drafts, publish a completion event, and mark complete. Compensation might release capacity, delete draft records, mark the import failed, or flag manual review.

## Exercise 2

Orchestration is often easier for this beginner workflow because the import coordinator owns state transitions and compensation decisions. Choreography may work later, but it requires stronger event tracing and ownership rules.

