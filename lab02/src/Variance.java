import org.junit.Test;

public class Variance {
    @Test
    public void testVarianceLectureWhyAmIDoingThis(){
        double[] input = {10, 20, 30, 40};
        double expected = 125.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testEmptyList() {
        double[] input = {};
        double  expected = 0.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void testRepeatedNumbers() {
        double[] input = {61, 61, 61, 61, 61};
        double  expected = 0.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNegative() {
        double[] input = {-1, -2, -3, -8};
        double  expected = 7.25;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAverageZero() {
        double[] input = {0};
        double expected = 0.0;
        double actual = Variance.average(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAverageEmpty() {
        double[] input = {};
        double expected = 0.0;
        double actual = Variance.average(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAverageRepeated() {
        double[] input = {5, 5, 5, 5, 5, 5, 5, 5, 23 };
        double expected = 7.0 ;
        double actual = Variance.average(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testVarianceInputNonInteger() {
        double[] input = {1.5, 2.5, 3.5, 4.5 };
        double expected = 1.25;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAverageOutputNotIntegers() {
        double[] input = {5, 5, 5, 5, 5, 5, 5, 24 };
        double expected = 7.375 ;
        double actual = Variance.average(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAverageInputNotIntegers() {
        double[] input = {5.5, 6.5 };
        double expected = 6 ;
        double actual = Variance.average(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testAverage() {
        double[] input = {0, 1, 2 };
        double output = Variance.average(input);

        assertThat(output).isEqualTo( 1);
    }



}

