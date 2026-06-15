# Common Module And Environment Mistakes

Modules and environment folders are organization tools. They do not automatically make IaC safer.

## Module Mistakes

- Creating a module before the pattern is understood.
- Hiding important security choices behind defaults.
- Exposing too many inputs.
- Exposing outputs that leak sensitive values.
- Making one module responsible for unrelated infrastructure.
- Changing shared module behavior without checking every caller.

## Environment Mistakes

- Copying production values into dev.
- Using similar names that make the target unclear.
- Mixing real and demo infrastructure in one folder.
- Running commands from the wrong directory.
- Assuming staging and production are identical without verifying.

## Better Habits

- Keep learning examples separate from real environments.
- Use clear environment names.
- Document module inputs and outputs.
- Review plans for each environment.
- Prefer boring structure over clever abstraction.

