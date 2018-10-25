package Amazon;

/**
Given an array of positive numbers, find whether there exists a subset in arr whose sum is n. Print the length of the subset.

 */
public class NSum {
    public int lengthOfNSum(int[] array, int n) {

        return lengthOfNSumRec(array, n, 0);
    }

    private int lengthOfNSumRec(int[] array, int n, int start) {
        if(n < 0) {
            return -1;
        }
        if(n == 0) {
            return 0;
        }
        for(int i = start; i < array.length; i++) {
            int len = lengthOfNSumRec(array, n - array[i], i + 1);
            if(len >= 0) {
                return len + 1;
            }
        }
        return -1;
    }
}
