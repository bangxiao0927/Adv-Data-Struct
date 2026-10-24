import java.util.Scanner;

/** Runs the state-information menu using MyHashMap. */
public class Runner {
    public static void main(String[] args) {
        MyHashMap<State, Info> states = new MyHashMap<State, Info>();
        addStartingStates(states);

        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                viewAbbreviations(states);
            } else if (choice.equals("2")) {
                viewStateInfo(input, states);
            } else if (choice.equals("3")) {
                addState(input, states);
            } else if (choice.equals("4")) {
                removeState(input, states);
            } else if (choice.equals("5")) {
                running = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Please choose a number from 1 to 5.");
            }
        }
        input.close();
    }

    private static void addStartingStates(MyHashMap<State, Info> states) {
        states.put(new State("CA"), new Info("California", "Sacramento", 39_538_223));
        states.put(new State("FL"), new Info("Florida", "Tallahassee", 21_538_187));
        states.put(new State("NY"), new Info("New York", "Albany", 20_201_249));
        states.put(new State("TX"), new Info("Texas", "Austin", 29_145_505));
        states.put(new State("WA"), new Info("Washington", "Olympia", 7_705_281));
    }

    private static void printMenu() {
        System.out.println("\nState Information Menu");
        System.out.println("1. View all state abbreviations");
        System.out.println("2. View state information");
        System.out.println("3. Add a state");
        System.out.println("4. Remove a state");
        System.out.println("5. Quit");
    }

    private static void viewAbbreviations(MyHashMap<State, Info> states) {
        DLList<State> abbreviations = states.keySet().toDLList();
        if (abbreviations.size() == 0) {
            System.out.println("There are no states in the map.");
            return;
        }

        System.out.println("State abbreviations:");
        for (int i = 0; i < abbreviations.size(); i++) {
            System.out.println(abbreviations.get(i));
        }
    }

    private static void viewStateInfo(Scanner input, MyHashMap<State, Info> states) {
        State state = readState(input);
        Info info = states.get(state);
        if (info == null) {
            System.out.println("That state is not in the map.");
        } else {
            System.out.println(info);
        }
    }

    private static void addState(Scanner input, MyHashMap<State, Info> states) {
        State state = readState(input);
        System.out.print("Enter the full state name: ");
        String name = input.nextLine().trim();
        System.out.print("Enter the capital city: ");
        String capital = input.nextLine().trim();
        long population = readPopulation(input);

        Info previous = states.put(state, new Info(name, capital, population));
        if (previous == null) {
            System.out.println("State added.");
        } else {
            System.out.println("State already existed, so its information was replaced.");
        }
    }

    private static void removeState(Scanner input, MyHashMap<State, Info> states) {
        State state = readState(input);
        Info removed = states.remove(state);
        if (removed == null) {
            System.out.println("That state is not in the map.");
        } else {
            System.out.println("State removed: " + removed.getName());
        }
    }

    private static State readState(Scanner input) {
        while (true) {
            System.out.print("Enter a two-letter state abbreviation: ");
            try {
                return new State(input.nextLine());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static long readPopulation(Scanner input) {
        while (true) {
            System.out.print("Enter the population: ");
            String response = input.nextLine().replace(",", "").trim();
            try {
                long population = Long.parseLong(response);
                if (population >= 0) {
                    return population;
                }
            } catch (NumberFormatException exception) {
                // The message after the catch explains what to enter.
            }
            System.out.println("Enter a non-negative whole number for population.");
        }
    }
}
