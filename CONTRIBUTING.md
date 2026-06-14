# Contributing

Thank you for helping improve this Java learning repository.

## Branch Naming

Use focused branch names:

- `docs/topic-name`
- `examples/topic-name`
- `exercises/topic-name`
- `projects/project-name`
- `tests/topic-name`
- `ci/github-actions`

## Commit Style

Use clear, small commits:

- `docs: add variables note`
- `examples: add array traversal demo`
- `exercises: add beginner loops practice`
- `test: add calculator unit tests`

## Adding Notes

- Put notes in the matching `docs/` topic folder.
- Explain why the concept matters.
- Include a small example.
- Include common mistakes.
- Include practice prompts.
- End with a "before moving on" checklist.

## Adding Exercises

- Put exercises in `exercises/beginner`, `exercises/intermediate`, or `exercises/advanced`.
- Include difficulty, concepts practiced, problem statement, examples, hints, and a stretch challenge.
- Keep beginner exercises readable and focused.

## Adding Projects

- Put projects in the correct level folder.
- Include a `README.md` with requirements, milestones, and testing ideas.
- Add starter code only when it helps learners begin without hiding the main lesson.

## Running Tests

Use Maven:

```bash
mvn test
```

## Review Expectations

Before merging a pull request:

- Check that examples compile.
- Check that explanations are beginner-friendly.
- Check that exercises have clear requirements.
- Check that no secrets or credentials are committed.
- Keep pull requests focused on one topic or project.
