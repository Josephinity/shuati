package Amazon;

/**
ief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms an n-nary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Definition for a n-ary tree node.
 * public
 */
import java.util.List;
import java.util.Map;
import java.util.HashMap;
class TreeNode {
    int val;
    List<TreeNode> kids;
}
public class HouseRobTree {
    public int houseRob(TreeNode root) {
        Map<TreeNode, Integer> money_in_tree = new HashMap<>();
        return houseRobHelper(root, money_in_tree);
    }

    private int houseRobHelper(TreeNode root, Map<TreeNode, Integer> money_in_tree) {
        if(root == null) return 0;
        if(root.kids == null || root.kids.isEmpty()) return root.val;
        if(money_in_tree.containsKey(root)) return money_in_tree.get(root);

        int max = 0;
        for(TreeNode kid: root.kids) {
            max = Math.max(max, houseRobHelper(kid, money_in_tree));
            if(kid.kids != null) {
                for(TreeNode grandKid: kid.kids) {
                    max = Math.max(max, houseRobHelper(grandKid, money_in_tree) + root.val);
                }
            }
        }
        money_in_tree.put(root, max);
        return max;
    }
}
