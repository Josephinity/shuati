package Google;
import java.util.*;

/*
            {1 ,0, 0, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 1, 0},
            {1, 1, 1, 1, 0},
 */

public class SweepingRobot {

    final int[][] directions;
    private Robot r;

    public SweepingRobot(int[][] m, int i, int j) {
        this.r = new Robot(m, i, j);
        directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }

    public void sweep() {
        VisitedSet set = new VisitedSet();
        set.add(0, 0);
        System.out.println("(0,0)");
        for(int d = 0; d <= 3; d++) {
            dfs(this.r, set, 0, 0, d);
            r.rotate();
        }
    }

    private void dfs(Robot r, VisitedSet set, int i, int j, int direction) {
        for(int k = 0; k <= 3; k++) {
            if (!set.visited(i + directions[direction][0], j + directions[direction][1]) && r.move()) {
                set.add(i + directions[direction][0], j + directions[direction][1]);
                System.out.println("(" + (i + directions[direction][0]) + "," + (j + directions[direction][1]) + ") ");
                dfs(r, set, i + directions[direction][0], j + directions[direction][1], direction);
            }
            r.rotate();
            direction = (direction + 1) % 4;
        }
        //backtrack
        r.rotate();
        r.rotate();
        r.move();
        r.rotate();
        r.rotate();
    }


    private class VisitedSet {

        Map<Integer, Set<Integer>> set;

        VisitedSet() {
            set = new HashMap<>();
        }

        boolean visited(int i, int j) {
            if(set.containsKey(i) && set.get(i).contains(j)) return true;
            return false;
        }

        void add(int i, int j) {
            Set<Integer> y = set.getOrDefault(i, new HashSet<>());
            y.add(j);
            set.put(i, y);
        }

    }


    class Robot {

        int facing; //0 - up, 1 - right, 2 - down, 3 - left
        int x, y;
        int[][] room;

        Robot(int[][] m, int i, int j) {
            facing = 0;
            room = m;
            x = i;
            y = j;
        }

        public boolean move() {
            int nx = x + directions[facing][0];
            int ny = y + directions[facing][1];
            if(nx < 0 || ny < 0 || nx >= room.length || ny >= room[0].length) return false;
            if(room[nx][ny] == 0) return false;
            x = nx;
            y = ny;
            return true;
        }

        public void rotate() {
            facing++;
            facing %= 4;
        }
    }

}
