# Idempotency Design

Workflow submission idempotency maps a caller key to a stable workflow id. Completion idempotency stores side-effect keys so repeated completion messages do not create extra effects.
