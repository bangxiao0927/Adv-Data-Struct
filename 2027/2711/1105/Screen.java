import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Screen Class (Part 2 of the assignment)
 * - instantiates the Circle class as cr
 * - constructor creates a Thread passing in cr and starts it
 * - paintComponent calls drawMe from cr
 * - creates an Animate thread that repeatedly repaints the screen
 * - also runs the Bubble Popping Game (Part 3):
 *   at least 20 bubbles, each moving on its own thread,
 *   click to pop, countdown timer, win/lose text at the end
 */
public class Screen extends JPanel implements MouseListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int TIMER_SECONDS = 30;

    private Circle cr;
    private BubbleList bubbles;

    private volatile boolean gameOver = false;
    private volatile String result = ""; // "WIN" or "LOSE"
    private volatile int seconds = TIMER_SECONDS;

    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        addMouseListener(this);

        // --- Circle part ---
        cr = new Circle(60);
        Thread circleThread = new Thread(cr);
        circleThread.start();

        // --- Game bubbles part (at least 20 bubbles, each on its own thread) ---
        bubbles = new BubbleList();
        int bubbleCount = 24;
        for (int i = 0; i < bubbleCount; i++) {
            Bubble b = new Bubble(WIDTH, HEIGHT);
            bubbles.add(b);
            new Thread(b).start();
        }

        // --- Animation repaint thread ---
        Animate animate = new Animate(this); // pass this as the Screen reference
        Thread animateThread = new Thread(animate);
        animateThread.start();

        // --- Countdown timer thread ---
        Countdown timer = new Countdown(this, TIMER_SECONDS);
        new Thread(timer).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the circle (cr) as required
        cr.drawMe(g);

        // draw all the bubbles
        Bubble[] list = bubbles.toArray();
        for (int i = 0; i < list.length; i++) {
            list[i].drawMe(g);
        }

        // draw the HUD
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Time: " + seconds, 25, 40);
        g.drawString("Bubbles left: " + bubbles.size(), 25, 72);
        g.drawString("Click a bubble to pop it!", 25, 104);

        // draw the win / lose text at the end
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 64));
            if (result.equals("WIN")) {
                g.setColor(new Color(0, 150, 0));
                g.drawString("YOU WIN!", WIDTH / 2 - 190, HEIGHT / 2);
            } else {
                g.setColor(new Color(180, 0, 0));
                g.drawString("TIME IS UP - YOU LOSE!", WIDTH / 2 - 300, HEIGHT / 2);
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Close the window to exit.", WIDTH / 2 - 130, HEIGHT / 2 + 70);
        }
    }

    // mouse click: pop the bubble that was clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver) {
            return;
        }
        int mx = e.getX();
        int my = e.getY();

        // check bubbles from top to bottom
        for (int i = bubbles.size() - 1; i >= 0; i--) {
            Bubble b = bubbles.get(i);
            if (b != null && b.contains(mx, my)) {
                b.pop();          // mark as popped so it is no longer drawn
                b.stop();         // stop the thread running this bubble
                bubbles.remove(i);// remove it from the data structure
                repaint();

                // win condition: all bubbles popped before the timer ends
                if (bubbles.isEmpty()) {
                    finish(true);
                }
                break;
            }
        }
    }

    // call when the game ends (win or lose): stop every remaining bubble thread
    public synchronized void finish(boolean won) {
        if (gameOver) {
            return;
        }
        gameOver = true;
        result = won ? "WIN" : "LOSE";
        Bubble[] list = bubbles.toArray();
        for (int i = 0; i < list.length; i++) {
            list[i].stop();
        }
        repaint();
    }

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public synchronized void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
