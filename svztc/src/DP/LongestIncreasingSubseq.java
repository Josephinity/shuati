package DP;
import java.util.Arrays;
/**
 * Created by xiaobaby on 3/23/17.
 */
import java.util.ArrayList;
import java.util.List;
public class LongestIncreasingSubseq {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; // initialized as Integer.MIN_VALUE
        int len = 0;
        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++; //when x is the greatest by far
        }
        return len;
    }

    //Find the longest increasing sequence of strings in a set of strings.
    public List<String> lengthOfLIS(List<String> strs) {
        List<String> sequence = new ArrayList<>(); // initialized as Integer.MIN_VALUE
        int len = 0;
        for (String s: strs) {
            int i = binarySearch(sequence, 0, len, s);
            sequence.set(i, s);
            if(i == len) len++; //when x is the greatest by far
        }
        return sequence;
    }

    //search for rank of s in sequence
    private int binarySearch(List<String> sequence, int lo, int hi, String s) {
        if(sequence.isEmpty() || s.compareTo(sequence.get(0)) <= 0) return 0;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            int comparison = s.compareTo(sequence.get(mid));
            if(comparison == 0) {
                return mid;
            } else if(comparison > 0) {
                lo = mid + 1;
            } else {
                if(s.compareTo(sequence.get(mid - 1)) > 0) { //sequence[mid - 1] < s < sequence[mid]
                    return mid;
                }
                hi = mid - 1;
            }
        }
        return lo; //s is greater than any strings in sequence
    }

// Find LIS for string array
//    public &lt;String&gt; lengthOfLIS(String[] strs) {
//        List&lt;String&gt; dp = new ArrayList<>(); // initialized as Integer.MIN_VALUE
//        int len = 0;
//        for(String x : strs) {
//            int i = binarySearch(dp, len, x);
//            if(i &lt; 0) i = -(i + 1);
//            dp.set(i, x);
//            if(i == len) len++; //when x is the greatest by far
//        }
//        return dp;
//    }
//
//    private int binarySearch(List&lt;String&gt; l, int size, String target) {
//        int lo = 0, hi = size;
//        while(lo &lt; hi) {
//            int mid = (lo + hi) / 2;
//            if(l.get(mid).equals(target)) {
//                return mid;
//            } else if(compare(l.get(mid), target) &gt; 0) {
//                if(mid == 0 || compare(l.get(mid - 1), target) &lt; 0) return -mid - 1;
//                hi = mid;
//            } else lo = mid + 1;
//        }
//        return -1;
//    }
}
