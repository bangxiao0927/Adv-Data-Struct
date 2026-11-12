import javax.swing.JFrame;

public class Runner {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Bubble Pop Game");
        Screen screen = new Screen();
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
