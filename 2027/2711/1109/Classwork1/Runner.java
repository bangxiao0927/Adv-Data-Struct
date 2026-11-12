import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

// To-Do list. The list is saved to tasks.dat after every add,
// and it is loaded again when the program starts.
public class Runner {
    private static final String FILE_NAME = "tasks.dat";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DLList<String> tasks = loadTasks();

        boolean running = true;
        while (running) {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add a task");
            System.out.println("2. View all tasks");
            System.out.println("3. Quit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter a task: ");
                String task = sc.nextLine().trim();
                tasks.add(task);
                saveTasks(tasks);
                System.out.println("Added: " + task);
            } else if (choice.equals("2")) {
                viewTasks(tasks);
            } else if (choice.equals("3")) {
                running = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
        sc.close();
    }

    // Loads the saved list if the file exists. Otherwise it makes a new empty list.
    @SuppressWarnings("unchecked")
    private static DLList<String> loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new DLList<String>();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            DLList<String> tasks = (DLList<String>) in.readObject();
            in.close();
            return tasks;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Could not load " + FILE_NAME + ". Starting with an empty list.");
            return new DLList<String>();
        }
    }

    private static void saveTasks(DLList<String> tasks) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(tasks);
            out.close();
        } catch (IOException ex) {
            System.out.println("Could not save " + FILE_NAME);
        }
    }

    private static void viewTasks(DLList<String> tasks) {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
