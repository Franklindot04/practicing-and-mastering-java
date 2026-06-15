# HTTP And REST Quiz

## Multiple Choice

1. Which HTTP method is usually best for reading a resource without changing server state?
   - A. `GET`
   - B. `POST`
   - C. `PATCH`
   - D. `DELETE`

2. Which status code best fits a successful resource creation?
   - A. `200 OK`
   - B. `201 Created`
   - C. `204 No Content`
   - D. `404 Not Found`

3. Which path is more REST-friendly?
   - A. `/createTask`
   - B. `/getAllTasks`
   - C. `/tasks`
   - D. `/deleteTaskById`

4. What should `404 Not Found` usually mean?
   - A. The server crashed.
   - B. The request body is invalid.
   - C. The requested resource does not exist.
   - D. The request succeeded without a body.

## Short Answer

5. What is the difference between a path variable and a query parameter?

6. Why should `GET` requests avoid changing server state?

## Code Reading

7. A client sends `PUT /tasks/8` with a JSON body containing a full replacement task. What resource is being changed, and why is `PUT` a reasonable method?
