import java.util.Scanner;

public class Runner {
    private static MyHashTable<Country, String> countries = new MyHashTable<Country, String>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Two countries you have visited or want to visit, each with 3 cities.
        Country usa = new Country("United States", "us");
        countries.put(usa, "New York");
        countries.put(usa, "Los Angeles");
        countries.put(usa, "Chicago");

        Country japan = new Country("Japan", "jp");
        countries.put(japan, "Tokyo");
        countries.put(japan, "Kyoto");
        countries.put(japan, "Osaka");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Country Cities ---");
            System.out.println("1. Display all countries and abbreviations");
            System.out.println("2. Select a country by abbreviation");
            System.out.println("3. Add a new country");
            System.out.println("4. Delete a country");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                showAllCountries();
            } else if (choice.equals("2")) {
                selectCountry();
            } else if (choice.equals("3")) {
                addCountry();
            } else if (choice.equals("4")) {
                deleteCountry();
            } else if (choice.equals("5")) {
                running = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
        sc.close();
    }

    private static void showAllCountries() {
        MyHashSet<Country> keys = countries.keySet();
        if (keys.size() == 0) {
            System.out.println("No countries yet.");
        } else {
            System.out.println("Countries:");
            System.out.print(keys.toString());
        }
    }

    private static void selectCountry() {
        System.out.print("Enter country abbreviation (2 lowercase letters): ");
        String abbr = sc.nextLine().trim();
        Country key = new Country("PLACEHOLDER", abbr);
        DLList<String> cities = countries.get(key);
        if (cities == null || cities.size() == 0) {
            System.out.println("Country not found.");
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n--- Country: " + abbr.toUpperCase() + " ---");
            System.out.println("1. Display all cities");
            System.out.println("2. Add a city");
            System.out.println("3. Remove a city");
            System.out.println("4. Back to main menu");
            System.out.print("Enter choice: ");
            String c = sc.nextLine().trim();

            if (c.equals("1")) {
                System.out.println("Cities:");
                for (int i = 0; i < cities.size(); i++) {
                    System.out.println("- " + cities.get(i));
                }
            } else if (c.equals("2")) {
                System.out.print("Enter city name: ");
                String city = sc.nextLine().trim();
                countries.put(key, city);
                System.out.println("Added " + city);
            } else if (c.equals("3")) {
                System.out.print("Enter city name to remove: ");
                String city = sc.nextLine().trim();
                countries.remove(key, city);
                System.out.println("Removed " + city + " (if it existed).");
            } else if (c.equals("4")) {
                back = true;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static void addCountry() {
        System.out.print("Enter country name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter abbreviation (2 lowercase letters): ");
        String abbr = sc.nextLine().trim();
        System.out.print("Enter a city: ");
        String city = sc.nextLine().trim();
        Country c = new Country(name, abbr);
        countries.put(c, city);
        System.out.println("Added " + name + " with city " + city);
    }

    private static void deleteCountry() {
        System.out.print("Enter abbreviation of country to delete: ");
        String abbr = sc.nextLine().trim();
        Country key = new Country("PLACEHOLDER", abbr);
        countries.remove(key);
        System.out.println("Deleted country with abbreviation " + abbr + " (if it existed).");
    }
}