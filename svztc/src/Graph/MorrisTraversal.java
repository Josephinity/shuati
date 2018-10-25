package Graph;

/**
 * Created by xiaobaby on 12/2/16.
 */

class TreeNode {
    int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
    }
}

public class MorrisTraversal {
    public void inorder(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = curr;
                    //pre-order : System.out.print(curr.val + " ");
                    curr = curr.left;
                } else {
                    prev.right = null;
                    System.out.print(curr.val + " ");  //pre-order: remove this
                    curr = curr.right;
                }
            }
        }
    }
}
