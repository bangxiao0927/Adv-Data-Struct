public class AreaCircle implements Runnable {
    private double radius;
    private double area;

    public AreaCircle(double radius) {
        this.radius = radius;
    }

    public void run() {
        try {
            Thread.sleep(3000); // takes 3 seconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        area = Math.PI * radius * radius;
    }

    public double getArea() {
        return area;
    }
}