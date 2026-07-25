package dev.franklindot04.learnjava.backend.messaging;

public record ConsumerResult(boolean processed, ErrorClassification errorClassification, String detail) {
    public static ConsumerResult success() {
        return new ConsumerResult(true, null, "processed");
    }

    public static ConsumerResult failure(ErrorClassification classification, String detail) {
        return new ConsumerResult(false, classification, detail);
    }
}
