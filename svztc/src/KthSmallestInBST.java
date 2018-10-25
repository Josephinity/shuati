/**
 Get kth smallest element in the BST.

 test cases:

 */

public class KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        int[] count = new int[2];  //count[1] is res, count[0] is current k
        kthSmallestElement(root, k, count);
        return count[1];
    }

    private void kthSmallestElement(TreeNode root, int k ,int[] count) {
        if(root == null || count[0] == k) return;
        kthSmallestElement(root.left, k, count);
        if(++count[0] == k) {
            count[1] = root.val;
            return;
        }
        kthSmallestElement(root.right, k, count);
    }
}
