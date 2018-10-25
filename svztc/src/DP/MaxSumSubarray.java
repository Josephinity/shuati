package DP;

/**
 https://leetcode.com/problems/maximum-subarray/#/description
 */
public class MaxSumSubarray {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int max = nums[0], maxCurr = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxCurr = Math.max(nums[i], maxCurr + nums[i]);
            max = Math.max(max, maxCurr);
        }
        return max;
    }
}
