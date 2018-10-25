package Tree;
import java.util.*;
/**
 balance a binary search tree
 by converting it into an array and reconstruct the tree
 */
public class BalanceBST {

    class Node {
        int val;
        Node left, right;
    }


    public Node balanceBinarySearchTree(Node root) {
        List<Node> inorderTraversal = new ArrayList<>();
        inorder(root, inorderTraversal);
        return buildTree(0, inorderTraversal.size() - 1, inorderTraversal);
    }

    private void inorder(Node root, List<Node> list) {
        if(root == null) return;
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }

    private Node buildTree(int start, int end, List<Node> list) {
        if(start > end) return null;
        if(start == end) {
            return list.get(start);
        }
        int mid = (start + end) / 2;
        Node root = list.get(mid);
        root.left = buildTree(start, mid - 1, list);
        root.right = buildTree(mid + 1, end, list);
        return root;
    }
}
