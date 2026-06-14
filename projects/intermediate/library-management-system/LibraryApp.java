import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryCatalog catalog = new LibraryCatalog();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> addBook(scanner, catalog);
                case 2 -> printBooks(catalog.allBooks());
                case 3 -> searchBooks(scanner, catalog);
                case 4 -> borrowBook(scanner, catalog);
                case 5 -> returnBook(scanner, catalog);
                case 6 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Please choose a number from 1 to 6.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add book");
        System.out.println("2. List books");
        System.out.println("3. Search books");
        System.out.println("4. Borrow book");
        System.out.println("5. Return book");
        System.out.println("6. Exit");
    }

    private static void addBook(Scanner scanner, LibraryCatalog catalog) {
        scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();

        try {
            Book book = catalog.addBook(title, author);
            System.out.println("Book added with id " + book.id() + ".");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void searchBooks(Scanner scanner, LibraryCatalog catalog) {
        scanner.nextLine();
        System.out.print("Search title or author: ");
        String text = scanner.nextLine();

        try {
            printBooks(catalog.search(text));
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void borrowBook(Scanner scanner, LibraryCatalog catalog) {
        int id = readInt(scanner, "Book id to borrow: ");
        try {
            catalog.borrowBook(id);
            System.out.println("Book borrowed.");
        } catch (IllegalArgumentException | IllegalStateException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void returnBook(Scanner scanner, LibraryCatalog catalog) {
        int id = readInt(scanner, "Book id to return: ");
        try {
            catalog.returnBook(id);
            System.out.println("Book returned.");
        } catch (IllegalArgumentException | IllegalStateException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {
            System.out.println(book.displayText());
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
}
