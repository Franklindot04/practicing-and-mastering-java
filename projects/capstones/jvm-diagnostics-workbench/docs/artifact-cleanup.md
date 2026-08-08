# Artifact Cleanup

Remove `target/`, JFR files, heap dumps, thread dumps, GC logs, benchmark outputs, profiler recordings, and temporary notes before committing.

## Why Cleanup Matters

Diagnostic artifacts can be large, noisy, machine-specific, and sensitive. They also make repository history harder to review. The repository should keep instructions and conclusions, not generated evidence files.

## Cleanup Checklist

- Maven `target/` directories.
- `*.jfr` recordings.
- `*.hprof`, `*.dump`, or heap snapshots.
- Thread dump text files.
- GC logs and runtime logs.
- Benchmark outputs and profiler exports.
- Temporary scratch notes that include local paths or sensitive values.

## Review Commands

Before committing, scan for common artifacts with `find` and check `git status --short`. If an artifact is needed for local analysis, keep it outside the repository or in a clearly ignored temporary directory.

## Production Comparison

Production incident artifacts should follow retention, access, and redaction policies. Local cleanup in this workbench is a simplified version of that discipline.
