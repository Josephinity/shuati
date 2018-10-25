import java.util.*;
/**
 Given a matrix with hurdles, find the longest possible route from the start to the end;
 */
public class FindLongestRouteInMatrix {

    public int findLongestRoute(boolean[][] matrix) {

        return findLongestRoute(matrix, 0, 0) + 1;

    }

    public int findLongestRoute(boolean[][] matrix, int x, int y) {

        int m = matrix.length - 1, n = matrix[0].length - 1;

        if(x == m && y == n) return -1;

        int maxLen = -1;

        matrix[x][y] = false;

        if(x < m && matrix[x + 1][y]) {
            maxLen = Math.max(maxLen, findLongestRoute(matrix, x + 1, y));
        }

        if(x > 0 && matrix[x - 1][y]) {
            maxLen = Math.max(maxLen, findLongestRoute(matrix, x - 1, y));
        }

        if(y < n && matrix[x][y + 1]) {
            maxLen = Math.max(maxLen, findLongestRoute(matrix, x, y + 1));
        }

        if(y > 0 && matrix[x][y - 1]) {
            maxLen = Math.max(maxLen, findLongestRoute(matrix, x, y - 1));
        }

        matrix[x][y] = true;

        return maxLen == -1 ? -1 : maxLen + 1;
    }

}
