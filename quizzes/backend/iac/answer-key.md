# IaC Foundation Quiz Answer Key

## IaC Basics Quiz

1. B
2. B
3. B
4. Manual infrastructure is changed directly through dashboards or commands. IaC describes intended infrastructure in files that can be reviewed, repeated, and versioned.
5. It may create unintended resources, delete or replace important resources, expose services, increase cost, or change production unexpectedly.
6. The real environment may drift from code, and reviewers lose the chance to evaluate the change before it affects users.

## Terraform/OpenTofu Concepts Quiz

1. B
2. A
3. A
4. Terraform and OpenTofu are IaC tools with similar beginner concepts: configuration, providers, resources, variables, outputs, state, plans, and applies.
5. Committed variables are visible in Git history and can leak through state, logs, review tools, or outputs. Secrets need protected storage.
6. Variable: `runtime_port`. Output: `health_check_url_hint`.

## State, Modules, And Operations Quiz

1. B
2. B
3. A
4. Local state is stored on a developer machine; remote state is stored in a shared backend with controls such as access management and locking.
5. Good questions include: Is replacement intended? What data could be lost? Is this the correct environment? Are backups and recovery tested?
6. Benefit: environments can be configured separately and reviewed clearly. Risk: values can be copied to the wrong place or commands can be run from the wrong directory.
7. Outputs can be displayed, stored in state, or consumed by other tools. Secret values should not be exposed as ordinary outputs.

