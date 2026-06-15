# Authentication And Security Basics Quiz

## Multiple Choice

1. Authentication answers which question?
   - A. What are you allowed to do?
   - B. Who are you?
   - C. Which database table is fastest?
   - D. Which Docker image is deployed?

2. Authorization failure usually maps to which status when the user is logged in?
   - A. `200 OK`
   - B. `201 Created`
   - C. `403 Forbidden`
   - D. `500 Internal Server Error`

3. What does least privilege mean?
   - A. Every user should be admin.
   - B. Give only the access needed.
   - C. Hide all validation errors.
   - D. Disable security in tests.

4. Which value must not be committed?
   - A. A real JWT secret
   - B. A public README
   - C. A sample endpoint path
   - D. A Java enum name

## Short Answer

5. Why should login errors avoid saying `email not found`?

6. What is the difference between a role and a permission?

## Design Reading

7. A user is authenticated but tries to access `/api/admin/demo` without admin role. What security concept applies?
