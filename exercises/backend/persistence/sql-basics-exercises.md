# SQL Basics Exercises

## Exercise 1: Read Task Rows

Difficulty: Beginner

Concepts practiced: `SELECT`, `WHERE`, `ORDER BY`, `LIMIT`

Problem statement: Write SQL to list the 10 newest incomplete tasks from a `tasks` table with columns `id`, `title`, `completed`, and `created_at`.

Hints:

- Filter with `WHERE`.
- Sort newest first.
- Limit the result count.

Stretch challenge: Return only `id` and `title`.

## Exercise 2: Insert A Task

Difficulty: Beginner

Concepts practiced: `INSERT`, columns, values

Problem statement: Write SQL to insert a task titled `Practice persistence` with `completed` set to false.

Hints:

- Insert only columns the application controls.
- Let generated ids be created by the database.

Stretch challenge: Add a `description` column to the insert.

## Exercise 3: Update And Delete Safely

Difficulty: Beginner

Concepts practiced: `UPDATE`, `DELETE`, `WHERE`

Problem statement: Write SQL to mark task `5` completed, then write SQL to delete task `8`.

Hints:

- Both statements need `WHERE`.
- Think about what happens without it.

Stretch challenge: Explain why deleting data may need extra care in real systems.
