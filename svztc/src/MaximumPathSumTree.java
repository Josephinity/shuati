/**
 * Created by xiaobaby on 10/30/16.
 */
public class MaximumPathSumTree {



    //positive only
    public int maximumPathSumTree1(TreeNode root) {
        if(root == null) return 0;
        int left = maximumPathSumTree1(root.left);
        int right = maximumPathSumTree1(root.right);
        return root.val + Math.max(left, right);
    }






    //negative numbers involved
    //res[0] is maxsum, res[1] is maxsumwithCurrNode
    public int[] maximumPathSumTree(TreeNode root) {

        if(root == null) return null;

        int[] left = maximumPathSumTree(root.left);
        int[] right = maximumPathSumTree(root.right);

        int[] res = new int[]{root.val, root.val};

        if(left != null) {
            res[1] = Math.max(left[1] + root.val, root.val);
            res[0] = Math.max(left[0], Math.max(res[0], res[1]));
        }

        if(right != null) {
            res[1] = Math.max(right[1] + root.val, root.val);
            res[0] = Math.max(right[0],Math.max(res[0], res[1]));
        }

        return res;

    }



}
