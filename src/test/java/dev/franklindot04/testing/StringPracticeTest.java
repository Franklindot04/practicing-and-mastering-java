package dev.franklindot04.testing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StringPracticeTest {
    @Test
    void blankStringsAreRejected() {
        assertTrue(isBlank(""));
        assertTrue(isBlank("   "));
        assertFalse(isBlank("Java"));
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
