import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        SLList<Student> students = new SLList<Student>();
        students.add(new Student("Alex", 1001));
        students.add(new Student("Bella", 1002));
        students.add(new Student("Carlos", 1003));
        students.add(new Student("Diana", 1004));
        students.add(new Student("Ethan", 1005));

        Scanner keyboard = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            displayMenu();
            choice = readInt(keyboard, "Choose an option: ");

            if (choice == 1) {
                System.out.println(students.toString());
            } else if (choice == 2) {
                addStudent(keyboard, students);
            } else if (choice == 3) {
                removeAtIndex(keyboard, students);
            } else if (choice == 4) {
                removeByID(keyboard, students);
            } else if (choice == 5) {
                sortByName(students);
                System.out.println("The students were sorted by name.");
            } else if (choice == 6) {
                scramble(students);
                System.out.println("The students were scrambled.");
            } else if (choice != 7) {
                System.out.println("Please choose a number from 1 through 7.");
            }
        }

        System.out.println("Goodbye!");
        keyboard.close();
    }

    private static void displayMenu() {
        System.out.println("\n1. Display list");
        System.out.println("2. Add a student at an index");
        System.out.println("3. Remove a student at an index");
        System.out.println("4. Remove a student by ID");
        System.out.println("5. Sort by student name");
        System.out.println("6. Scramble");
        System.out.println("7. Quit");
    }

    private static void addStudent(Scanner keyboard,
                                   SLList<Student> students) {
        int index = readInt(keyboard, "Enter an index from 0 to "
                + students.size() + ": ");

        if (index < 0 || index > students.size()) {
            System.out.println("That index is not valid.");
            return;
        }

        System.out.print("Enter the student's name: ");
        String name = keyboard.nextLine();
        int id = readInt(keyboard, "Enter the student's ID: ");
        Student newStudent = new Student(name, id);

        if (students.contains(newStudent)) {
            System.out.println("A student with that ID already exists.");
        } else {
            students.add(index, newStudent);
            System.out.println("Student added.");
        }
    }

    private static void removeAtIndex(Scanner keyboard,
                                      SLList<Student> students) {
        if (students.size() == 0) {
            System.out.println("The list is empty.");
            return;
        }

        int index = readInt(keyboard, "Enter an index from 0 to "
                + (students.size() - 1) + ": ");

        if (index < 0 || index >= students.size()) {
            System.out.println("That index is not valid.");
        } else {
            Student removed = students.remove(index);
            System.out.println("Removed: " + removed);
        }
    }

    private static void removeByID(Scanner keyboard,
                                   SLList<Student> students) {
        int id = readInt(keyboard, "Enter the ID to remove: ");

        // Student.equals compares IDs, so the name can be anything.
        Student studentToRemove = new Student("", id);
        if (students.remove(studentToRemove)) {
            System.out.println("Student removed.");
        } else {
            System.out.println("No student has that ID.");
        }
    }

    private static void sortByName(SLList<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            int smallest = i;

            for (int j = i + 1; j < students.size(); j++) {
                String currentName = students.get(j).getName();
                String smallestName = students.get(smallest).getName();

                if (currentName.compareToIgnoreCase(smallestName) < 0) {
                    smallest = j;
                }
            }

            Student temp = students.get(i);
            students.set(i, students.get(smallest));
            students.set(smallest, temp);
        }
    }

    private static void scramble(SLList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            int randomIndex = (int) (Math.random() * students.size());
            Student temp = students.get(i);
            students.set(i, students.get(randomIndex));
            students.set(randomIndex, temp);
        }
    }

    private static int readInt(Scanner keyboard, String message) {
        System.out.print(message);
        while (!keyboard.hasNextInt()) {
            System.out.print("Please enter an integer: ");
            keyboard.nextLine();
        }

        int number = keyboard.nextInt();
        keyboard.nextLine();
        return number;
    }
}
