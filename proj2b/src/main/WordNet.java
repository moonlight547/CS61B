package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordNet {
    // wrapper for a graph
    private Graph graph;
    private Map<Integer, String> idToWord;
    private Map<String, List<Integer>> wordToIds;

    public WordNet(String synsetFile, String hyponymsFile) {
        graph = new Graph();
        idToWord = new HashMap<>();
        wordToIds = new HashMap<>();

        //read and parse synsetFile
        List<String> synsetLines = readLines(synsetFile);
        for (int i = 0; i < synsetLines.size(); i++) {
            String[] parts =  synsetLines.get(i).split(",");
            int id = Integer.parseInt(parts[0]);
            String[] words = parts[1].split(" ");

            idToWord.put(id, parts[1]);
            for(String word : words) {
                wordToIds.computeIfAbsent(word, k -> new ArrayList<>()).add(id);
            }
        }

        // read and parse hyponymsFile,build the graph
        List<String> hyponymLines = readLines(hyponymsFile);
            for(int i = 0; i < hyponymLines.size(); i++) {
                String[] parts = hyponymLines.get(i).split(",");
                int parent = Integer.parseInt(parts[0]);

                for(int j = 1; j < parts.length; j++) {
                    int child = Integer.parseInt(parts[j]);
                    graph.addEdge(parent, child);
                }
            }
    }
    private List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = reader.readLine()) != null) {
               lines.add(line);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<String> hyponyms (Set<String> words) {
        //Determine whether this is an assignment or an intersection
        Set<Integer> result = null;

        for(String word : words) {
            //ids = if a word have more than one id;
            List<Integer> ids = wordToIds.getOrDefault(word, new ArrayList<>());
            Set<Integer> hyponymsForThisWord = new HashSet<>();
            for (int id :ids) {
                hyponymsForThisWord.add(id);
                hyponymsForThisWord.addAll(graph.getAllDescendants(id));
            }
            if (result == null) {
                result = hyponymsForThisWord;
            } else {
                //getting the intersection
                result.retainAll(hyponymsForThisWord);
            }
        }

        // Create a TreeSet to store words in soreted order.
        TreeSet<String> sortedWords = new TreeSet<>();
        for (int id : result) {
            String synonyms = idToWord.get(id);
            for(String word : synonyms.split(" ")) {
                sortedWords.add(word);
            }

        }
        // Copy the sorted elements from the TreeSet into a new ArrayList in the same order.
        return new ArrayList<>(sortedWords);


    }


}

