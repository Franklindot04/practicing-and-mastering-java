public class StrategyPatternDemo {
    public static void main(String[] args) {
        Checkout checkout = new Checkout(new CardPayment());
        checkout.pay(49.99);
    }
}

interface PaymentStrategy {
    void pay(double amount);
}

class CardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid by card: $" + amount);
    }
}

class Checkout {
    private final PaymentStrategy paymentStrategy;

    Checkout(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}
