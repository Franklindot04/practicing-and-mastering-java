# CI/CD Basics

CI runs checks automatically when code changes.

Common CI steps for Java:

1. Check out the repository.
2. Set up the JDK.
3. Cache Maven dependencies when safe.
4. Run `mvn test`.
5. Report pass/fail status on the pull request.

## Pull Request Checks

Pull request checks give reviewers confidence that tests pass in a clean environment. They do not replace code review.

Useful checks:

- Compile.
- Unit tests.
- Project-specific test commands.
- Formatting or linting if the repo uses them.
- Secret scanning if available.

## Branch Protection And Status Checks

Branch protection can require checks to pass before merging. This keeps the default branch healthier.

Use protection carefully. A broken or overly slow check can block useful work.

## Common CI/CD Mistakes

- Tests pass locally but not in CI because environment assumptions are hidden.
- CI requires secrets for simple tests.
- Generated artifacts are committed instead of rebuilt.
- Pipelines deploy automatically before the team is ready.
- Workflow names are vague.
- Pull request checks are ignored.
