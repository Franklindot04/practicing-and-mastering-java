import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readMenuChoice(scanner);

            if (choice == 5) {
                running = false;
                System.out.println("Goodbye!");
                continue;
            }

            double first = readNumber(scanner, "Enter first number: ");
            double second = readNumber(scanner, "Enter second number: ");

            try {
                double result = calculate(calculator, choice, first, second);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Choose an operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static int readMenuChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number from 1 to 5: ");
            scanner.next();
        }
        int choice = scanner.nextInt();

        while (choice < 1 || choice > 5) {
            System.out.print("Please enter a number from 1 to 5: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number from 1 to 5: ");
                scanner.next();
            }
            choice = scanner.nextInt();
        }

        return choice;
    }

    private static double readNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);

        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }

        return scanner.nextDouble();
    }

    private static double calculate(Calculator calculator, int choice, double first, double second) {
        return switch (choice) {
            case 1 -> calculator.add(first, second);
            case 2 -> calculator.subtract(first, second);
            case 3 -> calculator.multiply(first, second);
            case 4 -> calculator.divide(first, second);
            default -> throw new IllegalArgumentException("Unknown operation.");
        };
    }
}
