/*package main;
public class HyponymsHandler {
} */

package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HyponymHandler extends NgordnetQueryHandler {
    private WordNet wn;

    public HyponymHandler(WordNet wn) {
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        //return "NGrams and Timeseries aren't relevant for 2B! This button should do nothing.";
        Set<String> words = new HashSet<>(q.words());
        List<String> result = wn.hyponyms(words);
        return result.toString();

    }
}
