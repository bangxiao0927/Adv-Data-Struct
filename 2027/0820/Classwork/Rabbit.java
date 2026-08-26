
import java.awt.Graphics;

public class Rabbit extends Costume {
    public Rabbit(int x, int y) {
        super(x, y);
    }
  
    public void drawNose(Graphics g) {
        g.drawOval(getX() + 18, getY() + 18, 4, 4);
    }
    
    public void drawEars(Graphics g) {
        g.drawOval(getX() + 8, getY() - 25, 6, 25);
        g.drawOval(getX() + 26, getY() - 25, 6, 25);
    }
}
