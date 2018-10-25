package Amazon;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
/**April 1 Seattle on-site
find the left view of binary tree
 */
public class LeftViewBT {
    class TreeNode {
        int val;
        TreeNode left, right;
    }
    public List<Integer> leftView(TreeNode root) {
        List<Integer> leftview = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root != null) q.add(root);
        while(!q.isEmpty()) {
            leftview.add(q.peek().val);
            Queue<TreeNode> nextLevel = new LinkedList<>();
            while(!q.isEmpty()) {
                TreeNode node = q.poll();
                if(node.left != null) nextLevel.add(node.left);
                if(node.right != null) nextLevel.add(node.right);
            }
            q = nextLevel;
        }
        return leftview;
    }
    public void nextPermutation(int[] nums) {
        if(nums.length <= 1){
            return;
        }
        int i = nums.length - 1;
        while(i >= 1 && nums[i] <= nums[i-1]) i--; //find first number which is smaller than it's after number
        if(i!=0) swap(nums,i-1); //if the number exist,which means that the nums not like{5,4,3,2,1}
        reverse(nums,i);
    }

    private void swap(int[] a,int i){
        for(int j = a.length - 1;j > i;j--){
            if(a[j] > a[i]){
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
                break;
            }
        }
    }

    private void reverse(int[] a,int i){//reverse the number after the number we have found
        int first = i;
        int last = a.length - 1;
        while(first<last){
            int t = a[first];
            a[first] = a[last];
            a[last] = t;
            first++;
            last--;
        }
    }

}
