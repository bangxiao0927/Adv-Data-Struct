import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.ArrayList; 
import java.awt.*;

public class Screen extends JPanel {
	ArrayList<Costume> costumes;
	public Screen() {
  	    costumes = new ArrayList<Costume>();
        costumes.add(new Bird(100, 250));
        costumes.add(new Cat(200, 250));
        costumes.add(new Dog(300, 250));
        costumes.add(new Rabbit(400, 250));
    }
    @Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 500);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < costumes.size(); i++) {
	    	costumes.get(i).drawMe(g);
        }
  }
}
