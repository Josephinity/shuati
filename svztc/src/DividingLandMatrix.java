import java.util.Scanner;

/**
 divide matrix with 3 cuts in row and 3 cuts in col.
 make the smallest piece within the 16 pieces of segments the biggest possible.
 */
public class DividingLandMatrix {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[][] land = new int[m][n];
        for(int i = 0; i < m; i++) {
            String row = sc.next();
            for(int j = 0; j < n; j++) {
                land[i][j] = row.charAt(j) - '0';
            }
        }
        System.out.println(cutLand(land));
    }
    public static int cutLand(int[][] land) {
        int m = land.length, n = land[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + land[i - 1][j - 1];
            }
        }
        int min = 0, max = sum[m][n];
        while(min < max - 1) {
            int mid = (min + max) / 2;
            System.out.println(mid);
            if(isValid(mid, land, sum)) {
                min = mid;
            }
            else max = mid;
            if(min == max - 1) break;
        }
        return min;
    }

    public static boolean isValid(int min, int[][] land, int[][] sum) {
        int m = land.length, n = land[0].length;
        for(int i = 1; i < m - 2; i++) {
            for(int j = i + 1; j < m - 1; j++) {
                for(int k = j + 1; k < m; k++) {
                    int cutCount = 0, prevRow = 0;
                    for(int r = 1; r <= n - (3 - cutCount); r++) {
                        if( getArea(prevRow, r, 0, i, sum) >= min &&
                                getArea(prevRow, r, i, j, sum) >= min &&
                                getArea(prevRow, r, j, k, sum) >= min &&
                                getArea(prevRow, r, k, m, sum) >= min) {

                            cutCount++;
                            if (cutCount == 4) {
                                return true;
                            }
                            prevRow = r;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int getArea(int prevRow, int row, int prevCol, int col, int[][] sum) {
        return sum[col][row] - sum[prevCol][row] - sum[col][prevRow] + sum[prevCol][prevRow];
    }


}
