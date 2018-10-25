package Next;

/**
 spiral matrix
 */
public class SpiralMatrix {

        public static void main(String args[]) {
            printMatrix(9);
        }

        private static void printMatrix(int n) {
            int[][] matrix = new int[n][n];
            int num = 1;
            for (int layer = 0; layer < n / 2; layer++) {
                int count = n - 1 - 2 * layer;
                for (int i = 0; i < count; i++) {
                    matrix[layer][layer + i] = num + i;
                    matrix[layer + i][n - layer - 1] = num + count + i;
                    matrix[n - layer - 1][n - layer - 1 - i] = num + 2 * count + i;
                    matrix[n - layer - i - 1][layer] = num + 3 * count + i;
                }
                num += 4 * count;
            }
            if(n % 2 == 1) matrix[n / 2][n / 2] = num;
            System.out.println();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
        }
}
