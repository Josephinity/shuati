/**
 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 Return 6.

 */

public class MaxRectangle {

    public int maximalRectangle(char[][] matrix) {

        if(matrix.length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int[][] sum = new int[n][m];

        for(int j = 0; j < m; j++) {
            for(int i = n - 1; i >= 0; i--) {
                if(i == n - 1) {
                    sum[i][j] = matrix[i][j] == '1'? 1: 0;
                } else {
                    sum[i][j] = matrix[i][j] == '0'? 0: 1 + sum[i + 1][j];
                }
            }
        }
        // [
        //   40000       i = 0, j = 0, min = 4, max = 4
        //   30232       i = 1, j = 2, min = 2,
        //   21121
        //   10010
        //     ]
        // ["10100",
        //  "10111"
        //  "11111",
        //  "10010"]

        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int min = sum[i][j];
                for(int k = j; k < m; k++) {
                    if(sum[i][k] == 0) {
                        max = Math.max((k - j) * min, max);
                        break;
                    } else {
                        min = Math.min(min, sum[i][k]);
                        max = Math.max(max, (k - j + 1) * min);
                    }
                }
            }
        }
        return max;
    }
}
