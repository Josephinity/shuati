package Tree;
import java.util.*;
/**
 Print nodes in a tree by columns


 Solution complexity:
 Time: nlogn for sorting
 Space: n + d (logn)
 */



public class PrintTreeByColumn {

    public static void printTreeByColumn(TreeNode root) {

        PriorityQueue<NodeGrid> q = new PriorityQueue<>();
        processNodesByColumns(root, 0, 0, q);

        while(!q.isEmpty()) System.out.print(q.poll().self.val + " ");

    }

    //add tree nodes in the in-order sequence into the pqueue
    public static void processNodesByColumns(TreeNode node, int x,int y, PriorityQueue<NodeGrid> q) {

        if(node == null) return;
        q.add(new NodeGrid(node, x, y));
        processNodesByColumns(node.left, x - 1, y + 1, q);
        processNodesByColumns(node.right, x + 1, y + 1,q);

    }
}


class NodeGrid implements Comparable<NodeGrid> {

    TreeNode self;
    int x;
    int y;

    public NodeGrid (TreeNode self, int x, int y) {

        this.self = self;
        this.x = x;
        this.y = y;

    }

    public int compareTo(NodeGrid o) {   //sort the queue based on x grid and then y
        if(x != o.x) return x - o.x;
        else return y - o.y;
    }
}