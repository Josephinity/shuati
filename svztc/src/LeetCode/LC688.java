package LeetCode;

/**
 688. Knight Probability in Chessboard
 */
public class LC688 {
/*
XXXXX
XXXXX
XXXXX
XXXXX
XXXXX
      */
    int[][] steps = new int[][]{{1, 2}, {2, 1}, {-1, 2}, {1, -2}, {2, -1}, {-2, 1}, {-1, -2}, {-2, -1}};

    public double knightProbability(int N, int K, int r, int c) {
        int[][] board = new int[N][N];
        for (int[] step : steps) {
            if (inrange(r + step[0], c + step[1], N)) board[r + step[0]][c + step[1]]++;
        }
        int s = K;
        K--;
        while(K > 0) {
            int[][] next = new int[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    for(int[] step: steps) {
                        if(inrange(i + step[0], j + step[1], N)) {
                            next[i][j] += board[i + step[0]][j + step[1]];

                        }
                    }
                }
            }
            K--;
            board = next;
        }
        return 1.0*board[r][c]/Math.pow(8, s);
    }

    private boolean inrange(int i, int j, int N) {
        if(i < 0 || j < 0 || i >= N || j >= N) return false;
        return true;
    }

    private void print(int[][] board) {
        for(int[] a: board) {
            for(int b: a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

    }
}
