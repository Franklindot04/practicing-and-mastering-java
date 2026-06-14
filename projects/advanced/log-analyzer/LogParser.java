import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class LogParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Optional<LogEntry> parse(String line) {
        if (line == null || line.length() < 22) {
            return Optional.empty();
        }

        try {
            LocalDateTime timestamp = LocalDateTime.parse(line.substring(0, 19), FORMATTER);
            int levelStart = line.indexOf('[', 20);
            int levelEnd = line.indexOf(']', levelStart + 1);

            if (levelStart == -1 || levelEnd == -1 || levelEnd + 2 > line.length()) {
                return Optional.empty();
            }

            String level = line.substring(levelStart + 1, levelEnd).trim().toUpperCase();
            String message = line.substring(levelEnd + 1).trim();

            if (level.isBlank() || message.isBlank()) {
                return Optional.empty();
            }

            return Optional.of(new LogEntry(timestamp, level, message));
        } catch (DateTimeParseException error) {
            return Optional.empty();
        }
    }
}
