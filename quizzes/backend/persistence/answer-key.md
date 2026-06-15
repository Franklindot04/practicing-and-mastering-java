# Backend Persistence Quiz Answer Key

## SQL And Database Quiz

1. B
2. A
3. B
4. B
5. A row is one record. A column is one named field that appears on rows in a table.
6. A foreign key connects a row to a row in another table.
7. It likely represents many comments belonging to one task.

## JPA And Spring Data Quiz

1. A
2. A
3. B
4. B
5. Spring Data JPA generates common repository behavior from interfaces, including methods such as `save`, `findById`, and `findAll`.
6. Lazy loading waits to load related data until code needs it.
7. Long derived query names can hide complex database logic and become hard to read, test, and change.

## Entity, Repository, And Transactions Quiz

1. A
2. A
3. B
4. C
5. Committed credentials can leak access to real systems and are hard to rotate safely after exposure.
6. The repository pattern hides storage details behind a clear save/find/delete boundary.
7. It happens when one query loads a list and then extra queries load related data for each item.
8. The order and audit entry describe one workflow. Rolling back prevents the system from saving only half of the intended work.
