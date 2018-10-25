package DP;

/**
 * Given a matrix with positive integers, you may walk from the top left corner to the bottom right. You may only move down
 * or move right.
 * What's the maximum sum of numbers that you will walk through.
 *
 * Tim
 */
public class UniquePath {

    public int walkSum(int[][] m) {
        int[][] dp = new int[m.length + 1][m[0].length + 1];
        for(int i = 1; i <= m.length; i++) {
            for(int j = 1; j <= m[0].length; j++) {
                dp[i][j] = m[i - 1][j - 1] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m.length][m[0].length];
    }


    // follow up, what if you friend walk the matrix with you together. The value of any cell will be set to 0 once a person
    // steps on the cell. What's the maximum sum of numbers that the two of you walk through in total.


}
