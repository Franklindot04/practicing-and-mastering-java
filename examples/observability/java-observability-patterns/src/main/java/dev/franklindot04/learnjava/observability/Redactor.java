package dev.franklindot04.learnjava.observability;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public final class Redactor {
    private Redactor() {
    }

    public static Map<String, String> redact(Map<String, String> input) {
        Map<String, String> output = new LinkedHashMap<>();
        input.forEach((key, value) -> output.put(key, isSensitive(key) ? "[REDACTED]" : String.valueOf(value)));
        return Map.copyOf(output);
    }

    public static boolean isSensitive(String key) {
        String normalized = key == null ? "" : key.toLowerCase(Locale.ROOT);
        return normalized.contains("password")
                || normalized.contains("token")
                || normalized.contains("authorization")
                || normalized.contains("secret");
    }
}
