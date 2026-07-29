package dev.franklindot04.learnjava.observability;

import java.util.Map;

public record ExceptionSummary(String type, String message, String rootCauseType, Map<String, String> context) {
    public ExceptionSummary {
        context = Map.copyOf(Redactor.redact(context == null ? Map.of() : context));
    }

    public static ExceptionSummary from(Throwable throwable, Map<String, String> context) {
        Throwable root = throwable;
        while (root.getCause() != null) {
            root = root.getCause();
        }
        return new ExceptionSummary(
                throwable.getClass().getSimpleName(),
                throwable.getMessage(),
                root.getClass().getSimpleName(),
                context);
    }
}
