package dev.franklindot04.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class IntermediateExpenseTrackerTest {
    @TempDir
    Path tempDir;

    @Test
    void addsListsFiltersAndTotalsExpenses() throws Exception {
        ClassLoader classLoader = compileProject();
        Class<?> trackerClass = classLoader.loadClass("ExpenseTracker");
        Object tracker = trackerClass.getConstructor().newInstance();

        Method addExpense = trackerClass.getMethod(
                "addExpense", String.class, BigDecimal.class, String.class, LocalDate.class);
        Method allExpenses = trackerClass.getMethod("allExpenses");
        Method filterByCategory = trackerClass.getMethod("filterByCategory", String.class);
        Method totalSpending = trackerClass.getMethod("totalSpending");

        addExpense.invoke(tracker, "Lunch", new BigDecimal("12.50"), "Food", LocalDate.parse("2026-06-14"));
        addExpense.invoke(tracker, "Notebook", new BigDecimal("5.25"), "School", LocalDate.parse("2026-06-15"));
        addExpense.invoke(tracker, "Coffee", new BigDecimal("3.25"), "food", LocalDate.parse("2026-06-16"));

        assertEquals(3, ((List<?>) allExpenses.invoke(tracker)).size());
        assertEquals(2, ((List<?>) filterByCategory.invoke(tracker, "FOOD")).size());
        assertEquals(new BigDecimal("21.00"), totalSpending.invoke(tracker));
    }

    @Test
    void rejectsInvalidExpenseData() throws Exception {
        ClassLoader classLoader = compileProject();
        Class<?> trackerClass = classLoader.loadClass("ExpenseTracker");
        Object tracker = trackerClass.getConstructor().newInstance();
        Method addExpense = trackerClass.getMethod(
                "addExpense", String.class, BigDecimal.class, String.class, LocalDate.class);

        InvocationTargetException error = assertThrows(
                InvocationTargetException.class,
                () -> addExpense.invoke(tracker, "Lunch", BigDecimal.ZERO, "Food", LocalDate.parse("2026-06-14")));

        assertInstanceOf(IllegalArgumentException.class, error.getCause());
        assertEquals("Amount must be greater than zero.", error.getCause().getMessage());
    }

    private ClassLoader compileProject() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Path outputDirectory = tempDir.resolve("expense-tracker-cli");
        outputDirectory.toFile().mkdirs();

        int result = compiler.run(
                null,
                null,
                null,
                "-d",
                outputDirectory.toString(),
                "projects/intermediate/expense-tracker-cli/Expense.java",
                "projects/intermediate/expense-tracker-cli/ExpenseTracker.java",
                "projects/intermediate/expense-tracker-cli/ExpenseTrackerApp.java");
        assertEquals(0, result, "Expense tracker project should compile before tests run.");

        return new URLClassLoader(new URL[] {outputDirectory.toUri().toURL()});
    }
}
