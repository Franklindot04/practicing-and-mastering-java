# Configuration And Profiles Solutions

## Exercise 1

Safe defaults are reasonable for app name, port, and demo mode:

- `APP_NAME` can default to `task-api`.
- `APP_PORT` can default to `8080`.
- `APP_DEMO_MODE` can default to `true` for local learning.

A secret-like value such as `APP_SIGNING_SECRET` should not silently fall back in production-like runs. The app should fail startup with a clear message if that value is required and missing.

## Exercise 2

One reasonable split:

- `application.properties`: application name, default profile, safe management endpoint exposure.
- `application-local.properties`: local H2 URL, local logging level, H2 console if needed.
- `application-test.properties`: isolated H2 database and quieter test settings.

Do not commit real production database URLs or credentials.

## Exercise 3

Using `local-demo-secret` can be acceptable only for clearly labeled local demos. It is dangerous in staging or production because the app may start with weak, shared, or known signing material. A good startup rule is: "If the active profile is not local and signing is enabled, require `APP_SIGNING_SECRET` to be present and long enough."
