import java.util.Scanner;

public class Runner {

    private static DLList<Song> playlist = new DLList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Start with 5 songs - at least two share the same artist
        playlist.add(new Song("The Beatles", "Hey Jude"));
        playlist.add(new Song("Queen", "Bohemian Rhapsody"));
        playlist.add(new Song("The Beatles", "Let It Be"));
        playlist.add(new Song("Led Zeppelin", "Stairway to Heaven"));
        playlist.add(new Song("Pink Floyd", "Comfortably Numb"));

        while (true) {
            printMenu();
            int choice = getInt("Enter your choice: ");

            if (choice == 1) {
                addSong();
            } else if (choice == 2) {
                displayList();
            } else if (choice == 3) {
                deleteByArtistAndName();
            } else if (choice == 4) {
                deleteByNumber();
            } else if (choice == 5) {
                deleteByArtist();
            } else if (choice == 6) {
                deleteByName();
            } else if (choice == 7) {
                sortByArtist();
            } else if (choice == 8) {
                sortBySongName();
            } else if (choice == 9) {
                searchByArtist();
            } else if (choice == 10) {
                clearList();
            } else if (choice == 11) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== Song List Manager ==========");
        System.out.println("1.  Add a new song");
        System.out.println("2.  Display song list");
        System.out.println("3.  Delete a song (artist + name)");
        System.out.println("4.  Delete a song by number");
        System.out.println("5.  Delete songs by artist");
        System.out.println("6.  Delete songs by name");
        System.out.println("7.  Sort by artist name");
        System.out.println("8.  Sort by song name");
        System.out.println("9.  Search by artist");
        System.out.println("10. Clear the list");
        System.out.println("11. Quit");
        System.out.println("=======================================");
    }

    // 1. Add a new song
    private static void addSong() {
        System.out.print("Enter artist name: ");
        String artist = sc.nextLine().trim();
        System.out.print("Enter song name: ");
        String name = sc.nextLine().trim();
        playlist.add(new Song(artist, name));
        System.out.println("Song added.");
    }

    // 2. Display song list (numbered starting at 1)
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

    // 3. Delete a song given artist + name
    //    Create a Song object and pass it to remove(Object)
    private static void deleteByArtistAndName() {
        System.out.print("Enter artist name: ");
        String artist = sc.nextLine().trim();
        System.out.print("Enter song name: ");
        String name = sc.nextLine().trim();

        Song target = new Song(artist, name);
        if (playlist.remove(target)) {
            System.out.println("Song deleted.");
        } else {
            System.out.println("Song not found.");
        }
    }

    // 4. Delete a song by its number on the playlist
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

    // 5. Delete all songs by a given artist
    private static void deleteByArtist() {
        System.out.print("Enter artist name: ");
        String artist = sc.nextLine().trim();
        int count = 0;

        for (int i = playlist.size() - 1; i >= 0; i--) {
            if (playlist.get(i).getArtist().equalsIgnoreCase(artist)) {
                playlist.remove(i);
                count++;
            }
        }
        System.out.println(count + " song(s) by \"" + artist + "\" deleted.");
    }

    // 6. Delete all songs with a given name
    private static void deleteByName() {
        System.out.print("Enter song name: ");
        String name = sc.nextLine().trim();
        int count = 0;

        for (int i = playlist.size() - 1; i >= 0; i--) {
            if (playlist.get(i).getName().equalsIgnoreCase(name)) {
                playlist.remove(i);
                count++;
            }
        }
        System.out.println(count + " song(s) named \"" + name + "\" deleted.");
    }

    // 7. Sort by artist name
    private static void sortByArtist() {
        if (playlist.size() <= 1) {
            System.out.println("Nothing to sort.");
            return;
        }
        for (int i = 1; i < playlist.size(); i++) {
            Song key = playlist.get(i);
            int j = i - 1;
            while (j >= 0 && compareArtistThenName(playlist.get(j), key) > 0) {
                playlist.set(j + 1, playlist.get(j));
                j--;
            }
            playlist.set(j + 1, key);
        }
        System.out.println("Playlist sorted by artist name.");
        displayList();
    }

    // 8. Sort by song name
    private static void sortBySongName() {
        if (playlist.size() <= 1) {
            System.out.println("Nothing to sort.");
            return;
        }
        for (int i = 1; i < playlist.size(); i++) {
            Song key = playlist.get(i);
            int j = i - 1;
            while (j >= 0 && compareNameThenArtist(playlist.get(j), key) > 0) {
                playlist.set(j + 1, playlist.get(j));
                j--;
            }
            playlist.set(j + 1, key);
        }
        System.out.println("Playlist sorted by song name.");
        displayList();
    }

    // 9. Search by artist
    private static void searchByArtist() {
        System.out.print("Enter artist name: ");
        String artist = sc.nextLine().trim();
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

    // 10. Clear the list
    private static void clearList() {
        playlist.clear();
        System.out.println("Playlist cleared.");
    }

    // helpers
    private static int compareArtistThenName(Song a, Song b) {
        int c = a.getArtist().compareToIgnoreCase(b.getArtist());
        if (c != 0) return c;
        return a.getName().compareToIgnoreCase(b.getName());
    }

    private static int compareNameThenArtist(Song a, Song b) {
        int c = a.getName().compareToIgnoreCase(b.getName());
        if (c != 0) return c;
        return a.getArtist().compareToIgnoreCase(b.getArtist());
    }

    private static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}