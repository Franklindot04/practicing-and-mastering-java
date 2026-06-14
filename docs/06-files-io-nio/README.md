# Files, IO, And NIO

Java can read and write files with `java.io` and modern `java.nio.file` APIs.

```java
Path path = Path.of("notes.txt");
Files.writeString(path, "Java practice");
String text = Files.readString(path);
```

Why it matters: many real programs load configuration, process data files, generate reports, and write logs.

Common mistakes:

- Hard-coding machine-specific paths.
- Ignoring character encoding.
- Forgetting to close streams.
- Reading huge files into memory without thinking.

Practice prompts:

- Write text to a file.
- Read a file line by line.
- Count words in a file.
- Copy a file with `Files.copy`.

Before moving on, you should understand `Path`, `Files`, reading strings, writing strings, and line-based processing.
