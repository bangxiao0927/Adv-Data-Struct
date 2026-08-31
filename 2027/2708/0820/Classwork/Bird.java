
import java.awt.Graphics;

public class Bird extends Costume {
    public Bird(int x, int y) {
        super(x, y);
    }
    
    public void drawNose(Graphics g) {
        int[] xPoints = {getX() + 15, getX() + 20, getX() + 25};
        int[] yPoints = {getY() + 20, getY() + 35, getY() + 20};
        g.drawPolygon(xPoints, yPoints, 3);
    }
    
    public void drawEars(Graphics g) {
        // Birds do not have prominent ears, leave empty or draw small tufts
    }
    
    public void drawMe(Graphics g) {
        drawHead(g);
        drawBody(g);
        drawNose(g);
    }
}