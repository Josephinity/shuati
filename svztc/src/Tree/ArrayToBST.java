package Tree;

import java.util.Stack;

/** Yahoo
 Given a pre-order traversal of a BST represented by an array of integers, generate the tree
 */

//5 1 6 7 9 8 10

public class ArrayToBST {



    public static TreeNode arrayToBST(int[] array) {
        if(array.length == 0) return null;
        TreeNode root = new TreeNode(array[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for(int i = 1; i < array.length; i++) {
            int prev = stack.peek().val;
            TreeNode n = new TreeNode(array[i]);
            if(array[i] <= prev) {
                root.left = n;
            } else {
                TreeNode parent = stack.pop();
                while(!stack.isEmpty() && stack.peek().val < array[i]) {
                    parent = stack.pop();
                }
                parent.right = n;
            }
            stack.push(n);
        }
        return root;
    }
}
