# IaC Overview

Infrastructure as Code is the practice of managing infrastructure with files instead of only manual steps. Those files can describe pieces such as compute runtime, network rules, databases, storage, container platforms, monitoring settings, or deployment dependencies.

## Why IaC Exists

Manual infrastructure work is easy to start but hard to repeat. Two learners can follow the same checklist and still create slightly different environments. A team can also forget why a setting changed, who changed it, or whether production still matches documentation.

IaC helps because infrastructure changes become:

- Visible in version control.
- Reviewable through pull requests.
- Repeatable across environments.
- Easier to compare against a known desired state.
- Easier to discuss before money, security, or availability is affected.

## Manual Infrastructure Vs IaC

Manual infrastructure often looks like clicking through an admin console, copying values into a form, and writing notes afterward. IaC reverses that flow: the intended change is written first, reviewed, planned, and then applied by a tool.

Manual work is still sometimes necessary, especially when learning or recovering from incidents. The goal of IaC is not to pretend humans are absent. The goal is to make important infrastructure decisions explicit and traceable.

## Beginner Mental Model

Think of IaC as a map plus a change request:

- The files describe what should exist.
- The tool compares the files with known state.
- A plan describes what would change.
- A reviewed apply would make the change in a real environment.

In this repository, examples stop at safe learning and validation. Do not use them to create real infrastructure.

