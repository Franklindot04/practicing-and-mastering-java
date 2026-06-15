# Terraform/OpenTofu Concepts Solutions

## Exercise 1

- Cloud platform plugin: provider.
- Application port input: variable.
- Runtime service: resource.
- Generated service name: output.

A database password should not be a normal output because outputs can appear in command output, logs, state, or downstream tooling.

## Exercise 2

Reasonable variables:

- `application_name`: string, example `task-api`.
- `runtime_port`: number, example `8080`.
- `health_check_path`: string, example `/actuator/health`.

A plain-English validation rule: the runtime port should be a positive number in the allowed application port range.

