# To-Do List CLI Solution Walkthrough

## What The Project Does

The To-Do List CLI lets a learner manage tasks from the command line. The user can add tasks, list tasks, mark tasks as complete, and delete tasks. The tasks are stored in memory, so they disappear when the program ends.

## Main Java Concepts Used

- Classes and objects
- Encapsulation with private fields
- `ArrayList` for a growable list
- Console menus with `Scanner`
- Loops and `switch` expressions
- Basic input validation

## Important Classes And Methods

- `Task` stores one task description and whether it is complete.
- `TaskList` manages the collection of tasks.
- `TodoApp` handles the menu and user input.
- `add` validates and stores a new task.
- `markComplete` marks a numbered task as done.
- `delete` removes a numbered task.
- `displayText` formats one task for the menu output.

## Step-By-Step Logic

1. Create a `TaskList` to store tasks.
2. Show the menu inside a loop.
3. Read the user's menu choice.
4. For add, read a description and store it as a new `Task`.
5. For list, print each task with a number and completion mark.
6. For complete, ask for a task number and mark that task complete.
7. For delete, ask for a task number and remove that task.
8. For exit, stop the loop.

## Common Beginner Mistakes

- Forgetting that users count from 1 while lists use indexes starting at 0.
- Allowing blank tasks into the list.
- Returning the wrong task when converting a task number to an index.
- Trying to mark or delete a task when the list is empty.
- Mixing too much list logic directly into the menu class.

## Possible Improvements

- Save tasks to a file so they remain after the program closes.
- Add due dates or priorities.
- Let the user edit an existing task.
- Add search or filtering.
- Add tests for task numbering and validation rules.

## Reflection Questions

1. Why does `TaskList` convert task numbers to list indexes in one helper method?
2. What should happen if the user tries to delete task number 99?
3. Why is it useful for `Task` to decide how its display text looks?
4. What would need to change before tasks could be saved to a file?
