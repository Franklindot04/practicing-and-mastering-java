# Configuration And Profiles Quiz

## Multiple Choice

1. What is the safest place for a real production database password?
   - A. Hard-coded in a Java class
   - B. Committed in `application-prod.properties`
   - C. Supplied outside source control through approved runtime configuration
   - D. Printed in startup logs

2. Which value is usually safe to commit for a learning project?
   - A. Real API token
   - B. Local H2 database URL
   - C. Private key
   - D. Real JWT signing secret

3. What is the main purpose of a Spring profile?
   - A. To delete tests
   - B. To load environment-specific settings
   - C. To replace controllers
   - D. To encrypt source code

## Short Answer

4. Why can a silent fallback secret be dangerous outside local development?

5. Name two differences between local and test configuration.

## Design Reading

6. A project has `spring.datasource.password=real-password` committed in `application-prod.properties`. What should change before review?
