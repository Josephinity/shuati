package FB;

/**

 */
public class SparseMatrixMultiplication {

    public static void main(String[] args) {
        SparseMatrixMultiplication s = new SparseMatrixMultiplication();
        int[][] res = s.multiply(new int[][] {{1,0,0},{-1,0,3}}, new int[][]{{7,0,0},{0,0,0},{0,0,1}});
        for(int[] j:res) {
            for(int i : j) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

        public int[][] multiply(int[][] A, int[][] B) {
            int m = A.length, n = A[0].length, l = B[0].length;
            int[][] C = new int[m][l];

            for(int i = 0; i < m; i++) {
                for(int k = 0; k < n; k++) {
                    if (A[i][k] != 0){
                        for (int j = 0; j < l; j++) {
                            if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
            return C;
        }

}
