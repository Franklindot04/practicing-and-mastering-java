import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> addExpense(scanner, tracker);
                case 2 -> printExpenses(tracker.allExpenses());
                case 3 -> filterByCategory(scanner, tracker);
                case 4 -> System.out.println("Total spending: $" + tracker.totalSpending());
                case 5 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Please choose a number from 1 to 5.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add expense");
        System.out.println("2. List expenses");
        System.out.println("3. Filter by category");
        System.out.println("4. Show total spending");
        System.out.println("5. Exit");
    }

    private static void addExpense(Scanner scanner, ExpenseTracker tracker) {
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        BigDecimal amount = readAmount(scanner);
        scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        LocalDate date = readDate(scanner);

        try {
            tracker.addExpense(description, amount, category, date);
            System.out.println("Expense added.");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void filterByCategory(Scanner scanner, ExpenseTracker tracker) {
        scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();

        try {
            printExpenses(tracker.filterByCategory(category));
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void printExpenses(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        int number = 1;
        for (Expense expense : expenses) {
            System.out.println(expense.displayText(number));
            number++;
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static BigDecimal readAmount(Scanner scanner) {
        System.out.print("Amount: ");
        while (!scanner.hasNextBigDecimal()) {
            System.out.print("Please enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextBigDecimal();
    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            System.out.print("Date (YYYY-MM-DD): ");
            String text = scanner.nextLine();
            try {
                return LocalDate.parse(text);
            } catch (DateTimeParseException error) {
                System.out.println("Please enter a date like 2026-06-14.");
            }
        }
    }
}
