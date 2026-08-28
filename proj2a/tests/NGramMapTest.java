import ngrams.NGramMap;
import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Utils.*;
import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the NGramMap class.
 *  @author Josh Hug
 */
public class NGramMapTest {
    @Test
    public void testCountHistory() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(2005, 2006, 2007, 2008));
        List<Double> expectedCounts = new ArrayList<>
                (Arrays.asList(646179.0, 677820.0, 697645.0, 795265.0));

        TimeSeries request2005to2008 = ngm.countHistory("request");
        assertThat(request2005to2008.years()).isEqualTo(expectedYears);

        for (int i = 0; i < expectedCounts.size(); i += 1) {
            assertThat(request2005to2008.data().get(i)).isWithin(1E-10).of(expectedCounts.get(i));
        }

        expectedYears = new ArrayList<>
                (Arrays.asList(2006, 2007));
        expectedCounts = new ArrayList<>
                (Arrays.asList(677820.0, 697645.0));

        TimeSeries request2006to2007 = ngm.countHistory("request", 2006, 2007);

        assertThat(request2006to2007.years()).isEqualTo(expectedYears);

        for (int i = 0; i < expectedCounts.size(); i += 1) {
            assertThat(request2006to2007.data().get(i)).isWithin(1E-10).of(expectedCounts.get(i));
        }
    }

    @Test
    public void testOnLargeFile() {
        // creates an NGramMap from a large dataset
        NGramMap ngm = new NGramMap(TOP_14337_WORDS_FILE,
                TOTAL_COUNTS_FILE);

        // returns the count of the number of occurrences of fish per year between 1850 and 1933.
        TimeSeries fishCount = ngm.countHistory("fish", 1850, 1933);
        assertThat(fishCount.get(1865)).isWithin(1E-10).of(136497.0);
        assertThat(fishCount.get(1922)).isWithin(1E-10).of(444924.0);

        TimeSeries totalCounts = ngm.totalCountHistory();
        assertThat(totalCounts.get(1865)).isWithin(1E-10).of(2563919231.0);

        // returns the relative weight of the word fish in each year between 1850 and 1933.
        TimeSeries fishWeight = ngm.weightHistory("fish", 1850, 1933);
        assertThat(fishWeight.get(1865)).isWithin(1E-7).of(136497.0/2563919231.0);

        TimeSeries dogCount = ngm.countHistory("dog", 1850, 1876);
        assertThat(dogCount.get(1865)).isWithin(1E-10).of(75819.0);

        List<String> fishAndDog = new ArrayList<>();
        fishAndDog.add("fish");
        fishAndDog.add("dog");
        TimeSeries fishPlusDogWeight = ngm.summedWeightHistory(fishAndDog, 1865, 1866);

        double expectedFishPlusDogWeight1865 = (136497.0 + 75819.0) / 2563919231.0;
        assertThat(fishPlusDogWeight.get(1865)).isWithin(1E-10).of(expectedFishPlusDogWeight1865);
    }

    @Test
    public void testcountHistoryNonexistentWord() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.countHistory("zxcv");

        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    public void testcountHistoryWithYearsNonexistentWord() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.countHistory("zxcv", 2005,2007);

        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    public void testweightHistoryNonexistentWord() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.weightHistory("zxcv");

        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    public void testweightHistoryWithYearNonexistentWord() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.weightHistory("zxcv", 2005, 2007);

        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    public void testsummedWeightHistoryNonexistentWord() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.weightHistory("zxcv", 2005, 2007);

        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    public void testsummedWeightHistoryWithOneNonexistentWord() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        List<String> requestAndZxcv = new ArrayList<>();
        requestAndZxcv.add("request");
        requestAndZxcv.add("zxcv");

        TimeSeries result1 = ngm.summedWeightHistory(requestAndZxcv, 2005, 2007);

        List<String> request = new ArrayList<>();
        request.add("request");

        TimeSeries result2 = ngm.summedWeightHistory(request, 2005, 2007);

        assertThat(result1.data()).isEqualTo(result2.data());
        assertThat(result1.years()).isEqualTo(result2.years());

    }

    @Test
    public void testOneCountHistory() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(2005));
        List<Double> expectedCounts = new ArrayList<>
                (Arrays.asList(646179.0));
        TimeSeries request2005 = ngm.countHistory("request",2005, 2005);
        assertThat(request2005.years()).isEqualTo(expectedYears);
        assertThat(request2005.data().get(0)).isWithin(1E-10).of(expectedCounts.get(0));


    }

    @Test
    public void testtotalCountHistorydefensiveCopy() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.totalCountHistory();
        result.put(1470, 99999.0);
        TimeSeries result2 = ngm.totalCountHistory();

        assertThat(result2.get(1470)).isEqualTo(984.0);
    }

    @Test
    public void testCountHistorydefensiveCopy() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        TimeSeries result = ngm.countHistory("request");
        result.put(2005, 99999.0);
        TimeSeries result2 = ngm.countHistory("request");

        assertThat(result2.get(2005)).isEqualTo(646179.0);
    }

}  