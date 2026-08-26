import java.awt.Color;
import java.awt.Graphics;

public class Dog extends Costume {
    public Dog(int x, int y) {
        super(x, y);
    }
    
    public void drawNose(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(getX() + 17, getY() + 18, 5, 5);
    }
    
    public void drawEars(Graphics g) {
        g.drawOval(getX() + 5, getY() - 5, 5, 10);
        g.drawOval(getX() + 30, getY() - 5, 5, 10);
    }
}