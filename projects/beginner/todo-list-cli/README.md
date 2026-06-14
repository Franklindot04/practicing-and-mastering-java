# To-Do List CLI

A beginner-friendly command-line to-do list app that stores tasks in memory while the program runs.

## Concepts Practiced

- Classes and objects
- `ArrayList`
- Console input with `Scanner`
- Loops and conditionals
- Simple menu design
- Basic validation

## Files

```text
TodoApp.java
Task.java
TaskList.java
```

## Compile

From the repository root:

```bash
javac projects/beginner/todo-list-cli/Task.java projects/beginner/todo-list-cli/TaskList.java projects/beginner/todo-list-cli/TodoApp.java
```

## Run

```bash
java -cp projects/beginner/todo-list-cli TodoApp
```

## Example Usage

```text
1. Add task
2. List tasks
3. Mark task complete
4. Delete task
5. Exit
Enter choice: 1
Task description: Study Java arrays
Task added.
```

## Possible Improvements

- Save tasks to a file.
- Add due dates.
- Add priorities.
- Add search and filtering.
- Add automated tests after moving the code into a Maven source package.
