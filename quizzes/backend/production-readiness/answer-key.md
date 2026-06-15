# Production Readiness Quiz Answer Key

## Configuration And Profiles

1. C
2. B
3. B
4. A silent fallback secret can let a production-like environment start with a weak, shared, or known value instead of failing clearly.
5. Local configuration may enable local conveniences such as H2 console. Test configuration should be isolated, repeatable, and friendly to automated runs.
6. Remove the real password from source control, rotate it if exposed, and provide production credentials through approved external configuration.

## Logging And Observability

1. A
2. C
3. B
4. Monitoring asks whether something is wrong; observability helps explain why and where to investigate.
5. Request IDs connect related logs from the same request.
6. It exposes sensitive operational details and may leak credentials or infrastructure information.

## Docker And CI/CD

1. B
2. A
3. B
4. Early CI should prove build and tests are reliable before adding deployment risk and credentials.
5. Examples include committing generated artifacts, requiring secrets for basic tests, ignoring failing checks, unclear workflow names, and deploying before review.
6. Pull requests should not deploy automatically to production, and tests should not require production credentials. Split tests from deployment and use safe environments.
