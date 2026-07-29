package dev.franklindot04.learnjava.testinglab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InventoryPolicyTest {
    @ParameterizedTest
    @CsvSource({
            "0, false, quantity must be positive",
            "1, true, accepted",
            "5, true, accepted",
            "6, false, quantity exceeds per-order limit"
    })
    void classifiesBoundaryQuantities(int quantity, boolean accepted, String reason) {
        InventoryPolicy policy = new InventoryPolicy(5);

        assertEquals(accepted, policy.accepts(quantity));
        assertEquals(reason, policy.rejectionReason(quantity));
    }
}

