package dev.franklindot04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void greetingReturnsPracticeMessage() {
        assertEquals("Keep practicing Java.", App.greeting());
    }
}
