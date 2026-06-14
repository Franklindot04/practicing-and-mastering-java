package dev.franklindot04.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AdvancedLogAnalyzerTest {
    @TempDir
    Path tempDir;

    @Test
    void parserReturnsEntryForValidLine() throws Exception {
        ClassLoader classLoader = compileProject();
        Object parser = classLoader.loadClass("LogParser").getConstructor().newInstance();
        Method parse = parser.getClass().getMethod("parse", String.class);

        Object optional = parse.invoke(parser, "2026-06-14 09:01:30 [ERROR] Payment request failed");
        Method isPresent = optional.getClass().getMethod("isPresent");

        assertEquals(true, isPresent.invoke(optional));
    }

    @Test
    void analyzerCountsLevelsAndRepeatedMessages() throws Exception {
        ClassLoader classLoader = compileProject();
        Object analyzer = classLoader.loadClass("LogAnalyzer").getConstructor().newInstance();
        Method analyze = analyzer.getClass().getMethod("analyze", List.class);

        Object summary = analyze.invoke(analyzer, List.of(
                "2026-06-14 09:00:01 [INFO] Application started",
                "2026-06-14 09:01:30 [ERROR] Payment request failed",
                "2026-06-14 09:01:45 [ERROR] Payment request failed",
                "bad line"));

        assertEquals(4, summary.getClass().getMethod("totalLines").invoke(summary));
        assertEquals(3, summary.getClass().getMethod("parsedLines").invoke(summary));
        assertEquals(2, summary.getClass().getMethod("countForLevel", String.class).invoke(summary, "ERROR"));

        List<?> topMessages = (List<?>) summary.getClass().getMethod("topMessages").invoke(summary);
        assertTrue(topMessages.get(0).toString().contains("Payment request failed"));
    }

    private ClassLoader compileProject() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Path outputDirectory = tempDir.resolve("log-analyzer");
        outputDirectory.toFile().mkdirs();

        int result = compiler.run(
                null,
                null,
                null,
                "-d",
                outputDirectory.toString(),
                "projects/advanced/log-analyzer/LogEntry.java",
                "projects/advanced/log-analyzer/LogParser.java",
                "projects/advanced/log-analyzer/LogSummary.java",
                "projects/advanced/log-analyzer/LogAnalyzer.java",
                "projects/advanced/log-analyzer/LogAnalyzerApp.java");
        assertEquals(0, result, "Log analyzer should compile before tests run.");

        return new URLClassLoader(new URL[] {outputDirectory.toUri().toURL()});
    }
}
