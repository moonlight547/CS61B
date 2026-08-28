package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private Map<String, TimeSeries> data;
    private TimeSeries totalCounts;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        data = new HashMap<>();
        In words = new In(wordsFilename);
        In counts = new In(countsFilename);

        while (!words.isEmpty()) {
            String nextLine = words.readLine();
            String[] splitLine = nextLine.split("\t");

            int year = Integer.parseInt(splitLine[1]);
            double count = Double.parseDouble(splitLine[2]);

            TimeSeries ts = data.get(splitLine[0]);

            if (ts == null) {
                ts = new TimeSeries();
            }

            ts.put(year, count);
            data.put(splitLine[0],ts);
        }

        totalCounts = new TimeSeries();
        while (!counts.isEmpty()) {
            String nextLine = counts.readLine();
            String[] splitLine = nextLine.split(",");

            int year = Integer.parseInt(splitLine[0]);
            double count = Double.parseDouble(splitLine[1]);

            totalCounts.put(year, count);
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = data.get(word);
        TimeSeries result = new TimeSeries();
        if(ts == null) {
            return result;
        }

        for(int year : ts.keySet()) {
            if (year >= startYear && year <= endYear) {
                result.put(year, ts.get(year));
            }
        }
        return result;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts = data.get(word);
        TimeSeries result = new TimeSeries();
        if(ts == null) {
            return result;
        }
        for (int year : ts.keySet()) {
            result.put(year, ts.get(year));
        }
        return result;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
       TimeSeries result = new TimeSeries();

        for(int year : totalCounts.keySet()) {
            result.put(year, totalCounts.get(year));
        }
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = data.get(word);
        TimeSeries result = new TimeSeries();
        if(ts == null) {
            return result;
        }
        for(int year : ts.keySet()) {
            if (year >= startYear && year <= endYear) {
                result.put(year,ts.get(year)/totalCounts.get(year));
            }
        }

        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts = data.get(word);
        TimeSeries result = new TimeSeries();
        if(ts == null) {
            return result;
        }
        for (int year : ts.keySet()) {
            result.put(year,ts.get(year)/totalCounts.get(year));
        }
        return result;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();


        for(String word : words) {
            TimeSeries weights = weightHistory(word, startYear, endYear);

            for(int year : weights.keySet()) {
                if (year >= startYear && year <= endYear) {
                    double w = weights.get(year);

                    if(result.containsKey(year)) {
                        w += result.get(year);
                    }

                    result.put(year,w);
                }
            }


        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.

        TimeSeries result = new TimeSeries();

        for(String word : words) {
            TimeSeries weights = weightHistory(word);


            for(int year : weights.keySet()) {
                double w = weights.get(year);

                if(result.containsKey(year)) {
                    w += result.get(year);
                }

                result.put(year,w);

            }

        }
        return result;

    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
