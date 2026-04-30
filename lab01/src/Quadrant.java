public class Quadrant {

    // This method returns the quadrant number (1, 2, 3, 4) or 0
    public static int quadrant(double x, double y) {
        if (x > 0 && y > 0) {
            return 1;
        } else if (x < 0 && y > 0) {
            return 2;
        } else if (x < 0 && y < 0) {
            return 3;
        } else if (x > 0 && y < 0) {
            return 4;
        } else {
            return 0; // On the axis
        }
    }

    public static void main(String[] args) {
        // Expected output: 1, 2, 3, 4, 0
        System.out.println(quadrant(12.4, 17.8));
        System.out.println(quadrant(-2.3, 3.5));
        System.out.println(quadrant(-15.2, -3.1));
        System.out.println(quadrant(4.5, -4.5));
        System.out.println(quadrant(0.0, 0.0));
    }
}

