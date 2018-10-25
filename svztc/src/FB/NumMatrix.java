package FB;

/**
 *
 * 2017(4-6月)  onsite
Leetcode 304

 Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12

 Note:
 You may assume that the matrix does not change.
 There are many calls to sumRegion function.
 You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class NumMatrix {
    public static void main(String args[]) {
        int[][] m = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix instance = new NumMatrix(m);
    }

    int matrix[][];
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        if(matrix != null && matrix.length != 0) {
            for(int i = 1; i < matrix.length; i++) {
                matrix[i][0] += matrix[i - 1][0];
            }
            for(int j = 1; j < matrix[0].length; j++) {
                matrix[0][j] += matrix[0][j - 1];
            }

            for(int i = 1; i < matrix.length; i++) {
                for(int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] += (matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1]);
                }
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] +  "  ");
            }
            System.out.println();
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int lower = Math.min(matrix.length - 1, row2), upper = row1 - 1;
        int right = Math.min(col2, matrix[0].length - 1), left = col1 - 1;
        return matrix[lower][right] -
                (left == -1 ? 0: matrix[lower][left]) -
                (upper == -1 ? 0: matrix[upper][right]) +
                (left == -1 || upper == -1 ? 0 : matrix[upper][left]);

    }
}
