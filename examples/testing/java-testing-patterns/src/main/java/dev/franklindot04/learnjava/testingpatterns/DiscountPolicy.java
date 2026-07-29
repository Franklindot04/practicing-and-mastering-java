package dev.franklindot04.learnjava.testingpatterns;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal discountFor(OrderDraft draft);
}

