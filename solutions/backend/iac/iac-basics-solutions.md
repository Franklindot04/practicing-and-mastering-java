# IaC Basics Solutions

## Exercise 1

Manual dashboard changes can be hard to review, hard to repeat, and easy to forget. A pull request description could say: "Change the task API runtime port from the old placeholder to `8080` in the dev environment configuration, then review the generated plan before any apply."

A useful reviewer question: "Does this affect only the intended environment?"

## Exercise 2

Desired state example: "The task API runtime should be named `task-api-local`, run in the `local` learning environment, listen on port `8080`, and expose `/actuator/health` for health checks."

An IaC tool could compare the configured port with known state or provider data and show a plan if the real value differs.

