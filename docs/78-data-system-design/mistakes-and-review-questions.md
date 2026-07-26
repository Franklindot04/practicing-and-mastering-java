# Data Design Mistakes And Review Questions

Data mistakes are expensive because old data outlives old code.

## Common Mistakes

- Choosing storage before naming access patterns.
- Sharing one database across services without ownership rules.
- Splitting databases before the domain boundary is clear.
- Adding indexes without understanding write cost.
- Assuming replicas are always current.
- Treating eventual consistency as invisible to users.
- Creating dual writes without a recovery plan.
- Storing sensitive data where a reference would work.
- Designing migrations that require all clients to update at once.
- Keeping data forever without retention reasoning.

## Decision Table

| Question | If Yes | If No |
| --- | --- | --- |
| Does the write require local transactional integrity? | Keep the write inside one consistency boundary | Consider async integration or separate ownership |
| Can reads be stale? | Read models, replicas, or caches may fit | Prefer strongly consistent reads for that path |
| Is the key distribution even? | Partitioning may be simpler | Plan hot-key mitigation |
| Does data cross privacy boundaries? | Minimize and audit fields | Keep the design simpler |
| Will schema change while clients are live? | Use expand-and-contract | A simpler migration may be enough |

## ASCII Review Flow

```text
Requirement
  |
  v
Data owner known? -- no --> clarify ownership
  |
 yes
  v
Query patterns known? -- no --> list reads and writes
  |
 yes
  v
Consistency needed? -> choose transaction, replica, event, or read model strategy
  |
  v
Lifecycle known? -> retention, backup, migration, privacy review
```

## Review Questions

- Which component owns each important data fact?
- Which write must be atomic?
- Which reads can be stale?
- Which queries need indexes?
- Which partition key could become hot?
- Which data should not appear in events or logs?
- What is the rollback plan for a schema change?
- How would a read model be rebuilt?
- What happens if a dual write only half succeeds?
- Which data can be deleted, archived, or anonymized?
