// Runner
// Your program will have a list of 5 employees and 3 different companies in an ArrayList of a Pair of Employee and Company.   
// (e.g.  ArrayList<Pair<Employee,Company>> myList = new ….)
// Some employees will have the same company.  For example,

// John : Microsoft $110.47
// Jose : Google $1172.10
// Jennifer : Google $1172.10
// Heather : Apple $219.89
// Maria : Apple $219.89

// In an infinite loop, have 5 options.  
// Option 1 is to use a loop to display all the names and corresponding company info of the ArrayList of Pairs using the toString() of the generic pair.  
// Option 2 is to ask the user for a company name.  You can then change the stockPrice of that company.  That information would be reflected when you print the ArrayList of Pairs using Option 1.   
// Option 3 is to have the ability to add a new company. (Hint: You may want to keep a list of all the companies.)
// Option 4 is to be able to select an employee and replace the current company with another company.  (We will assume the user knows the company name.)
// Option 5 is to quit.

import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Pair<Employee,Company>> myList = new ArrayList<>();

        Employee john = new Employee("John");
        Employee jose = new Employee("Jose");
        Employee jennifer = new Employee("Jennifer");
        Employee heather = new Employee("Heather");
        Employee maria = new Employee("Maria");

        Company microsoft = new Company("Microsoft", 110.47);
        Company google = new Company("Google", 1172.10);
        Company apple = new Company("Apple", 219.89);

        ArrayList<Company> companies = new ArrayList<>();
        companies.add(microsoft);
        companies.add(google);
        companies.add(apple);

        myList.add(new Pair<>(john, microsoft));
        myList.add(new Pair<>(jose, google));
        myList.add(new Pair<>(jennifer, google));
        myList.add(new Pair<>(heather, apple));
        myList.add(new Pair<>(maria, apple));

        boolean running = true;

        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Display all");
            System.out.println("2. change stockPrice");
            System.out.println("3. add new company");
            System.out.println("4. change company");
            System.out.println("5. QUIT");
            System.out.print("Enter choice: ");
            int mainMenuChoice = sc.nextInt();
            sc.nextLine();

            if (mainMenuChoice == 1) {
                for (Pair<Employee,Company> thisOne : myList) {
                    System.out.println(thisOne);
                }
            } else if (mainMenuChoice == 2) {
                System.out.print("Enter company name: ");
                String companyName = sc.nextLine();

                System.out.print("Enter company stockPrice: ");
                double companyPrice = sc.nextDouble();

                sc.nextLine();

                for (Company company : companies) {
                    if (company.getName().equals(companyName)) {
                        company.updateStockPrice(companyPrice);
                    }
                }
            } else if (mainMenuChoice == 3) {
                System.out.print("Enter company name: ");
                String companyName = sc.nextLine();

                System.out.print("Enter company stockPrice: ");
                double companyPrice = sc.nextDouble();
                sc.nextLine();

                companies.add(new Company(companyName, companyPrice));
            } else if (mainMenuChoice == 4) {
                System.out.print("Enter user name: ");
                String userName = sc.nextLine();

                System.out.print("Enter new company name: ");
                String companyName = sc.nextLine();

                for (Pair<Employee,Company> thisOne : myList) {
                    if (thisOne.getKey().getName().equals(userName)) {
                        for (Company company : companies) {
                            if (company.getName().equals(companyName)) {
                                thisOne.setValue(company);
                            }
                        }
                    }
                }
            } else if (mainMenuChoice == 5) {
                running = !running;
            }
        }
    }
}
