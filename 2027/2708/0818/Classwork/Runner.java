import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    private static class ToDoTask {
        private final String name;
        private final int rank;

        ToDoTask(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
    }

    public static void main(String[] args) {
        ArrayList<ToDoTask> tasks = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nToDo List Menu");
            System.out.println("1. Add a task");
            System.out.println("2. Delete a task");
            System.out.println("3. Display the list");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            if (choice.equals("1")) {
                addTask(tasks, input);
                displayTasks(tasks);
            } else if (choice.equals("2")) {
                deleteTask(tasks, input);
                displayTasks(tasks);
            } else if (choice.equals("3")) {
                displayTasks(tasks);
            } else if (choice.equals("4")) {
                System.out.println("Goodbye!");
                input.close();
                break;
            } else {
                System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
            }
        }
    }

    private static void addTask(ArrayList<ToDoTask> tasks, Scanner input) {
        System.out.print("Enter the task name: ");
        String name = input.nextLine();

        if (name.isEmpty()) {
            System.out.println("A task name cannot be empty.");
            return;
        }

        int rank;
        while (true) {
            System.out.print("Enter the rank (1 or greater): ");
            try {
                rank = Integer.parseInt(input.nextLine());
                if (rank >= 1) {
                    break;
                }
            } catch (NumberFormatException ignored) {
                // The error message below handles non-integer input.
            }
            System.out.println("Invalid rank. Rank must be an integer starting from 1.");
        }

        tasks.add(new ToDoTask(name, rank));
        sortTasks(tasks);
        System.out.println("Task added.");
    }

    private static void sortTasks(ArrayList<ToDoTask> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = 0; j < tasks.size() - 1 - i; j++) {
                if (tasks.get(j).rank > tasks.get(j + 1).rank) {
                    ToDoTask temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }
    }

    private static void deleteTask(ArrayList<ToDoTask> tasks, Scanner input) {
        System.out.print("Enter the name of the task to delete: ");
        String name = input.nextLine();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).name.equals(name)) {
                tasks.remove(i);
                System.out.println("Task deleted.");
                return;
            }
        }

        System.out.println("Task not found.");
    }

    private static void displayTasks(ArrayList<ToDoTask> tasks) {
        System.out.println("\nCurrent ToDo List");
        if (tasks.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        System.out.printf("%-6s%s%n", "Rank", "Task");
        for (ToDoTask task : tasks) {
            System.out.printf("%-6d%s%n", task.rank, task.name);
        }
    }
}
