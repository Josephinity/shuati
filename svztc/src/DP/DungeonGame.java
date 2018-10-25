package DP;

/**
 The knight needs to get pass a dungeon where each cell has either an orb to recover hp or a monster to bring down hp.
 To pass the dungeon, the warrior needs to maintain at least 1 hp through the dungeon.
 */
public class DungeonGame {

    public int initialHealth(int[][] dungeon) {

        int m = dungeon.length, n = dungeon[0].length;

        int[][] dp = new int[m][n];

        dp[m - 1][n -1] = Math.max(1, 1 - dungeon[m - 1][n -1]);  //rule to get initial health

        for(int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }

        for(int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(1, dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }

        for(int i = m - 2; i >= 0; i--) {
            for(int j = n - 2; j >= 0; j--) {
                int from_down = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                int from_right = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                dp[i][j] = Math.min(from_down, from_right);
            }
        }

        for(int[] x : dp) {
            for(int y: x) System.out.print(y + "   ");
            System.out.println();
        }

        return dp[0][0];
    }
}
