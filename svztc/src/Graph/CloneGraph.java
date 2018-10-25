package Graph;
import java.util.*;
/**
How to clone an undirectedGraph
 */


class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}


public class CloneGraph {
    //DFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> visited = new HashMap<>();
        return cloneDFS(node, visited);
    }

    private UndirectedGraphNode cloneDFS(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> visited) {
        if(node == null) return null;
        if(visited.containsKey(node.label)) return visited.get(node.label);

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        visited.put(node.label, clone);
        for(UndirectedGraphNode neighbor: node.neighbors) {
            clone.neighbors.add(cloneDFS(neighbor, visited));
        }
        return clone;
    }


    //BFS
    public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<Integer, UndirectedGraphNode> visited = new HashMap<>();
        LinkedList<UndirectedGraphNode>  queue = new LinkedList<>();
        queue.add(node);
        visited.put(node.label, new UndirectedGraphNode(node.label));

        while(!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();     //dequeue next
            for(UndirectedGraphNode neighbor: cur.neighbors) {      //add neighbors to queue (exclude ones that were added before)
                if(!visited.containsKey(neighbor.label)) {
                    visited.put(neighbor.label, new UndirectedGraphNode((neighbor.label))); //clone neighbor
                    queue.add(neighbor);                                                    // add neighbor to queue
                }
                visited.get(cur.label).neighbors.add(visited.get(neighbor.label));          //add neighbor to the 'neighbors' list of current node
            }
        }
        return visited.get(node.label);
    }
}
