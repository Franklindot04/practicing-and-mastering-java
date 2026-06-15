# State And Secrets Safety

IaC for a Java backend may reference sensitive values even when the source files look clean.

## State

State can contain provider-returned values, generated identifiers, connection details, or values derived from resources. Keep state outside Git and protect it with approved access controls.

## Secrets

Secrets should come from approved secret storage or environment-specific configuration. Do not put real credentials into `.tf`, `.tfvars`, Markdown, shell history, or issue comments.

## Review Questions

- Could this change expose a service publicly?
- Could this change replace or delete a database?
- Could this output reveal a secret?
- Does this environment use least privilege?
- Are demo values clearly separated from real infrastructure?

