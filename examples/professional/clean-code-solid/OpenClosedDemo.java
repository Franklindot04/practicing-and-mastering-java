import java.util.List;

public class OpenClosedDemo {
    public static void main(String[] args) {
        List<DiscountPolicy> policies = List.of(new StudentDiscount(), new SeasonalDiscount());

        for (DiscountPolicy policy : policies) {
            System.out.println(policy.applyTo(100.00));
        }
    }
}

interface DiscountPolicy {
    double applyTo(double price);
}

class StudentDiscount implements DiscountPolicy {
    public double applyTo(double price) {
        return price * 0.90;
    }
}

class SeasonalDiscount implements DiscountPolicy {
    public double applyTo(double price) {
        return price * 0.80;
    }
}
