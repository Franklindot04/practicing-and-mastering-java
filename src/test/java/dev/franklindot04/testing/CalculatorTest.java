package dev.franklindot04.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    void divideReturnsQuotient() {
        assertEquals(5, Calculator.divide(10, 2));
    }

    @Test
    void divideRejectsZeroDivisor() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.divide(10, 0));
    }

    private static class Calculator {
        static int divide(int dividend, int divisor) {
            if (divisor == 0) {
                throw new IllegalArgumentException("Divisor cannot be zero");
            }
            return dividend / divisor;
        }
    }
}
