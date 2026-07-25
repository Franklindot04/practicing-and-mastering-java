# Read Models And Write Models

Read models and write models optimize for different jobs.

## Write Model

A write model protects business rules and accepts changes.

It answers questions like:

- Is this command valid?
- Who owns this entity?
- Can this state transition happen?
- What business invariant must remain true?

## Read Model

A read model presents data for lookup, reporting, screens, or API responses.

It answers questions like:

- What should this page display?
- Which fields should be searchable?
- Which summary is fast to read?
- What shape is convenient for clients?

## Example

```text
Write model:
Task
  id
  title
  completed
  validateTitle()
  markCompleted()

Read model:
TaskListItem
  id
  title
  statusLabel
  lastUpdatedDisplayText
```

## Design Rule

Do not let a convenient read shape weaken write-side business rules.
