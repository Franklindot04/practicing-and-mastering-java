# Consistency Decisions

The in-memory state log represents durable-style recovery decisions. The simulation favors idempotent completion and duplicate side-effect protection over exactly-once claims.
