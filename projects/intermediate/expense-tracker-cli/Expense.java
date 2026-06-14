import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private final String description;
    private final BigDecimal amount;
    private final String category;
    private final LocalDate date;

    public Expense(String description, BigDecimal amount, String category, LocalDate date) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be blank.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date is required.");
        }

        this.description = description.trim();
        this.amount = amount;
        this.category = category.trim();
        this.date = date;
    }

    public String description() {
        return description;
    }

    public BigDecimal amount() {
        return amount;
    }

    public String category() {
        return category;
    }

    public LocalDate date() {
        return date;
    }

    public String displayText(int number) {
        return number + ". " + date + " | " + category + " | $" + amount + " | " + description;
    }
}
