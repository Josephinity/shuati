import java.util.Arrays;

/**
 Find greatest plus in matrix
 */
public class findBiggestPlusInMatrix {

    public void findBiggestPlusInMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] max = new int[n][m];
        int greater = n > m? n: m;
        for(int[] arr: max) {
            Arrays.fill(arr, greater);
        }
        for(int i = 0; i < m; i++) {
            int len = 0;
            for(int j = 0; j < n; j++) {
                if(matrix[j][i] == 0) {
                    len = 0;
                } else {
                    len++;
                }
                max[j][i] = Math.min(max[j][i], len);
            }
        }

        for(int i = 0; i < m; i++) {
            int len = 0;
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[j][i] == 0) {
                    len = 0;
                } else {
                    len++;
                }
                max[j][i] = Math.min(max[j][i], len);
            }
        }

        for(int i = 0; i < n; i++) {
            int len = 0;
            for(int j = m - 1; j >= 0; j--) {
                if(matrix[i][j] == 0) {
                    len = 0;
                } else {
                    len++;
                }
                max[i][j] = Math.min(max[i][j], len);
            }
        }
        int x = 0, y = 0, min = 0;
        for(int i = 0; i < n; i++) {
            int len = 0;
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == 0) {
                    len = 0;
                } else {
                    len++;
                }
                max[i][j] = Math.min(max[i][j], len);
                if(max[i][j] > min) {
                    min = max[i][j];
                    x = i; y = j;
                }
            }
        }
        System.out.println("At position i = " + x+", j = " + y + " , max cross edge = " + min);

    }
}
