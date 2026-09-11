public class Song {
    // Information stored for one song.
    private String name;
    private String artist;
    private String album;

    public Song(String name, String artist, String album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object object) {
        // Two songs match when all three parts are the same.
        if (this == object) {
            return true;
        }
        if (!(object instanceof Song)) {
            return false;
        }
        Song other = (Song) object;
        return name.equals(other.getName())
                && artist.equals(other.getArtist())
                && album.equals(other.getAlbum());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + album.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + ", " + artist + ", " + album;
    }
}
