public class PrintIndexed {

    // This method prints each character followed by its "reverse index"
    public static void printIndexed(String s) {
        // Loop through the string from start to finish
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);                 // Get the character
            int reverseIndex = s.length() - 1 - i; // Calculate distance from the end

            System.out.print(c);            // Print the letter
            System.out.print(reverseIndex); // Print the number
        }
        System.out.println(); // Print a new line at the end
    }

    public static void main(String[] args) {
        // Test the method with the word "ZELDA"
        printIndexed("ZELDA");
    }
}
