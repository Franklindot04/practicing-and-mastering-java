# Professional Design Pattern Examples

Design patterns are names for common design solutions. Use them when they make change safer and code easier to discuss.

## Examples

- `StrategyPatternDemo.java`: swap payment behavior without changing checkout code.
- `FactoryMethodPatternDemo.java`: create notifications without exposing construction details.
- `BuilderPatternDemo.java`: build a configuration object with readable options.
- `AdapterPatternDemo.java`: wrap an incompatible service behind the interface the app expects.
- `ObserverPatternDemo.java`: notify subscribers when an event happens.
- `SingletonPatternDemo.java`: show one shared instance, with a warning about overuse.
- `RepositoryPatternDemo.java`: separate storage operations from business code.

## Compile

```bash
javac examples/professional/design-patterns/*.java
```

## Run

```bash
java -cp examples/professional/design-patterns StrategyPatternDemo
java -cp examples/professional/design-patterns RepositoryPatternDemo
```

## When Not To Use Patterns

- Do not add a pattern only to sound professional.
- Do not hide simple code behind many tiny classes.
- Do not choose a pattern before understanding the change you expect.
- Do not use Singleton as a shortcut for global mutable state.

Patterns are most useful when they clarify a real extension point, integration boundary, or object creation problem.
