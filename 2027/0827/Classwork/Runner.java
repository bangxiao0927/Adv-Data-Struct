import javax.swing.JFrame;

public class Runner {
	public static void main(String[] args) {
  	JFrame frame = new JFrame("Screen");
    
    Screen sc = new Screen();
    frame.add(sc);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}