package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private NGramMap map;

    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        //go to NGrammap, get weightHistory
        for (String word : words) {
            TimeSeries history = map.weightHistory(word, startYear, endYear);

            response += word + ": {" ;
            //through all years,get value, and string these.
            for(int year : history.keySet()) {
                response += year + "=" + history.get(year);
                if (year != history.lastKey()) {
                    response += ", ";
                }
            }

            response += "}\n";
        }
        return response;

    }

}
