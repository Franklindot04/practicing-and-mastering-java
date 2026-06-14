import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogSummary {
    private final int totalLines;
    private final int parsedLines;
    private final Map<String, Integer> levelCounts;
    private final List<Map.Entry<String, Integer>> topMessages;

    public LogSummary(
            int totalLines,
            int parsedLines,
            Map<String, Integer> levelCounts,
            List<Map.Entry<String, Integer>> topMessages) {
        this.totalLines = totalLines;
        this.parsedLines = parsedLines;
        this.levelCounts = new LinkedHashMap<>(levelCounts);
        this.topMessages = List.copyOf(topMessages);
    }

    public int totalLines() {
        return totalLines;
    }

    public int parsedLines() {
        return parsedLines;
    }

    public Map<String, Integer> levelCounts() {
        return Map.copyOf(levelCounts);
    }

    public List<Map.Entry<String, Integer>> topMessages() {
        return topMessages;
    }

    public int countForLevel(String level) {
        return levelCounts.getOrDefault(level.toUpperCase(), 0);
    }
}
