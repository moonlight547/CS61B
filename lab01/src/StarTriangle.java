public class StarTriangle {
    public static void main(String[] args) {
        // Exercise 2.5: starTriangle
        // We use two loops:
        // The outer loop controls the number of lines (1 to 5)
        // The inner loop controls how many stars to print on each line

        for (int line = 1; line <= 5; line++) {
            for (int j = 0; j < line; j++) {
                System.out.print("*");
            }
            System.out.println(); // Go to the next line
        }
    }
}
