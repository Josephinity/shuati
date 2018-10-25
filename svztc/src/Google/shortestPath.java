package Google;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 Round 3
 Given a matrix of 0s and 1s where 0 is wall and 1 is pathway, print the shortest path from the first row to the last row.
 Can walk to the left, top, right, bottom at any given spot.

 Follow-up:
 If every pathway takes a cost (positive integer) to get through, print the minimum cost path from the first row to the last row.

 */
public class shortestPath {

    public static void main(String[] args) {
        shortestPath sp = new shortestPath();
        int[][] m = new int[][] {
                {1,5,0,1},
                {2,1,1,0},
                {1,1,1,2},
                {0,6,4,1}
        };
        sp.minCostPath(m);
    }

    //Round 3
    public void shortestPath(int[][] matrix) {
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>(); //key: current location; value: the previous node from where it gets to the current location
        for(int j = 0; j < matrix[0].length; j++) { //put first row into queue
            if(matrix[0][j] == 1) {
                queue.add(j);
                map.put(j, -1);
            }
        }
        int destination = -1;
        while(!queue.isEmpty()) {
            int id = queue.poll();
            int fromX = id / matrix[0].length, fromY = id % matrix[0].length;
            if(fromX + 1 < matrix.length)
                if(moveTo(queue, map, matrix, id, fromX + 1, fromY) && fromX + 1 == matrix.length - 1) {
                    destination = id + matrix[0].length;    //reach the last row
                    break;
                }
            if(fromY + 1 < matrix[0].length)
                moveTo(queue, map, matrix, id, fromX, fromY + 1);
            if(fromX - 1 >= 0)
                moveTo(queue, map, matrix, id, fromX - 1, fromY);
            if(fromY - 1 >= 0)
                moveTo(queue, map, matrix, id, fromX, fromY - 1);
        }
        if(destination == -1) return; //no such path from first row to last row
        while(map.containsKey(destination)) {   //print shortest path from destination to source
            int x = destination / matrix[0].length, y = destination % matrix[0].length;
            System.out.println("(" + x + ","+ y + "),");
            destination = map.get(destination);
        }
    }

    private boolean moveTo(Queue<Integer> queue, Map<Integer, Integer> map, int[][] matrix, int from, int x, int y) {
        if(matrix[x][y] == 0) return false;
        int id = x * matrix[0].length + y;
        if(!map.containsKey(id)) {
            map.put(id, from);
            queue.add(id);
            return true;
        }
        return false;
    }

    //Follow-up
    public void minCostPath(int[][] matrix) {
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, int[]> map = new HashMap<>(); //key: current location; value[0]: the previous node from where it gets to the current location, value[1]:total cost up to current node
        for(int j = 0; j < matrix[0].length; j++) { //put first row into queue
            if(matrix[0][j] != 0) {
                queue.add(j);
                map.put(j, new int[] {-1, matrix[0][j]});
            }
        }
        int destination = -1, minCost = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            int id = queue.poll();
            int fromX = id / matrix[0].length, fromY = id % matrix[0].length;
            if(fromX + 1 < matrix.length) {
                int cost = moveMinCost(queue, map, matrix, id, fromX + 1, fromY);
                if (cost >= 0 && fromX + 1 == matrix.length - 1) {
                    if (cost < minCost) {
                        destination = id + matrix[0].length;
                        minCost = cost;
                    }
                }
            }
            if(fromY + 1 < matrix[0].length)
                moveMinCost(queue, map, matrix, id, fromX, fromY + 1);
            if(fromX - 1 >= 0)
                moveMinCost(queue, map, matrix, id, fromX - 1, fromY);
            if(fromY - 1 >= 0)
                moveMinCost(queue, map, matrix, id, fromX, fromY - 1);
        }
        if(destination == -1) return; //no such path from first row to last row
        while(map.containsKey(destination)) {   //print shortest path from destination to source
            int x = destination / matrix[0].length, y = destination % matrix[0].length;
            System.out.println("(" + x + ","+ y + "),");
            destination = map.get(destination)[0];
        }
    }

    private int moveMinCost(Queue<Integer> queue, Map<Integer, int[]> map, int[][] matrix, int from, int x, int y) {
        if(matrix[x][y] == 0) return -1;
        int id = x * matrix[0].length + y;
        int cost = map.get(from)[1] + matrix[x][y];
        if(!map.containsKey(id) || map.get(id)[1] > cost) {
            map.put(id, new int[]{from, cost});
            queue.add(id);
            return cost;
        }
        return -1;
    }
}
