import java.util.Objects;

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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns true if the other object is a Song with the same
     * artist name and song name, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Song other = (Song) obj;
        return Objects.equals(artist, other.artist) && Objects.equals(name, other.name);
    }

    /**
     * Returns "artist - song".
     */
    @Override
    public String toString() {
        return artist + " - " + name;
    }
}
