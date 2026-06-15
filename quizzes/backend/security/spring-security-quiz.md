# Spring Security Quiz

## Multiple Choice

1. What does `SecurityFilterChain` configure?
   - A. HTTP security rules
   - B. SQL table indexes
   - C. Git remotes
   - D. Maven project names

2. What does `PasswordEncoder.matches` do?
   - A. Compares raw input with an encoded password
   - B. Deletes a user
   - C. Creates an endpoint
   - D. Disables authentication

3. Which annotation can protect a method by role?
   - A. `@PreAuthorize`
   - B. `@Entity`
   - C. `@Column`
   - D. `@RequestBody`

4. Which route should usually be public in a simple API demo?
   - A. `/api/health`
   - B. `/api/admin/demo`
   - C. `/api/tasks`
   - D. `/api/users/all-passwords`

## Short Answer

5. What does `UserDetailsService` do?

6. Why is HTTP Basic acceptable for a small learning demo but not the end of production security design?

## Design Reading

7. A security config has `anyRequest().permitAll()` during development and is accidentally shipped. What is the risk?
