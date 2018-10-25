package DP;
import java.util.List;
import java.util.ArrayList;
/**
The difference between finding combinations and permutations
 */

public class CombVSPerm {

    public static void main(String args[]) {

        CombVSPerm t = new CombVSPerm();
        t.permutation(4, new int[]{1,2,3});

        System.out.println(t.res);

    }

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    void combination(int target, int[] nums, int i) {

        if(0 > target || i == nums.length) return;

        if(0 == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        combination(target, nums, i + 1);

        int k = 0;
        while(nums[i] <= target) {

            path.add(nums[i]);
            target -= nums[i];
            k++;
            combination(target, nums, i + 1);

        }

        while(k > 0) {
            path.remove(path.size() - 1);
            k--;
        }

    }

    void permutation(int target, int[] nums) {

        if(0 > target) return;
        if(0 == target) {
            res.add(new ArrayList<>(path));
        }

        for(int i = 0; i < nums.length; i++) {
            path.add(nums[i]);
            permutation(target - nums[i], nums);
            path.remove(path.size() - 1);
        }

    }

}
