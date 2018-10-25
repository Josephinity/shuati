package Graph;

import java.util.*;
/**
 Find if the graph is cyclic

input 1: nodes

input 2: edges
 */
public class CyclicGraph {
    boolean[][] adjacencyMatrix;

    public CyclicGraph(boolean[][] matrix) {
        adjacencyMatrix = matrix;
    }

    //for adj set , peeling sinks
    public boolean hasCycle(Set<Node> graph) {//pass in a set of nodes with their out-degrees
        Map<Node, Integer> inDegree = new HashMap<>();

        for (Node n : graph) {
            for (Node child : n.children) {
                inDegree.put(child, inDegree.getOrDefault(child, 0) + 1);
            }
        }

        Deque<Node> queue = new ArrayDeque<>();
        for (Node sink : graph) {
            if (!inDegree.containsKey(sink)) {  //find all sinks and remove them
                queue.addLast(sink);
            }
        }

        int count_sink = 0;
        while (!queue.isEmpty()) {
            Node temp = queue.removeFirst();
            count_sink++;
            for (Node child : temp.children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    queue.addLast(child);
                    count_sink++;
                }
            }
        }
        return graph.size() == count_sink;

    }


    //for undirected graph
    public boolean isCyclicUndirected() {

        if (adjacencyMatrix == null || adjacencyMatrix.length == 0) return false;
        int n = adjacencyMatrix.length;

        boolean[] checked = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (checked[i]) continue;
            checked[i] = true;
            boolean[] visited = new boolean[n];
            boolean hasCycle = hasCycleUntil(visited, i);
            if (hasCycle) return true;
        }
        return false;
    }

    private boolean hasCycleUntil(boolean[] visited, int parent) { //dfs
        for (int j = parent + 1; j < adjacencyMatrix.length; j++) {
            if (adjacencyMatrix[parent][j]) {
                if (visited[j])
                    return true;
                visited[j] = true;
                hasCycleUntil(visited, j);
            }
        }
        return false;
    }

    //for directed graph
    public boolean isCyclicDirected() {
        if (adjacencyMatrix == null || adjacencyMatrix.length == 0) return false;
        int n = adjacencyMatrix.length;

        boolean[] checked = new boolean[n], inPath = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                inPath[i] = true;
                if (dfs(adjacencyMatrix, inPath, checked, i)) return true;
                checked[i] = true;
            }
        }
        return false;
    }

    private boolean dfs(boolean[][] adjacencyMatrix, boolean[] inPath, boolean[] checked, int source) {
        for (int j = 0; j < adjacencyMatrix.length; j++) {
            if (adjacencyMatrix[source][j] && !checked[j]) {
                if (inPath[j]) return true;
                inPath[j] = true;
                if (dfs(adjacencyMatrix, inPath, checked, j)) return true;
                inPath[j] = false;
                checked[j] = true;
            }
        }
        return false;
    }


    public void printGraph() {
        int n = adjacencyMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (adjacencyMatrix[i][j]) {
                    System.out.println((char) (i + 'A') + "\t" + (char) (j + 'A') + " is connected");
                }
            }
        }
    }

    public void printDirectedGraph() {
        int n = adjacencyMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j]) {
                    System.out.println((char) (i + 'A') + "\t" + (char) (j + 'A') + " is connected");
                }
            }
        }
    }


    //other solution
    class Node {
        String val;
        List<Node> children;        //successors

        Node(String val, List<Node> list) {
            this.val = val;
            this.children = list;
        }


    }

    public void traverse(List<Node> graph) {
        Set<Node> visited = new HashSet<>();
        for(Node n: graph) {
            dfs(n, visited);
        }
    }

    private void dfs(Node n, Set<Node> visited) {
        if(visited.contains(n)) return;
        System.out.print(n.val + " ");
        visited.add(n);
        for(Node child: n.children) {
            dfs(child, visited);
        }
    }
}
