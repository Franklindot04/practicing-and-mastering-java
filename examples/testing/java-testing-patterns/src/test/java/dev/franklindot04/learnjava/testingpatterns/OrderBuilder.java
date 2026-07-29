package dev.franklindot04.learnjava.testingpatterns;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

final class OrderBuilder {
    private String customerId = "customer-1";
    private boolean preferredCustomer;
    private final List<LineItem> items = new ArrayList<>(List.of(new LineItem("book", 1, new BigDecimal("10.00"))));

    static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    OrderBuilder forPreferredCustomer() {
        this.preferredCustomer = true;
        return this;
    }

    OrderBuilder withLine(String sku, int quantity, String unitPrice) {
        this.items.add(new LineItem(sku, quantity, new BigDecimal(unitPrice)));
        return this;
    }

    OrderBuilder withoutDefaultItems() {
        this.items.clear();
        return this;
    }

    OrderDraft buildDraft() {
        return new OrderDraft(customerId, preferredCustomer, items);
    }
}

