import java.awt.Color;
import java.awt.Graphics;

// Base class for every object that can be placed in the scenery.
public abstract class Scenery {
    private int x;
    private int y;

    public Scenery(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // The rectangular bounds are also the click target.
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + getWidth()
            && mouseY >= y && mouseY < y + getHeight();
    }

    // Every item reports its own size so the Screen can place it on the sand.
    public abstract String getName();

    public abstract int getWidth();

    public abstract int getHeight();

    // A soft shadow so the random positions still look grounded.
    public void drawShadow(Graphics g) {
        g.setColor(new Color(0, 0, 0, 60));
        g.fillOval(getX() - 5, getY() + getHeight() - 8, getWidth() + 10, 16);
    }

    public abstract void drawMe(Graphics g);
}
