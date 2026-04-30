public class NumberTotal {
    public static void main(String[] args) {
        int total = 25;

        // Loop runs as long as 'number' is less than or equal to half of 'total'
        for (int number = 1; number <= (total / 2); number++) {
            total = total - number;
            System.out.println(total + " " + number);
        }

        System.out.println("Final total: " + total);
    }
}
