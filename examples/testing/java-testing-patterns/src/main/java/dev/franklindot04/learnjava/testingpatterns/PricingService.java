package dev.franklindot04.learnjava.testingpatterns;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PricingService {
    public BigDecimal total(OrderDraft draft, DiscountPolicy discountPolicy) {
        BigDecimal subtotal = draft.items().stream()
                .map(LineItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discount = discountPolicy.discountFor(draft);
        BigDecimal total = subtotal.subtract(discount);
        if (total.signum() < 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}

