# Chart Values Exercises

## Exercise 1: File Identification

Difficulty: Beginner

Concepts practiced: Chart.yaml, values.yaml, templates

Problem statement: Match each file to its purpose: `Chart.yaml`, `values.yaml`, `templates/deployment.yaml`, `templates/_helpers.tpl`.

Hints:
- Metadata, defaults, rendered object, reusable snippets.

Stretch challenge: Explain where a placeholder image tag belongs.

## Exercise 2: Template Mapping

Difficulty: Beginner

Concepts practiced: values-to-template mapping

Problem statement: A template uses `{{ .Values.image.repository }}:{{ .Values.image.tag }}`. Which values should you set for a local-only Java backend?

Hints:
- Use placeholders.
- Avoid real registry credentials.

Stretch challenge: Explain why `latest` can be confusing.
