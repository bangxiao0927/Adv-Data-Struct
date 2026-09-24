import java.util.Scanner;

// Song List Manager - keeps asking until the user quits.
public class Runner {
    private static DLList<Song> playlist = new DLList<Song>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 5 songs to start, two of them share an artist
        playlist.add(new Song("The Beatles", "Hey Jude"));
        playlist.add(new Song("Queen", "Bohemian Rhapsody"));
        playlist.add(new Song("The Beatles", "Let It Be"));
        playlist.add(new Song("Led Zeppelin", "Stairway to Heaven"));
        playlist.add(new Song("Queen", "Don't Stop Me Now"));

        boolean quit = false;
        while (!quit) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            if (choice == 1) {
                addSong();
            } else if (choice == 2) {
                showList(playlist, "Playlist:");
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
                playlist.clear();
                System.out.println("Playlist cleared.");
            } else if (choice == 11) {
                quit = true;
                System.out.println("Goodbye!");
            } else {
                System.out.println("That is not a choice.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("Song List Manager");
        System.out.println("1. Add a new song");
        System.out.println("2. Display song list");
        System.out.println("3. Delete a song by artist and song name");
        System.out.println("4. Delete a song by number on the playlist");
        System.out.println("5. Delete songs by artist");
        System.out.println("6. Delete songs by name");
        System.out.println("7. Sort by artist name");
        System.out.println("8. Sort by song name");
        System.out.println("9. Search by artist");
        System.out.println("10. Clear the list");
        System.out.println("11. Quit");
    }

    // prints the songs numbered from 1. Song ends with a new line, so print is used
    private static void showList(DLList<Song> songs, String title) {
        if (songs.size() == 0) {
            System.out.println("The playlist is empty.");
            return;
        }
        System.out.println(title);
        for (int i = 0; i < songs.size(); i++) {
            System.out.print((i + 1) + ". " + songs.get(i));
        }
    }

    private static void addSong() {
        System.out.print("Artist name: ");
        String artist = sc.nextLine();
        System.out.print("Song name: ");
        String name = sc.nextLine();
        playlist.add(new Song(artist, name));
        System.out.println("Song added.");
    }

    private static void deleteByArtistAndName() {
        System.out.print("Artist name: ");
        String artist = sc.nextLine();
        System.out.print("Song name: ");
        String name = sc.nextLine();

        // make a Song from what was typed and let remove compare with equals
        if (playlist.remove(new Song(artist, name))) {
            System.out.println("Song deleted.");
        } else {
            System.out.println("That song is not in the list.");
        }
    }

    private static void deleteByNumber() {
        if (playlist.size() == 0) {
            System.out.println("The playlist is empty.");
            return;
        }
        showList(playlist, "Playlist:");

        int number = readInt("Number to delete: ");
        if (number < 1 || number > playlist.size()) {
            System.out.println("There is no song " + number + ".");
            return;
        }
        Song gone = playlist.remove(number - 1);
        System.out.println("Deleted " + gone.getArtist() + " - " + gone.getName());
    }

    private static void deleteByArtist() {
        System.out.print("Artist name: ");
        String artist = sc.nextLine();

        // go backwards so the index stays right after each remove
        int count = 0;
        for (int i = playlist.size() - 1; i >= 0; i--) {
            if (playlist.get(i).getArtist().equals(artist)) {
                playlist.remove(i);
                count++;
            }
        }
        System.out.println("Deleted " + count + " song(s).");
    }

    private static void deleteByName() {
        System.out.print("Song name: ");
        String name = sc.nextLine();

        int count = 0;
        for (int i = playlist.size() - 1; i >= 0; i--) {
            if (playlist.get(i).getName().equals(name)) {
                playlist.remove(i);
                count++;
            }
        }
        System.out.println("Deleted " + count + " song(s).");
    }

    private static void sortByArtist() {
        // insertion sort using get and set on the list
        for (int i = 1; i < playlist.size(); i++) {
            Song temp = playlist.get(i);
            int j = i - 1;
            while (j >= 0 && compareArtist(playlist.get(j), temp) > 0) {
                playlist.set(j + 1, playlist.get(j));
                j--;
            }
            playlist.set(j + 1, temp);
        }
        showList(playlist, "Sorted by artist:");
    }

    private static void sortBySongName() {
        for (int i = 1; i < playlist.size(); i++) {
            Song temp = playlist.get(i);
            int j = i - 1;
            while (j >= 0 && compareName(playlist.get(j), temp) > 0) {
                playlist.set(j + 1, playlist.get(j));
                j--;
            }
            playlist.set(j + 1, temp);
        }
        showList(playlist, "Sorted by song name:");
    }

    // artist first, then song name if the artists are the same
    private static int compareArtist(Song a, Song b) {
        int result = a.getArtist().compareTo(b.getArtist());
        if (result != 0) {
            return result;
        }
        return a.getName().compareTo(b.getName());
    }

    // song name first, then artist if the names are the same
    private static int compareName(Song a, Song b) {
        int result = a.getName().compareTo(b.getName());
        if (result != 0) {
            return result;
        }
        return a.getArtist().compareTo(b.getArtist());
    }

    private static void searchByArtist() {
        System.out.print("Artist name: ");
        String artist = sc.nextLine();

        DLList<Song> found = new DLList<Song>();
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getArtist().equals(artist)) {
                found.add(playlist.get(i));
            }
        }
        if (found.size() == 0) {
            System.out.println("No songs by " + artist + ".");
        } else {
            showList(found, "Songs by " + artist + ":");
        }
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Please type a number.");
            System.out.print(prompt);
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
}
