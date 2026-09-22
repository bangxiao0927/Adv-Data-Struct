import java.util.Scanner;
import java.util.Comparator;

public class Runner {
    private static DLList<Song> playlist = new DLList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Start with 5 songs (at least 2 by the same artist)
        playlist.add(new Song("The Beatles", "Hey Jude"));
        playlist.add(new Song("Queen", "Bohemian Rhapsody"));
        playlist.add(new Song("The Beatles", "Let It Be"));
        playlist.add(new Song("Led Zeppelin", "Stairway to Heaven"));
        playlist.add(new Song("Pink Floyd", "Comfortably Numb"));

        while (true) {
            printMenu();
            int choice = getInt("Enter your choice: ");

            switch (choice) {
                case 1: addSong(); break;
                case 2: displayList(); break;
                case 3: deleteByArtistAndName(); break;
                case 4: deleteByNumber(); break;
                case 5: deleteByArtist(); break;
                case 6: deleteByName(); break;
                case 7: sortByArtist(); break;
                case 8: sortBySongName(); break;
                case 9: searchByArtist(); break;
                case 10: clearList(); break;
                case 11:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== Song List Manager ==========");
        System.out.println("1. Add a new song");
        System.out.println("2. Display song list");
        System.out.println("3. Delete a song (artist + name)");
        System.out.println("4. Delete a song by number");
        System.out.println("5. Delete songs by artist");
        System.out.println("6. Delete songs by name");
        System.out.println("7. Sort by artist name");
        System.out.println("8. Sort by song name");
        System.out.println("9. Search by artist");
        System.out.println("10. Clear the list");
        System.out.println("11. Quit");
        System.out.println("=======================================");
    }

    private static void addSong() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine().trim();
        System.out.print("Enter song name: ");
        String name = scanner.nextLine().trim();
        playlist.add(new Song(artist, name));
        System.out.println("Song added.");
    }

    private static void displayList() {
        if (playlist.isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }
        System.out.println("Current Playlist:");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ". " + playlist.get(i));
        }
    }

    private static void deleteByArtistAndName() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine().trim();
        System.out.print("Enter song name: ");
        String name = scanner.nextLine().trim();
        Song target = new Song(artist, name);
        if (playlist.remove(target)) {
            System.out.println("Song deleted.");
        } else {
            System.out.println("Song not found.");
        }
    }

    private static void deleteByNumber() {
        if (playlist.isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }
        displayList();
        int num = getInt("Enter the number of the song to delete: ");
        if (num < 1 || num > playlist.size()) {
            System.out.println("Invalid number.");
            return;
        }
        Song removed = playlist.remove(num - 1);
        System.out.println("Deleted: " + removed);
    }

    private static void deleteByArtist() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine().trim();
        int count = 0;
        // Remove from the end to keep indices stable
        for (int i = playlist.size() - 1; i >= 0; i--) {
            if (playlist.get(i).getArtist().equalsIgnoreCase(artist)) {
                playlist.remove(i);
                count++;
            }
        }
        System.out.println(count + " song(s) by \"" + artist + "\" deleted.");
    }

    private static void deleteByName() {
        System.out.print("Enter song name: ");
        String name = scanner.nextLine().trim();
        int count = 0;
        for (int i = playlist.size() - 1; i >= 0; i--) {
            if (playlist.get(i).getName().equalsIgnoreCase(name)) {
                playlist.remove(i);
                count++;
            }
        }
        System.out.println(count + " song(s) named \"" + name + "\" deleted.");
    }

    private static void sortByArtist() {
        playlist.sort(Comparator.comparing(Song::getArtist, String.CASE_INSENSITIVE_ORDER)
                                .thenComparing(Song::getName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Playlist sorted by artist name.");
        displayList();
    }

    private static void sortBySongName() {
        playlist.sort(Comparator.comparing(Song::getName, String.CASE_INSENSITIVE_ORDER)
                                .thenComparing(Song::getArtist, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Playlist sorted by song name.");
        displayList();
    }

    private static void searchByArtist() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine().trim();
        System.out.println("Songs by \"" + artist + "\":");
        int count = 0;
        for (int i = 0; i < playlist.size(); i++) {
            Song s = playlist.get(i);
            if (s.getArtist().equalsIgnoreCase(artist)) {
                count++;
                System.out.println(count + ". " + s);
            }
        }
        if (count == 0) {
            System.out.println("No songs found for that artist.");
        }
    }

    private static void clearList() {
        playlist.clear();
        System.out.println("Playlist cleared.");
    }

    private static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}