import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeapYearTest {

    /** Test for Leap Years: Should be True */
    @Test
    public void testLeapYear() {
        assertTrue(LeapYear.isLeapYear(2000), "2000 should be a leap year");
        assertTrue(LeapYear.isLeapYear(2004), "2004 should be a leap year");
        assertTrue(LeapYear.isLeapYear(2400), "2400 should be a leap year");
    }

    /** Test for Non-Leap Years: Should be False */
    @Test
    public void testNonLeapYear() {
        assertFalse(LeapYear.isLeapYear(2001), "2001 should be a non-leap year");
        assertFalse(LeapYear.isLeapYear(1900), "1900 should be a non-leap year (divisible by 100 but not 400)");
        assertFalse(LeapYear.isLeapYear(2100), "2100 should be a non-leap year");
        assertFalse(LeapYear.isLeapYear(2002), "2002 should be a non-leap year");
    }
}

