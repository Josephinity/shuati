package DP; /**
 Given a list of positive numbers,
 return the smallest positive number that cannot be summed to by the numbers in the list.
 [7, 10, 37, 2, 3, 11, 1] = 35

 [] = 1

 [2] = 1

 */
import java.util.Arrays;
public class ContinuousSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2, 3, 8,10, 27};
        System.out.println(findFirstMissingInt(nums));
        System.out.println(minUnreached(nums));
    }
    public static int minUnreached(int[] nums) {
        if(nums.length == 0 || nums[0] != 1) return 1;
        Arrays.sort(nums);
        int sum = 1, index = 1;
        while(index < nums.length) {
            if(sum + 1 < nums[index]) return sum + 1;
            else {
                sum += nums[index];
            }
            index++;
        }
        return sum + 1;
    }

    public static int findFirstMissingInt(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if(len == 0 || nums[0] != 1) {
            return 1;
        }
        int target = 2;
        int pos = 0;
        while (true) {
            while (pos < len && target > nums[pos]) {
                pos++;  //target = 2 , [1,1, 1]
            }
            if (nums[pos] == target) {
                target++;
                continue;
            }
            pos--;
            int t = target;
            int i = pos;
            while (i>=0 && t - nums[i] >= nums[i]) {
                t -= nums[i];
                i--;
            }
            if (i < 0 && t > 0) {
                return target;
            }
            target++;
        }
    }
}
