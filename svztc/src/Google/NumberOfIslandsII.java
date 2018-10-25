package Google;

/**
Graph: UnionFind Leetcode 305

 A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example:

 Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

 0 0 0
 0 0 0
 0 0 0
 Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

 1 0 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

 1 1 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

 1 1 0
 0 0 1   Number of islands = 2
 0 0 0
 Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

 1 1 0
 0 0 1   Number of islands = 3
 0 1 0
 We return the result as an array: [1, 1, 2, 3]

 Challenge:

 Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
import java.util.Arrays;

public class NumberOfIslandsII {

//    public static void main(String args[]) {
//
//        int[][] positions = new int[][] {{0,0}, {0,1}, {1,2}, {2,1},{1, 1}};
//
//        numOfIslands(3,3,positions);
//
//    }

    public static void numOfIslands(int m, int n, int[][] positions) {

        int[] islands = new int[m * n];

        final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        Arrays.fill(islands, -1);//water is marked by -1

        int count = 0;

        for(int[] position: positions) {

            int source = -1;

            int pos_id = n * position[0] + position[1];

            if(islands[pos_id] == -1) count++;

            for(int[] direction: directions) {

                int x = position[0] + direction[0], y = position[1] + direction[1];

                if(x >= 0 && x < m && y >= 0 && y < n) {

                    int connected_pos_id = n * x + y;

                    if (islands[connected_pos_id] != -1) {

                        int root = find(connected_pos_id, islands);

                        if (source == -1) {

                            count--;

                            source = root;

                        } else if (root != source) { //two islands connected by pos_id

                            count--;

                            islands[root] = source;

                        }

                    }
                }

            }

            if(source == -1) islands[pos_id] = pos_id;
            else islands[pos_id] = source;

            System.out.print(count + "  ");

        }

    }

    private static int find(int node, int[] src) {

        int root = node;

        while(root != src[root]) root = src[root];

        while(node != src[node]) { //path compression

            int tmp = src[node];

            src[node] = root;

            node = tmp;

        }

        return root;

    }

}
