/**
 * Created by xiaobaby on 4/1/18.
 */
public class FlipIsland2 {

        char[][] boardMatrix = { { 'X', 'X', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'X', 'X', 'O', 'X', 'O' },
                { 'X', 'X', 'O', 'O', 'X', 'X', 'O' }, { '0', 'X', 'O', 'O', 'O', 'O', 'X' }, };


        private void boundaryDFS(char[][] board, int i, int j) {
            if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j]!='X')
                return;
            if (board[i][j] == 'X')
                board[i][j] = '*';
            boundaryDFS(board, i - 1, j);
            boundaryDFS(board, i + 1, j);
            boundaryDFS(board, i, j - 1);
            boundaryDFS(board, i, j + 1);
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
            // Start from first and last row, turn ‘X' to '*'
            for (int j = 0; j < n; j++) {
                boundaryDFS(board, 0, j);
                boundaryDFS(board, m - 1, j);
            }
            for (int i = 0; i < boardMatrix.length; i++) {
                for (int j = 0; j < boardMatrix[i].length; j++) {
                    System.out.print(boardMatrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            // post-prcessing, turn 'X' to ‘O', '*' back to 'X', keep 'O' intact.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'X')
                        board[i][j] = 'O';
                    else if (board[i][j] == '*')
                        board[i][j] = 'X';
                }
            }
        }

        public static void main(String args[]) {
            FlipIsland2 fi = new FlipIsland2();
            for (int i = 0; i < fi.boardMatrix.length; i++) {
                for (int j = 0; j < fi.boardMatrix[i].length; j++) {
                    System.out.print(fi.boardMatrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("After ...");
            fi.solve(fi.boardMatrix);
            for (int i = 0; i < fi.boardMatrix.length; i++) {
                for (int j = 0; j < fi.boardMatrix[i].length; j++) {
                    System.out.print(fi.boardMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }



}
