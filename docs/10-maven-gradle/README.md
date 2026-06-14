# Maven First

Maven is a build tool that can compile code, run tests, package applications, and manage dependencies.

Important files and commands:

```text
pom.xml
mvn test
mvn package
mvn clean
```

Why it matters: real Java projects usually depend on libraries and automated builds.

Common mistakes:

- Editing generated `target/` files.
- Not understanding dependency scopes.
- Adding dependencies without reviewing them.
- Assuming Maven is only for large projects.

Practice prompts:

- Add JUnit 5 to a `pom.xml`.
- Run `mvn test`.
- Find where compiled classes are generated.

Gradle can be added later as a separate learning topic. Maven comes first here because it is common and beginner-friendly.

## Next Practice

- [ ] Run `mvn test`.
- [ ] Inspect generated output under `target/`, then avoid committing it.
- [ ] Use Maven while completing [Intermediate Exercises](../../exercises/intermediate/) and [Intermediate Projects](../../projects/intermediate/README.md).
