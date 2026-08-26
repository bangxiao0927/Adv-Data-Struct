import java.awt.Graphics;

public class Cat extends Costume {
    public Cat(int x, int y) {
        super(x, y);
    }

    public void drawNose(Graphics g) {
        int[] xPoints = {getX() + 20, getX() + 15, getX() + 25};
        int[] yPoints = {getY() + 18, getY() + 23, getY() + 23};
        g.drawPolygon(xPoints, yPoints, 3);

        g.drawLine(getX() + 20, getY() + 23, getX() + 30, getY() + 28);
        g.drawLine(getX() + 20, getY() + 23, getX() + 10, getY() + 28);
    }
  
    public void drawEars(Graphics g) {
        int[] xPoints1 = {getX() + 10, getX() + 5, getX() + 15};
        int[] yPoints1 = {getY(), getY() - 15, getY() - 10};
        g.drawPolygon(xPoints1, yPoints1, 3);
    
        int[] xPoints2 = {getX() + 30, getX() + 25, getX() + 35};
        int[] yPoints2 = {getY(), getY() - 10, getY() - 15};
        g.drawPolygon(xPoints2, yPoints2, 3);
    }
}
