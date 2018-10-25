/**
 * Created by xiaobaby on 4/12/17.
 */
public class ValidBST {
    boolean validBST(TreeNode root) {
        if(root == null) return true;
        if(root.left != null && root.left.val > root.val) return false;
        if(root.right != null && root.right.val < root.val) return false;
        return validBST(root.left) && validBST(root.right);
    }
}
