package dev.franklindot04.learnjava.testingpatterns;

import java.math.BigDecimal;

public record LineItem(String sku, int quantity, BigDecimal unitPrice) {
    public LineItem {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("sku is required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        if (unitPrice.signum() < 0) {
            throw new IllegalArgumentException("unit price cannot be negative");
        }
    }

    BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}

