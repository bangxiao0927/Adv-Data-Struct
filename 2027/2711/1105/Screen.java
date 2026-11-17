import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Screen extends JPanel implements MouseListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int TIMER_SECONDS = 30;

    private Circle cr;
    private BubbleList bubbles;

    private boolean gameOver = false;
    private String result = "";
    private int seconds = TIMER_SECONDS;

    public Screen() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        addMouseListener(this);

        cr = new Circle(60);
        Thread circleThread = new Thread(cr);
        circleThread.start();

        bubbles = new BubbleList();
        int bubbleCount = 24;
        for (int i = 0; i < bubbleCount; i++) {
            Bubble b = new Bubble(WIDTH, HEIGHT);
            bubbles.add(b);
            new Thread(b).start();
        }

        Animate animate = new Animate(this);
        Thread animateThread = new Thread(animate);
        animateThread.start();

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

        cr.drawMe(g);

        Bubble[] list = bubbles.toArray();
        for (int i = 0; i < list.length; i++) {
            list[i].drawMe(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Time: " + getSeconds(), 25, 40);
        g.drawString("Bubbles left: " + bubbles.size(), 25, 72);
        g.drawString("Click a bubble to pop it!", 25, 104);

        if (isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 64));
            if (getResult().equals("WIN")) {
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

    @Override
    public void mousePressed(MouseEvent e) {
        if (isGameOver()) {
            return;
        }
        int mx = e.getX();
        int my = e.getY();

        for (int i = bubbles.size() - 1; i >= 0; i--) {
            Bubble b = bubbles.get(i);
            if (b != null && b.contains(mx, my)) {
                b.pop();
                b.stop();
                bubbles.remove(i);
                repaint();

                if (bubbles.isEmpty()) {
                    finish(true);
                }
                break;
            }
        }
    }

    public synchronized void finish(boolean won) {
        if (gameOver) {
            return;
        }
        gameOver = true;
        if (won) {
            result = "WIN";
        } else {
            result = "LOSE";
        }
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

    public synchronized int getSeconds() {
        return seconds;
    }

    public synchronized String getResult() {
        return result;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
