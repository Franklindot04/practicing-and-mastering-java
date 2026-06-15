# Declarative Infrastructure And Drift

Most Terraform/OpenTofu-style IaC is declarative. You describe the desired result instead of writing every step needed to reach it.

## Declarative Vs Imperative

An imperative instruction says how to do something:

```text
Create a runtime.
Open this port.
Add this environment variable.
Restart the app.
```

A declarative configuration says what should be true:

```text
The backend runtime should have one application instance, port 8080, and named environment settings.
```

The IaC tool decides what actions are needed to move from the current state toward the desired state.

## Desired State

Desired state is the intended infrastructure described by the configuration files. The tool compares desired state with what it knows from state and provider APIs.

This comparison is powerful, but it is not magic. If the configuration is wrong, the desired state can still be wrong. Reviews and plans matter.

## Drift

Drift happens when real infrastructure no longer matches the IaC configuration or recorded state. Common causes include:

- Someone changed a setting manually in a dashboard.
- An emergency fix was not copied back into code.
- A provider changed defaults.
- A resource was modified or removed outside the IaC workflow.

Drift does not always mean someone did something careless. It means the team needs a way to detect, discuss, and reconcile differences safely.

## Why Beginners Should Care

Drift is one reason IaC belongs in a review workflow. A plan can reveal that a tool wants to recreate or remove something surprising. Learners should practice reading proposed changes before trusting them.

