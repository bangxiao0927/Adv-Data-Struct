// A song has an artist and a song name.
public class Song {
    private String artist;
    private String name;

    public Song(String artist, String name) {
        this.artist = artist;
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    // true if the artist and the song name are both the same
    public boolean equals(Object other) {
        if (other instanceof Song) {
            Song s = (Song) other;
            return artist.equals(s.artist) && name.equals(s.name);
        }
        return false;
    }

    // "artist - song name" with a new line at the end
    public String toString() {
        return artist + " - " + name + "\n";
    }
}
