import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Screen extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private MyArrayList<Song> songs;
    private JTextArea display;
    private JTextField name, artist, album, location;
    private JButton add, deleteNumber, deleteSong, randomize, sort;
    private JComboBox<String> sortChoice;

    public Screen() {
        // Add the starting songs to the playlist.
        songs = new MyArrayList<Song>();
        songs.add(new Song("Here Comes the Sun", "The Beatles", "Abbey Road"));
        songs.add(new Song("Dreams", "Fleetwood Mac", "Rumours"));
        songs.add(new Song("Billie Jean", "Michael Jackson", "Thriller"));
        songs.add(new Song("Rolling in the Deep", "Adele", "21"));
        songs.add(new Song("Viva la Vida", "Coldplay", "Viva la Vida"));
        songs.add(new Song("Fast Car", "Tracy Chapman", "Tracy Chapman"));

        // Create the area that displays the playlist.
        setLayout(new BorderLayout());
        display = new JTextArea(18, 55);
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);

        // Create the text fields and buttons.
        JPanel controls = new JPanel(new GridLayout(4, 1));
        JPanel row1 = new JPanel();
        name = new JTextField(10);
        artist = new JTextField(10);
        album = new JTextField(10);
        row1.add(new JLabel("Song:"));
        row1.add(name);
        row1.add(new JLabel("Artist:"));
        row1.add(artist);
        row1.add(new JLabel("Album:"));
        row1.add(album);

        JPanel row2 = new JPanel();
        location = new JTextField(4);
        add = new JButton("Add Song");
        deleteNumber = new JButton("Delete Number");
        row2.add(new JLabel("Location:"));
        row2.add(location);
        row2.add(add);
        row2.add(deleteNumber);

        JPanel row3 = new JPanel();
        deleteSong = new JButton("Delete Matching Song");
        row3.add(new JLabel("Enter the song information above:"));
        row3.add(deleteSong);

        JPanel row4 = new JPanel();
        randomize = new JButton("Randomize");
        sort = new JButton("Sort A-Z");
        sortChoice = new JComboBox<String>(new String[] {"Song", "Artist", "Album"});
        row4.add(randomize);
        row4.add(sortChoice);
        row4.add(sort);

        controls.add(row1);
        controls.add(row2);
        controls.add(row3);
        controls.add(row4);
        add(controls, BorderLayout.SOUTH);

        add.addActionListener(this);
        deleteNumber.addActionListener(this);
        deleteSong.addActionListener(this);
        randomize.addActionListener(this);
        sort.addActionListener(this);
        displaySongs();
    }

    public void actionPerformed(ActionEvent event) {
        // Check which button the user clicked.
        if (event.getSource() == add) {
            // All song information must be entered before adding.
            if (name.getText().trim().equals("")
                    || artist.getText().trim().equals("")
                    || album.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Enter the song, artist, and album.");
            } else {
                try {
                    Song newSong = new Song(name.getText().trim(),
                            artist.getText().trim(), album.getText().trim());
                    songs.add(Integer.parseInt(location.getText()) - 1, newSong);
                    name.setText("");
                    artist.setText("");
                    album.setText("");
                    location.setText("");
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(this,
                            "The add location must be from 1 to " + (songs.size() + 1) + ".");
                }
            }
        } else if (event.getSource() == deleteNumber) {
            // The displayed numbers start at 1, but array indexes start at 0.
            try {
                songs.remove(Integer.parseInt(location.getText()) - 1);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(this, "Enter a valid song number.");
            }
        } else if (event.getSource() == deleteSong) {
            Song songToDelete = new Song(name.getText(), artist.getText(), album.getText());
            // This calls the required remove(Object) method.
            if (!songs.remove((Object) songToDelete)) {
                JOptionPane.showMessageDialog(this, "Song not found.");
            }
        } else if (event.getSource() == randomize) {
            // Swap each song with a random song.
            Random random = new Random();
            for (int i = songs.size() - 1; i > 0; i--) {
                int randomIndex = random.nextInt(i + 1);
                Song temp = songs.get(i);
                songs.set(i, songs.get(randomIndex));
                songs.set(randomIndex, temp);
            }
        } else if (event.getSource() == sort) {
            // Use a bubble sort to put the songs in A-Z order.
            for (int i = 0; i < songs.size() - 1; i++) {
                for (int j = 0; j < songs.size() - i - 1; j++) {
                    String first;
                    String second;
                    if (sortChoice.getSelectedIndex() == 1) {
                        first = songs.get(j).getArtist();
                        second = songs.get(j + 1).getArtist();
                    } else if (sortChoice.getSelectedIndex() == 2) {
                        first = songs.get(j).getAlbum();
                        second = songs.get(j + 1).getAlbum();
                    } else {
                        first = songs.get(j).getName();
                        second = songs.get(j + 1).getName();
                    }
                    if (first.compareToIgnoreCase(second) > 0) {
                        Song temp = songs.get(j);
                        songs.set(j, songs.get(j + 1));
                        songs.set(j + 1, temp);
                    }
                }
            }
        }
        displaySongs();
    }

    public void displaySongs() {
        // Rewrite the numbered list after every change.
        display.setText("PLAYLIST\n\n");
        for (int i = 0; i < songs.size(); i++) {
            display.append((i + 1) + ". " + songs.get(i) + "\n");
        }
    }
}
