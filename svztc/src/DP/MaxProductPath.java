package DP;

/**

 */
public class MaxProductPath {
    //the question is vague. is there a fixed start/end for the path. what is the rule for walking down the path?
    //here assume you may only walk down and right from the top left to bottom right corner.
    //assume the scale of product is within the range of an integer

    public int maxProductPath(int[][] matrix) {

        if(matrix.length == 0 || matrix[0].length == 0) return 0;

        if(matrix.length == 1 && matrix[0].length == 1) return matrix[0][0];

        int n = matrix.length, m = matrix[0].length;
        int[][][] mem = new int[matrix.length + 1][matrix[0].length + 1][2];
        //mem[i][j][0] stores current positive max product
        //mem[i][j][1] stores current negative min product

        for(int i = 0; i <= n; i++) {
            mem[i][0][0] = 1;
            mem[i][0][1] = -1;
        }

        for(int j = 0; j <= m; j++) {
            mem[0][j][0] = 1;
            mem[0][j][1] = -1;
        }


        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(matrix[i - 1][j - 1] > 0) {
                    mem[i][j][0] = Math.max(mem[i - 1][j][0], mem[i][j - 1][0]) * mem[i][j][0]; //pos max
                    mem[i][j][1] = Math.min(mem[i - 1][j][1], mem[i][j - 1][1] * mem[i][j][0]); //neg min
                } else {
                    mem[i][j][1] = Math.max(mem[i - 1][j][0], mem[i][j - 1][0]) * mem[i][j][0]; //neg min
                    mem[i][j][0] = Math.min(mem[i - 1][j][1], mem[i][j - 1][1]) * mem[i][j][0]; //pos max
                }
            }
        }

        return mem[n][m][0];
    }
}
