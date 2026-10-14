import java.util.HashSet;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashSet<Student> students = new HashSet<Student>();

        students.add(new Student("Alice", 16));
        students.add(new Student("Brian", 17));
        students.add(new Student("Carlos", 15));
        students.add(new Student("Diana", 18));
        students.add(new Student("Emma", 14));

        int choice = 0;

        while (choice != 5) {
            System.out.println("\nStudent Menu");
            System.out.println("1. View all students");
            System.out.println("2. Add a student");
            System.out.println("3. Check for a student");
            System.out.println("4. Remove a student");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                System.out.println("\nStudents:");
                for (Student student : students) {
                    System.out.println(student);
                }
            } else if (choice == 2) {
                System.out.print("Enter the student's first name: ");
                String name = input.next();
                System.out.print("Enter the student's age: ");
                int age = input.nextInt();

                Student student = new Student(name, age);
                if (students.add(student)) {
                    System.out.println("Student added.");
                } else {
                    System.out.println("A student with that first letter and age already exists.");
                }
            } else if (choice == 3) {
                System.out.print("Enter the student's first name: ");
                String name = input.next();
                System.out.print("Enter the student's age: ");
                int age = input.nextInt();

                Student student = new Student(name, age);
                System.out.println("Student exists: " + students.contains(student));
            } else if (choice == 4) {
                System.out.print("Enter the student's first name: ");
                String name = input.next();
                System.out.print("Enter the student's age: ");
                int age = input.nextInt();

                Student student = new Student(name, age);
                if (students.remove(student)) {
                    System.out.println("Student removed.");
                } else {
                    System.out.println("Student not found.");
                }
            } else if (choice == 5) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice.");
            }
        }

        input.close();
    }
}
