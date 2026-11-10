import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        // ---- Part 1: 3 Count threads ----
        Count c1 = new Count();
        Count c2 = new Count();
        Count c3 = new Count();

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);

        t1.start();
        t2.start();
        t3.start();

        // Join: wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("----- All Count threads finished -----");

        // ---- CountingStars: run for 5 seconds, then interrupt ----
        CountingStars cs = new CountingStars();
        Thread starThread = new Thread(cs);
        starThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        starThread.interrupt();

        System.out.println("----- CountingStars interrupted -----");

        // ---- AreaCircle + VolCylinder with user input ----
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius: ");
        double radius = sc.nextDouble();

        System.out.print("Enter height: ");
        double height = sc.nextDouble();

        // AreaCircle
        AreaCircle ac = new AreaCircle(radius);
        Thread areaThread = new Thread(ac);
        areaThread.start();

        try {
            areaThread.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Area of circle: " + ac.getArea());

        // VolCylinder
        VolCylinder vc = new VolCylinder(ac.getArea(), height);
        Thread volThread = new Thread(vc);
        volThread.start();

        try {
            volThread.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Volume of cylinder: " + vc.getVolume());

        sc.close();
    }
}