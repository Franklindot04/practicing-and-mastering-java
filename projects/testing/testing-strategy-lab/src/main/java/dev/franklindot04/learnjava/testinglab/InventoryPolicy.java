package dev.franklindot04.learnjava.testinglab;

public final class InventoryPolicy {
    private final int maxPerOrder;

    public InventoryPolicy(int maxPerOrder) {
        this.maxPerOrder = maxPerOrder;
    }

    public boolean accepts(int quantity) {
        return quantity > 0 && quantity <= maxPerOrder;
    }

    public String rejectionReason(int quantity) {
        if (quantity <= 0) {
            return "quantity must be positive";
        }
        if (quantity > maxPerOrder) {
            return "quantity exceeds per-order limit";
        }
        return "accepted";
    }
}

