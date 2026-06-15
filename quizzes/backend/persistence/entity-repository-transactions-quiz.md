# Entity, Repository, And Transactions Quiz

## Multiple Choice

1. Why use DTOs instead of returning entities directly?
   - A. DTOs help control the public API shape.
   - B. DTOs replace the database.
   - C. DTOs automatically encrypt secrets.
   - D. DTOs run Maven tests.

2. Which layer usually validates request data before saving?
   - A. Service
   - B. Git remote
   - C. Markdown
   - D. Browser

3. What does a transaction rollback do?
   - A. Saves all pending changes.
   - B. Undoes changes from a failed unit of work.
   - C. Creates a DTO.
   - D. Starts a web server.

4. Which is a common persistence mistake?
   - A. Mapping entities to DTOs.
   - B. Keeping credentials out of source code.
   - C. Returning entities directly from controllers.
   - D. Validating request data.

## Short Answer

5. Why should real database credentials never be committed?

6. What is the repository pattern used for?

7. What is the N+1 query problem in beginner terms?

## Code Reading

8. A service method saves an order, then saves an audit entry. If the audit save fails, why might the order save need to roll back?
