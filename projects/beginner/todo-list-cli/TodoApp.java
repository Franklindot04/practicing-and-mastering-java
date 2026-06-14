import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> addTask(scanner, taskList);
                case 2 -> listTasks(taskList);
                case 3 -> markComplete(scanner, taskList);
                case 4 -> deleteTask(scanner, taskList);
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
        System.out.println("1. Add task");
        System.out.println("2. List tasks");
        System.out.println("3. Mark task complete");
        System.out.println("4. Delete task");
        System.out.println("5. Exit");
    }

    private static void addTask(Scanner scanner, TaskList taskList) {
        System.out.print("Task description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        try {
            taskList.add(description);
            System.out.println("Task added.");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void listTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }

        int number = 1;
        for (Task task : taskList.all()) {
            System.out.println(task.displayText(number));
            number++;
        }
    }

    private static void markComplete(Scanner scanner, TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to complete.");
            return;
        }

        listTasks(taskList);
        int taskNumber = readInt(scanner, "Task number to complete: ");

        try {
            taskList.markComplete(taskNumber);
            System.out.println("Task marked complete.");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void deleteTask(Scanner scanner, TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }

        listTasks(taskList);
        int taskNumber = readInt(scanner, "Task number to delete: ");

        try {
            taskList.delete(taskNumber);
            System.out.println("Task deleted.");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
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
