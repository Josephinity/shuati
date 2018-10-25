package Google;

/**
 * Phone Interview
 Finding the perimeter of an island in a 2D grid given a point on the island.
 Complexity and follow up if the 2D grid was millions of nodes. Complexity when there are a lot of local searches.



 Each point in the grid is either land or sea.
 A point at the border of island must be land and it must have at least 1 neighboring point of sea.
 Given a random point in the island, first thing is to reach the border of the island by keep going into one direction until the border is met.
 To get the perimeter, run depth first search on border points and get the count of border points.

 Complexity for the search will be O(find border from initial point +  size of perimeter). Wouldn't need to search every point in island.


Solution
 This is a  graph traversal problem. Can be done with DFS. It takes the time of O(len of the perimeter), since we did not have to traverse every node in the island but only the border. So at first the traversal will go into one direction until it hits the border. Then just move on to the neighbor nodes at the border with a DFS. Identify a node at the border as it has at least 1 adjacent cell that is not land but water.
 */
import java.util.*;
abstract class Grid { //a structure like this should be given as the input to tell which node is land or sea,
            //discuss with the interviewer what he had in mind as a proper input
    abstract public boolean isLand(int x, int y);
}
public class PerimeterOfIsland {

    Grid grid; //input

    class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this. y = y;
        }
    }

    private Point findBorder(int x, int y) {
        while(!atBorder(x, y)) {
            x--;
        }
        return new Point(x, y);
    }

    private boolean atBorder(int x, int y) {
        if(grid.isLand(x - 1, y)
            && grid.isLand(x, y + 1)
            && grid.isLand(x + 1, y)
            && grid.isLand(x, y - 1)
            && grid.isLand(x + 1, y + 1)
            && grid.isLand(x - 1, y - 1)
            && grid.isLand(x + 1, y - 1)
            && grid.isLand(x - 1, y + 1))
            return false;
        return true;
    }

    public int getPerimeter(Point p) {
        if(!grid.isLand(p.x, p.y)) return 0;
        Point borderPoint = findBorder(p.x, p.y);
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        return dfs(borderPoint.x, borderPoint.y, visited);
    }

    private int dfs(int x, int y, Map<Integer, Set<Integer>> visited) {
        if(!grid.isLand(x, y) || !atBorder(x, y)) return 0; //(x, y) is not a point at the border
        if(visited.containsKey(x) && visited.get(x).contains(y)) { //(x, y) was counted in previous
            return 0;
        }
        int len = 1;
        if(!visited.containsKey(x)) visited.put(x, new HashSet<Integer>());
        visited.get(x).add(y); //mark (x, y) visited
        len += dfs(x + 1, y, visited);
        len += dfs(x - 1, y, visited);
        len += dfs(x, y + 1, visited);
        len += dfs(x, y - 1, visited);
        len += dfs(x + 1, y + 1, visited);
        len += dfs(x + 1, y - 1, visited);
        len += dfs(x - 1, y + 1, visited);
        len += dfs(x - 1, y - 1, visited);
        return len;
    }
}
