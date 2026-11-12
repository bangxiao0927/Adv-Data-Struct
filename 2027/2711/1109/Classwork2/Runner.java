import javax.swing.JFrame;

// Runs the program. Closing the window stops it.
public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Saving Players");
        Screen screen = new Screen();
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        screen.requestFocusInWindow();
    }
}
