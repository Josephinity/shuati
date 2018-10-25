/**
 */
public class FlipIsland {


    public static void main(String args[]) {
        FlipIsland fi = new FlipIsland();
        for(int i =0;i<fi.boardMatrix.length;i++) {
            for(int j =0;j<fi.boardMatrix[i].length;j++) {
                System.out.print(fi.boardMatrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("After ...");
        fi.solve(fi.boardMatrix);


    }

        /**

         Flip islands into the sea.
         X marks land and O marks water. Out of the map area are all lands.
         Node connected to the outer area are peninsula
         XXOOOOO        XXOOOOO
         OOXXOXO    --> OOOOOOO
         XXOOXXO    --> XXOOOOO
         OXOOOOX        OXOOOOX

         * @param board
         * @param i
         * @param j
         */

        char[][] boardMatrix = {{'X','X','O','O','O','O','O'},
                                {'0','0','X','X','O','X','O'},
                                {'X','X','O','O','X','X','O'},
                                {'0','X','O','O','O','O','X'},
        };
        // Use DFS algo to turn internal however boundary-connected 'O' to
        private void boundaryDFS(char[][] board, int i, int j) {
            if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] == 'O')
                return;
            if (board[i][j] == 'X')
                board[i][j] = '*';
            if (i > 1 && board[i-1][j] == 'X')
                boundaryDFS(board, i-1, j);
            if (i < board.length - 2 && board[i+1][j] == 'X')
                boundaryDFS(board, i+1, j);
            if (j > 1 && board[i][j-1] == 'X')
                boundaryDFS(board, i, j-1);
            if (j < board[i].length - 2 && board[i][j+1] == 'X' )
                boundaryDFS(board, i, j+1);
        }

        public void solve(char[][] board) {
            if (board.length == 0 || board[0].length == 0)
                return;
            if (board.length < 2 || board[0].length < 2)
                return;
            int m = board.length, n = board[0].length;
            // Any 'O' connected to a boundary can't be turned to 'X', so ...
            // Start from first and last column, turn 'O' to '*'.
            for (int i = 0; i < m; i++) {
                boundaryDFS(board, i, 0);
                boundaryDFS(board, i, n - 1);
            }
            // Start from first and last row, turn '0' to '*'
            for (int j = 0; j < n; j++) {
                boundaryDFS(board, 0, j);
                boundaryDFS(board, m - 1, j);
            }
            System.out.println();

            for(int i =0;i<board.length;i++) {
                for(int j =0;j<board[i].length;j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();


            // post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'X')
                        board[i][j] = '0';
                    else if (board[i][j] == '*')
                        board[i][j] = 'X';
                }
            }

            for(int i =0;i<board.length;i++) {
                for(int j =0;j<board[i].length;j++) {
                    System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
        }



}
