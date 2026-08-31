import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.ArrayList;

public abstract class Costume {
    private int x;
    private int y;
  
    public Costume(int xc, int yc) {
        x = xc;
        y = yc;
    }
  
    public abstract void drawNose(Graphics g);
    public abstract void drawEars(Graphics g);
  
    public void drawBody(Graphics g) {
        g.drawRect(x, y + 40, 40, 60);
    }
  
    public void drawHead(Graphics g) {
        g.drawOval(x+10, y, 40, 40);
    }
    
    public void drawMe(Graphics g) {
        drawHead(g);
        drawBody(g);
        drawNose(g);
        drawEars(g);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}