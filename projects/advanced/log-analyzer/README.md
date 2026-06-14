# Log Analyzer

A command-line log analyzer that reads a simple log file, counts log levels, tracks total lines, and reports repeated messages.

Expected log format:

```text
2026-06-14 09:01:30 [ERROR] Payment request failed
```

## Concepts Practiced

- File handling with `Path` and `Files`
- Parsing text into model objects
- `Optional` for parse failures
- Maps and frequency counting
- Sorting map entries
- Separating CLI, parser, and analyzer logic
- Graceful handling of unreadable files

## Files

```text
LogEntry.java
LogParser.java
LogAnalyzer.java
LogSummary.java
LogAnalyzerApp.java
sample.log
```

## Compile

From the repository root:

```bash
javac projects/advanced/log-analyzer/LogEntry.java projects/advanced/log-analyzer/LogParser.java projects/advanced/log-analyzer/LogSummary.java projects/advanced/log-analyzer/LogAnalyzer.java projects/advanced/log-analyzer/LogAnalyzerApp.java
```

## Run

```bash
java -cp projects/advanced/log-analyzer LogAnalyzerApp projects/advanced/log-analyzer/sample.log
```

If no path is provided, the program prompts for one.

## Example Output

```text
Total lines: 8
Parsed lines: 7

Counts by level:
- INFO: 2
- DEBUG: 1
- WARN: 2
- ERROR: 2

Top repeated messages:
- 2x Payment request failed
- 2x Slow response from payment service
```

## Possible Improvements

- Process very large files line by line instead of reading all lines at once.
- Filter by date range or level.
- Export the summary to a report file.
- Support more log formats.
- Track error bursts over time.
