public class VolCylinder implements Runnable {
    private double area;
    private double height;
    private double volume;

    public VolCylinder(double area, double height) {
        this.area = area;
        this.height = height;
    }

    public void run() {
        try {
            Thread.sleep(5000); // takes 5 seconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        volume = area * height;
    }

    public double getVolume() {
        return volume;
    }
}