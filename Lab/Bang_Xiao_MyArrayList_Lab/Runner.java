import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        // Create and show the program window.
        JFrame window = new JFrame("MyArrayList Playlist");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new Screen());
        window.pack();
        window.setVisible(true);
    }
}
