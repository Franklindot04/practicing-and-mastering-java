import java.time.LocalDateTime;

public class LogEntry {
    private final LocalDateTime timestamp;
    private final String level;
    private final String message;

    public LogEntry(LocalDateTime timestamp, String level, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public String level() {
        return level;
    }

    public String message() {
        return message;
    }
}
