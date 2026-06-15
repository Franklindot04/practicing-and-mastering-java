# State, Secrets, And Modules Solutions

## Exercise 1

Do not commit `terraform.tfstate` or `.terraform/`. State can contain sensitive or provider-returned values, and `.terraform/` is generated local tooling metadata. `notes.md` may be committed only if it is intentional and contains no secrets.

Cleanup checklist: check status, find generated IaC files, remove state and tool directories, scan for secrets, then stage only intended learning files.

## Exercise 2

Useful inputs: `application_name`, `environment_name`, `runtime_port`, `health_check_path`, `java_version`, and placeholder `container_image`.

Useful outputs: non-secret runtime summary, service name hint, and health check URL hint. Avoid secret outputs.

One reason not to create the module yet: the team may not understand the repeated pattern well enough.

## Exercise 3

Runtime port and environment name are normal variables. Database password and provider access token are secrets that belong in approved secret storage or protected environment configuration, not committed files.

`sensitive = true` can hide display in some places, but state may still contain sensitive values.

