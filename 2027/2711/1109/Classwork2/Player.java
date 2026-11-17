import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }
}
