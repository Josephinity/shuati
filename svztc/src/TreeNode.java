/**
 * Created by xiaobaby on 10/25/16.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int n) {
        val = n;
    }

    public boolean isLeaf() {
        if(left == null && right == null) return true;
        else return false;
    }
}
