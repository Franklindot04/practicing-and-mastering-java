# Helm Basics Solutions

## Exercise 1

Raw YAML is easiest when learning Kubernetes objects because every field is visible. Helm becomes useful when the same app shape needs configurable values across environments. Templating too early can hide labels, selectors, ports, and probe details the learner does not yet understand.

## Exercise 2

A chart is a packaged application definition. A template is a Kubernetes manifest with placeholders. Values are inputs used by templates. A release is an installed instance of a chart. This repo focuses on render-only practice to avoid touching real clusters.
