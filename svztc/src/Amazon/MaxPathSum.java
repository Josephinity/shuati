package Amazon;

/**
 Given a matrix of integers, start from any column of the  first row. can only move diagonally left, diagonally right and down.
 Find the max sum possible. This is a DP problem.
 */
public class MaxPathSum {
    int maxSum(int[][] matrix) {
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = max(j == 0 ? 0: matrix[i - 1][j - 1],
                        matrix[i - 1][j],
                        j == matrix[0].length - 1 ? 0: matrix[i - 1][j + 1]);
            }
        }
        return max(matrix[matrix.length - 1]);
    }

    int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    int max(int[] nums) {
        int res = 0;
        for(int n: nums) {
            if(res < n) res = n;
        }
        return res;
    }
}
