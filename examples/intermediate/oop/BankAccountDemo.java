public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Franklin", 100.00);
        account.deposit(50.00);
        account.withdraw(30.00);

        System.out.println(account.owner() + " balance: " + account.balance());
    }
}

class BankAccount {
    private final String owner;
    private double balance;

    BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.owner = owner;
        this.balance = openingBalance;
    }

    void deposit(double amount) {
        requirePositive(amount);
        balance += amount;
    }

    void withdraw(double amount) {
        requirePositive(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    String owner() {
        return owner;
    }

    double balance() {
        return balance;
    }

    private void requirePositive(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
