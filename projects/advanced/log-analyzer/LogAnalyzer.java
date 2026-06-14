import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private final LogParser parser = new LogParser();

    public LogSummary analyze(List<String> lines) {
        Map<String, Integer> levelCounts = new LinkedHashMap<>();
        Map<String, Integer> messageCounts = new LinkedHashMap<>();
        int parsedLines = 0;

        for (String line : lines) {
            var parsed = parser.parse(line);
            if (parsed.isEmpty()) {
                continue;
            }

            LogEntry entry = parsed.get();
            parsedLines++;
            levelCounts.merge(entry.level(), 1, Integer::sum);
            messageCounts.merge(entry.message(), 1, Integer::sum);
        }

        List<Map.Entry<String, Integer>> topMessages = new ArrayList<>(messageCounts.entrySet());
        topMessages.sort(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()));

        if (topMessages.size() > 5) {
            topMessages = topMessages.subList(0, 5);
        }

        return new LogSummary(lines.size(), parsedLines, levelCounts, topMessages);
    }
}
