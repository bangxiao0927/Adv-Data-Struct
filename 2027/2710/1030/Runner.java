import java.util.Scanner;

/** Runs the country and city menu using MyHashTable. */
public class Runner {
    public static void main(String[] args) {
        MyHashTable<Country, String> countries = new MyHashTable<Country, String>();
        addStartingCountries(countries);

        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMainMenu();
            System.out.print("Choose an option: ");
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                viewCountries(countries);
            } else if (choice.equals("2")) {
                selectCountry(input, countries);
            } else if (choice.equals("3")) {
                addCountry(input, countries);
            } else if (choice.equals("4")) {
                deleteCountry(input, countries);
            } else if (choice.equals("5")) {
                running = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Please choose a number from 1 to 5.");
            }
        }
        input.close();
    }

    private static void addStartingCountries(MyHashTable<Country, String> countries) {
        Country japan = new Country("Japan", "jp");
        countries.put(japan, "Tokyo");
        countries.put(japan, "Kyoto");
        countries.put(japan, "Osaka");

        Country italy = new Country("Italy", "it");
        countries.put(italy, "Rome");
        countries.put(italy, "Florence");
        countries.put(italy, "Venice");
    }

    private static void printMainMenu() {
        System.out.println("\nCountry and City Menu");
        System.out.println("1. View all countries");
        System.out.println("2. Select a country");
        System.out.println("3. Add a country");
        System.out.println("4. Delete a country");
        System.out.println("5. Quit");
    }

    private static void viewCountries(MyHashTable<Country, String> countries) {
        DLList<Country> keys = countries.keySet().toDLList();
        if (keys.size() == 0) {
            System.out.println("There are no countries in the table.");
            return;
        }

        System.out.println("Countries:");
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i));
        }
    }

    private static void selectCountry(Scanner input, MyHashTable<Country, String> countries) {
        Country country = readExistingCountry(input, countries);
        if (country == null) {
            return;
        }

        boolean running = true;
        while (running) {
            printCountryMenu(country);
            System.out.print("Choose an option: ");
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                viewCities(countries, country);
            } else if (choice.equals("2")) {
                addCity(input, countries, country);
            } else if (choice.equals("3")) {
                removeCity(input, countries, country);
            } else if (choice.equals("4")) {
                running = false;
            } else {
                System.out.println("Please choose a number from 1 to 4.");
            }
        }
    }

    private static void printCountryMenu(Country country) {
        System.out.println("\n" + country);
        System.out.println("1. View all cities");
        System.out.println("2. Add a city");
        System.out.println("3. Remove a city");
        System.out.println("4. Back to the main menu");
    }

    private static void viewCities(MyHashTable<Country, String> countries, Country country) {
        DLList<String> cities = countries.get(country);
        if (cities == null || cities.size() == 0) {
            System.out.println(country.getName() + " has no cities yet.");
            return;
        }

        System.out.println("Cities in " + country.getName() + ":");
        for (int i = 0; i < cities.size(); i++) {
            System.out.println((i + 1) + ". " + cities.get(i));
        }
    }

    private static void addCity(Scanner input, MyHashTable<Country, String> countries, Country country) {
        System.out.print("Enter the city name: ");
        String city = input.nextLine().trim();
        if (city.length() == 0) {
            System.out.println("A city name cannot be blank.");
            return;
        }

        if (hasCity(countries, country, city)) {
            System.out.println(city + " is already listed for " + country.getName() + ".");
            return;
        }

        countries.put(country, city);
        System.out.println("City added.");
    }

    private static void removeCity(Scanner input, MyHashTable<Country, String> countries, Country country) {
        System.out.print("Enter the city name: ");
        String city = input.nextLine().trim();
        if (!hasCity(countries, country, city)) {
            System.out.println(city + " is not listed for " + country.getName() + ".");
            return;
        }

        countries.remove(country, city);
        System.out.println("City removed.");
    }

    private static boolean hasCity(MyHashTable<Country, String> countries, Country country, String city) {
        DLList<String> cities = countries.get(country);
        if (cities == null) {
            return false;
        }

        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).equals(city)) {
                return true;
            }
        }
        return false;
    }

    private static void addCountry(Scanner input, MyHashTable<Country, String> countries) {
        System.out.print("Enter the country name: ");
        String name = input.nextLine().trim();
        if (name.length() == 0) {
            System.out.println("A country name cannot be blank.");
            return;
        }

        System.out.print("Enter a two-letter abbreviation (aa-zz): ");
        String abbreviation = input.nextLine().trim().toLowerCase();
        if (!isValidAbbreviation(abbreviation)) {
            System.out.println("Enter exactly two letters, like jp or it.");
            return;
        }
        if (findCountry(countries, abbreviation) != null) {
            System.out.println("That abbreviation is already used.");
            return;
        }

        System.out.print("Enter the first city: ");
        String city = input.nextLine().trim();
        if (city.length() == 0) {
            System.out.println("A city name cannot be blank.");
            return;
        }

        Country country = new Country(name, abbreviation);
        countries.put(country, city);
        System.out.println("Country added: " + country);
    }

    private static void deleteCountry(Scanner input, MyHashTable<Country, String> countries) {
        Country country = readExistingCountry(input, countries);
        if (country == null) {
            return;
        }

        countries.remove(country);
        System.out.println("Country removed: " + country);
    }

    private static Country readExistingCountry(Scanner input, MyHashTable<Country, String> countries) {
        System.out.print("Enter a two-letter country abbreviation: ");
        String abbreviation = input.nextLine().trim().toLowerCase();

        Country country = findCountry(countries, abbreviation);
        if (country == null) {
            System.out.println("No country uses that abbreviation.");
        }
        return country;
    }

    // The country is the key, so its abbreviation has to find the Country object first.
    private static Country findCountry(MyHashTable<Country, String> countries, String abbreviation) {
        DLList<Country> keys = countries.keySet().toDLList();
        for (int i = 0; i < keys.size(); i++) {
            Country country = keys.get(i);
            if (country.getAbbreviation().equals(abbreviation)) {
                return country;
            }
        }
        return null;
    }

    private static boolean isValidAbbreviation(String abbreviation) {
        if (abbreviation.length() != 2) {
            return false;
        }

        for (int i = 0; i < abbreviation.length(); i++) {
            char letter = abbreviation.charAt(i);
            if (letter < 'a' || letter > 'z') {
                return false;
            }
        }
        return true;
    }
}
