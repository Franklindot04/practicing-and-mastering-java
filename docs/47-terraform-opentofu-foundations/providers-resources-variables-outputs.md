# Providers, Resources, Variables, And Outputs

Terraform/OpenTofu configuration is built from a few repeated ideas.

## Providers

A provider is a plugin that knows how to talk to a platform or service. Cloud providers, Kubernetes, databases, monitoring tools, and local demo providers can all have providers.

Real providers often need credentials. Do not commit those credentials. Do not add provider-specific automation to this repository that requires secrets.

## Resources

A resource describes something the tool manages. In real environments, resources might be networks, databases, service accounts, container platforms, or application runtime settings.

In local-only learning examples, resources should stay harmless and educational. Avoid anything that creates cloud resources, costs money, or needs secrets.

## Data Sources

A data source reads information rather than managing it. For example, a real configuration might read an existing network or image ID. Data sources can still expose sensitive information, so they belong in the same review and safety model as resources.

## Variables

Variables are inputs. They make configuration reusable without hard-coding every value.

Good variables have:

- Clear names.
- Helpful descriptions.
- Reasonable types.
- Safe example values.

Variables are not automatically secret storage. Marking a variable as sensitive can reduce display in some output, but it does not make state safe to commit.

## Outputs

Outputs expose selected values after evaluation. They are useful for learning and for connecting modules, but they can also leak sensitive information if chosen carelessly.

Output only what a learner or downstream module truly needs.

