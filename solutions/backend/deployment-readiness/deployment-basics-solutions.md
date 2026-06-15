# Deployment Basics Solutions

## Exercise 1

A reasonable flow is: run tests, choose version, build artifact, review runtime configuration, deploy, call health endpoint, run smoke tests, watch logs, then decide continue or rollback.

## Exercise 2

Possible names:

- `SERVER_PORT` or `APP_PORT`
- `SPRING_PROFILES_ACTIVE`
- `APP_RELEASE_VERSION`
- `APP_COMMIT_SHA`

The version endpoint can show release version, commit SHA, app name, and active profile if those values are safe.

## Exercise 3

A JAR answers "what executable app did we build?" A container image answers "what packaged runtime should run?" Release notes answer "what changed and how do we verify or roll back?" Generated artifacts are rebuilt from source and should not be committed.
