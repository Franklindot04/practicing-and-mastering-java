import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private final List<Expense> expenses = new ArrayList<>();

    public void addExpense(String description, BigDecimal amount, String category, LocalDate date) {
        expenses.add(new Expense(description, amount, category, date));
    }

    public List<Expense> allExpenses() {
        return List.copyOf(expenses);
    }

    public List<Expense> filterByCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be blank.");
        }

        List<Expense> matches = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.category().equalsIgnoreCase(category.trim())) {
                matches.add(expense);
            }
        }
        return matches;
    }

    public BigDecimal totalSpending() {
        BigDecimal total = BigDecimal.ZERO;
        for (Expense expense : expenses) {
            total = total.add(expense.amount());
        }
        return total;
    }

    public boolean isEmpty() {
        return expenses.isEmpty();
    }
}
