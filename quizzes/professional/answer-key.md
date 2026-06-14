# Professional Java Quiz Answer Key

## Clean Code And SOLID Quiz

1. B. A method that mixes unrelated responsibilities is a good extraction candidate.
2. A. SRP says a class should have one clear reason to change.
3. B. Dependency inversion encourages depending on abstractions.
4. Names reduce the need to mentally decode intent.
5. Too many tiny methods can scatter logic and make flow harder to follow.
6. Tests can pass fake dependencies instead of using real external systems.
7. Dependency inversion.

## Design Patterns Quiz

1. A. Strategy lets behavior vary behind a shared interface.
2. B. Singleton is often overused as global state.
3. A. Repository hides storage details behind an interface.
4. It helps when object creation has branching rules or setup details.
5. Mechanical pattern use adds complexity without solving a real problem.
6. An adapter wraps an incompatible API in the shape the app expects.
7. Strategy.

## Reflection, Networking, And JDBC Quiz

1. C. `RUNTIME` retention allows runtime reflection.
2. A. Networks can hang or respond slowly.
3. B. `PreparedStatement` binds values instead of concatenating them into SQL.
4. Reflection is powerful but harder to trace and refactor, so keeping it isolated limits risk.
5. Environment variables, deployment configuration, or a secret manager.
6. DNS failure, timeout, refused connection, partial response, or unavailable service.
7. It concatenates user input into SQL, which can allow SQL injection.
