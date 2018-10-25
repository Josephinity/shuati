package FB;


/** Yahoo
 how to convert a binary search tree to a double linked list
 */
//# Definition for a binary tree node.
//        # class TreeNode(object):
//        #     def __init__(self, x):
//        #         self.val = x
//        #         self.left = None
//        #         self.right = None

//  def FB.BSTtoDLL(root)

public class BSTtoDLL {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
    }

    public static TreeNode[] BSTtoDLL (TreeNode root) {

        if(root == null) return null;

        TreeNode[] prev = BSTtoDLL(root.left);
        TreeNode[] next = BSTtoDLL(root.right);

        TreeNode[] res = new TreeNode[] {root, root};

        if(prev != null) {
            prev[1].right = root;
            root.left = prev[1];
            res[0] = prev[0];
        }

        if(next != null) {
            next[0].left = root;
            root.right = next[0];
            res[1] = next[1];
        }
        return res;

    }
}
