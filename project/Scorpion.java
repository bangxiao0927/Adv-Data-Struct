import java.awt.Color;
import java.awt.Graphics;

// Item 5: a scorpion with legs, claws, and a curved tail.
public class Scorpion extends Scenery {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;

    public Scorpion(int x, int y) {
        super(x, y);
    }

    public String getName() {
        return "Scorpion";
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void drawMe(Graphics g) {
        drawShadow(g);
        int x = getX();
        int y = getY();
        g.setColor(new Color(99, 55, 30));
        for (int i = 0; i < 4; i++) {
            int legX = x + 25 + i * 7;
            g.drawLine(legX, y + 39, legX - 9, y + 24);
            g.drawLine(legX, y + 43, legX - 9, y + 58);
        }
        g.fillOval(x + 22, y + 30, 35, 22);
        g.drawLine(x + 26, y + 35, x + 12, y + 22);
        g.drawLine(x + 26, y + 47, x + 10, y + 53);
        g.fillArc(x, y + 10, 22, 20, 30, 290);
        g.fillArc(x, y + 43, 22, 17, 30, 290);
        g.fillOval(x + 51, y + 29, 14, 12);
        g.fillOval(x + 61, y + 22, 12, 13);
        g.fillOval(x + 68, y + 12, 12, 14);
        g.fillOval(x + 64, y + 3, 12, 12);
        g.fillPolygon(new int[] {x + 65, x + 55, x + 65},
                      new int[] {y + 3, y + 11, y + 13}, 3);
        g.setColor(new Color(234, 190, 117));
        g.fillOval(x + 25, y + 35, 3, 3);
        g.fillOval(x + 25, y + 43, 3, 3);
    }
}
