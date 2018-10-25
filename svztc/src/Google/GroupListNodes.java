package Google;

/**
 *
 * Round 1 Find random point in rectangles
 *
 *
 Round2
 Given some nodes in a singly linked list, how many groups of consecutively connected nodes there is.
 For linked list
 0->1->2->3->4->5->6,
 given nodes 1, 3, 5, 6
 there are 3 groups [1], [3], and [5, 6].

 */
import java.util.*;

class Node {
    int val;
    Node next;
}
public class GroupListNodes {
    //get the number of connected components in a list
    public int numberOfGroups(Set<Node> nodes) {
        Set<Node> visited = new HashSet<>();
        int cnt = 0;
        for(Node node: nodes) {
            while(!visited.contains(node)) {
                if(nodes.contains(node)){
                    visited.add(node);
                    node = node.next;
                } else {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
}
