package Tree;

/**
 Print a tree level by level in the zigzag order
 */
import apple.laf.JRSUIUtils;

import java.util.Stack;

public class PrintTreeSpiralOrder {

    public void printZigzag(TreeNode root) {

        if(root == null) return;

        Stack<TreeNode> left_to_right = new Stack<>();
        Stack<TreeNode> right_to_left = new Stack<>();

        right_to_left.push(root);

        while(!left_to_right.isEmpty() || !right_to_left.isEmpty()) {

            if(!left_to_right.isEmpty()) {

                while(!left_to_right.isEmpty()) {

                    TreeNode n = left_to_right.pop();

                    if(n.right != null) right_to_left.push(n.right);
                    if(n.left != null) right_to_left.push(n.left);

                    System.out.print(n.val + " ");

                }

            } else{

                while(!right_to_left.isEmpty()) {

                    TreeNode n = left_to_right.pop();

                    if(n.left != null) left_to_right.push(n.left);
                    if(n.right != null) left_to_right.push(n.right);

                    System.out.print(n.val + " ");

                }

            }
        }
    }
}
