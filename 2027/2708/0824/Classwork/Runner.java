import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Item<Food> item1 = new Item<Food>(new Food("burger"), 5.99);
        Item<Food> item2 = new Item<Food>(new Food("bread"), 2.50);
        Item<Food> item3 = new Item<Food>(new Food("soup"), 3.99);
        Item<Food> item4 = new Item<Food>(new Food("salad"), 4.50);
        Item<Food> item5 = new Item<Food>(new Food("pizza"), 8.99);

        Item<Toy> item6 = new Item<Toy>(new Toy("doll", 3), 12.99);
        Item<Toy> item7 = new Item<Toy>(new Toy("figure", 5), 9.99);
        Item<Toy> item8 = new Item<Toy>(new Toy("book", 2), 6.50);
        Item<Toy> item9 = new Item<Toy>(new Toy("lego", 7), 29.99);
        Item<Toy> item10 = new Item<Toy>(new Toy("blocks", 1), 15.00);

        ArrayList<Item<Food>> foods = new ArrayList<>();
        foods.add(item1);
        foods.add(item2);
        foods.add(item3);
        foods.add(item4);
        foods.add(item5);

        // Print initial food list
        for (int i = 0; i < foods.size(); i++){
            System.out.print(foods.get(i).getItem() + " ");
        }
        System.out.println();

        // Add toys to list
        ArrayList<Item<Toy>> toys = new ArrayList<>();
        toys.add(item6);
        toys.add(item7);
        toys.add(item8);
        toys.add(item9);
        toys.add(item10);

        // Print initial toy list
        for (int j = 0; j < toys.size(); j++){
            System.out.print(toys.get(j).getItem() + " ");
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main Program Loop
        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Select Food");
            System.out.println("2. Select Toys");
            System.out.println("3. Quit");
            System.out.print("Enter choice: ");
            int mainMenuChoice = scanner.nextInt();

            if (mainMenuChoice == 1) {
                // FOOD SUB-MENU
                System.out.println("\n--- Food List ---");
                for (int i = 0; i < foods.size(); i++){
                    System.out.print(foods.get(i).getItem() + " ");
                }
                boolean inSubMenu = true;
                while (inSubMenu) {
                    System.out.println("\nFood Options");
                    System.out.println("1. Display List again");
                    System.out.println("2. Return to Main Menu");
                    System.out.println("3. Quit");
                    System.out.print("Enter choice: ");
                    int subMenuChoice = scanner.nextInt();

                    if (subMenuChoice == 1) {
                        System.out.println("Food List");
                        System.out.println(foods);
                    } else if (subMenuChoice == 2) {
                        inSubMenu = false; // Goes back to main menu
                    } else if (subMenuChoice == 3) {
                        inSubMenu = false;
                        running = false; // Exits program
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            } else if (mainMenuChoice == 2) {
                // TOY SUB-MENU
                System.out.println("\n--- Toy List ---");
                for (int j = 0; j < toys.size(); j++){
                    System.out.print(toys.get(j).getItem() + " ");
                }
                boolean inSubMenu = true;
                while (inSubMenu) {
                    System.out.println("\n--- Toy Options ---");
                    System.out.println("1. Display List again");
                    System.out.println("2. Return to Main Menu");
                    System.out.println("3. Quit");
                    System.out.print("Enter choice: ");
                    int subMenuChoice = scanner.nextInt();

                    if (subMenuChoice == 1) {
                        System.out.println("\n--- Toy List ---");
                        for (int j = 0; j < toys.size(); j++){
                            System.out.print(toys.get(j).getItem() + " ");
                        }
                    } else if (subMenuChoice == 2) {
                        inSubMenu = false; // Goes back to main menu
                    } else if (subMenuChoice == 3) {
                        inSubMenu = false;
                        running = false; // Exits program
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            } else if (mainMenuChoice == 3) {
                running = false;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
