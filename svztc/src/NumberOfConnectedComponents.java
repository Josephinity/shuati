/**
find the number of connected components in a graph
 */

import java.util.List;
import java.util.Set;
import java.util.HashSet;

class GraphNode {
    int label;
    List<GraphNode> successors;
}
public class NumberOfConnectedComponents {

    List<GraphNode> graph;  //takes a graph with adjacency lists
    int numberOfConnComponents;

    // ..constructor..

    public int numberOfConnectedComponents() {
        numberOfConnComponents = 0;
        Set<GraphNode> visited = new HashSet<>();
        for(GraphNode n: graph) {
            dfs(n, visited);
        }
        return numberOfConnComponents;
    }

    private void dfs(GraphNode n, Set<GraphNode> visited) {
        if(visited.contains(n)) return;
        numberOfConnComponents++;
        visited.add(n);
        for(GraphNode successor: n.successors) {
            dfs(successor, visited);
        }
    }
}
