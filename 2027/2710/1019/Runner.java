import java.util.HashMap;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        HashMap<Integer, Student> studentMap = new HashMap<Integer, Student>();
        Scanner input = new Scanner(System.in);

        // Starting students. Jordan Lee appears twice with different IDs.
        studentMap.put(1001, new Student("Jordan Lee", "jordan.lee@example.com", "LAHS"));
        studentMap.put(1002, new Student("Taylor Kim", "taylor.kim@example.com", "AVHS"));
        studentMap.put(1003, new Student("Jordan Lee", "jlee2@example.com", "MVHS"));
        studentMap.put(1004, new Student("Morgan Patel", "morgan.patel@example.com", "LAHS"));
        studentMap.put(1005, new Student("Casey Rivera", "casey.rivera@example.com", "AVHS"));

        boolean running = true;

        while (running) {
            System.out.println("\n1. View All Students");
            System.out.println("2. Add a new student");
            System.out.println("3. Check if an ID exists");
            System.out.println("4. Display student info by ID");
            System.out.println("5. Remove a student by ID");
            System.out.println("6. Search for students by name");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt(input);

            if (choice == 1) {
                viewAllStudents(studentMap);
            } else if (choice == 2) {
                addStudent(input, studentMap);
            } else if (choice == 3) {
                System.out.print("Enter the ID to check: ");
                int id = readInt(input);

                if (studentMap.containsKey(id)) {
                    System.out.println("That ID exists.");
                } else {
                    System.out.println("That ID does not exist.");
                }
            } else if (choice == 4) {
                System.out.print("Enter the ID: ");
                int id = readInt(input);

                if (studentMap.containsKey(id)) {
                    Student student = studentMap.get(id);
                    printStudent(id, student);
                } else {
                    System.out.println("No student has that ID.");
                }
            } else if (choice == 5) {
                System.out.print("Enter the ID to remove: ");
                int id = readInt(input);

                if (studentMap.containsKey(id)) {
                    studentMap.remove(id);
                    System.out.println("Student removed.");
                } else {
                    System.out.println("No student has that ID.");
                }
            } else if (choice == 6) {
                System.out.print("Enter the name to search for: ");
                String name = input.nextLine();
                boolean found = false;

                for (int id : studentMap.keySet()) {
                    Student student = studentMap.get(id);

                    if (student.getName().equalsIgnoreCase(name)) {
                        printStudent(id, student);
                        found = true;
                    }
                }

                if (found == false) {
                    System.out.println("No students found with that name.");
                }
            } else if (choice == 0) {
                running = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Please choose a number from 0 to 6.");
            }
        }

        input.close();
    }

    public static void viewAllStudents(HashMap<Integer, Student> studentMap) {
        if (studentMap.size() == 0) {
            System.out.println("There are no students.");
        } else {
            for (int id : studentMap.keySet()) {
                Student student = studentMap.get(id);
                printStudent(id, student);
            }
        }
    }

    public static void addStudent(Scanner input, HashMap<Integer, Student> studentMap) {
        System.out.print("Enter the new student's ID: ");
        int id = readInt(input);

        System.out.print("Enter the student's name: ");
        String name = input.nextLine();

        System.out.print("Enter the student's email: ");
        String email = input.nextLine();

        String school = "";
        boolean validSchool = false;

        while (validSchool == false) {
            System.out.print("Enter the school (LAHS, AVHS, or MVHS): ");
            school = input.nextLine().toUpperCase();

            if (school.equals("LAHS") || school.equals("AVHS") || school.equals("MVHS")) {
                validSchool = true;
            } else {
                System.out.println("Please enter LAHS, AVHS, or MVHS.");
            }
        }

        boolean alreadyExists = studentMap.containsKey(id);
        studentMap.put(id, new Student(name, email, school));

        if (alreadyExists) {
            System.out.println("That ID already existed, so its student was replaced.");
        } else {
            System.out.println("Student added.");
        }
    }

    public static void printStudent(int id, Student student) {
        System.out.println("ID: " + id);
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("School: " + student.getSchool());
        System.out.println();
    }

    public static int readInt(Scanner input) {
        while (true) {
            String line = input.nextLine();

            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a whole number: ");
            }
        }
    }
}
