import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class LogAnalyzerApp {
    public static void main(String[] args) {
        Path path = args.length > 0 ? Path.of(args[0]) : promptForPath();

        try {
            List<String> lines = Files.readAllLines(path);
            LogSummary summary = new LogAnalyzer().analyze(lines);
            printSummary(summary);
        } catch (IOException error) {
            System.out.println("Could not read log file: " + error.getMessage());
        }
    }

    private static Path promptForPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Log file path: ");
        return Path.of(scanner.nextLine());
    }

    private static void printSummary(LogSummary summary) {
        System.out.println("Total lines: " + summary.totalLines());
        System.out.println("Parsed lines: " + summary.parsedLines());
        System.out.println();
        System.out.println("Counts by level:");
        for (var entry : summary.levelCounts().entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
        System.out.println("Top repeated messages:");
        for (var entry : summary.topMessages()) {
            System.out.println("- " + entry.getValue() + "x " + entry.getKey());
        }
    }
}
