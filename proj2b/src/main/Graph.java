package main;

import java.util.*;

public class Graph {
    // varibles : what is our graph representation?
    // adjList, adjMatrix

    Map<Integer, List<Integer>> adj = new HashMap<>();

    public void addEdge(int parent, int child) {
        adj.computeIfAbsent(parent, k  -> new ArrayList<>()).add(child);

    }

    public List<Integer> getChildren(int parent) {
        return adj.getOrDefault(parent, new ArrayList<>());
    }

    // go to graph, find the ID's hyponym
    public Set<Integer> getAllDescendants ( int id){
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(id);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int child : adj.getOrDefault(current, new ArrayList<>())) {
                if (visited.add(child)) {
                    stack.push(child);
                }
            }
        }
        return visited;
    }

}
