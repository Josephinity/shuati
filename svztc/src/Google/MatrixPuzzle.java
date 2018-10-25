package Google;

/**
 dp题。给一个grid的宽和长，求得从左下的点到右下的点所有可能的路径之和。
 起点当然是左下，要求每次只能走三个方向， ➡↗↘
 follow-up: 2d dp to 1d dp

 DP Problem. Given the length and width of a matrix, get the number of paths from bottom-left to bottom right.
 You may only walk into those 3 directions ➡ (right) ↗ (upper-right) ↘ (lower-right) at each point.
 Follow-up: optimize 2d DP to 1d DP of linear extra space.
 Follow-up: what if some cells are blocked
 */
public class MatrixPuzzle {

    int column;
    int row;

//    public static void main(String[] args) {
//        MatrixPuzzle maze = new MatrixPuzzle(5,6);
//        maze.numberOfPaths();
//    }

    public MatrixPuzzle(int length, int width) {
        this.column = length;
        this.row = width;
    }

    //The dp formula will be M[i,j] = M[i - 1, j - 1] + M[i - 1, j] + M[i - 1, j + 1]
    //Because one could only land on current cell from the 3 cells in the upper-left, left and lower-left.
    //To make the space consumption 1d, cache the numbers in one column of the matrix at a time.
    //Follow-up 2: Just reset path-counts for blocked cells to 0
    public int numberOfPaths() {
        int[] paths = new int[row];
        paths[row - 1] = 1; //start from bottom-left corner
        for(int col = 1; col < column; col++) {
            int upper_left_value = 0;
            for(int r = 0; r < row; r++) {
                int left_value = paths[r];
                paths[r] += upper_left_value + (r == row - 1 ? 0 : paths[r + 1]);
                upper_left_value = left_value;
            }
        }

        return paths[row - 1]; //exit from bottom-right
    }
}
