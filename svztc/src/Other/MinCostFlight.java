package Other;
import java.util.*;



/**
 *
 * Airbnb
 *
 *
 * 1. mincost of flight from start to end if allowed at most k connections. 比如：
 A->B, 100,
 B->C, 100,
 A->C, 500.
 如果k是1的话，起点终点是A，C的话，那A->B->C最好, 我只想到BFS的解法。
 2. Text justification
 3. Design key-value store
 4. project deep dive.


 Min cost of flight from start to end if allowed at most k transfers.
 Given all the flights in a string:
 A->B,100,
 B->C,100,
 A->C,500,
 If k = 1，from A to C the best route is A->B->C.

 Dijkstra's shortest path
 */
public class MinCostFlight {
    public int minCost(String flights, String from, String to, int k) {
        if(from == to) return 0;
        if(flights.isEmpty()) return -1;

        int[][] edges = parseString(flights,from, to);
        Map<Integer, Integer> minCostSet = new HashMap<>();
        minCostSet.put(0, 0);

        Queue<Integer> prev = new ArrayDeque<>(); //previous level of nodes
        prev.add(0);

        while(k >= 0 && !prev.isEmpty()) {  //BFS
            Queue<Integer> current = new ArrayDeque<>();
            for(int source: prev) {
                for(int destination = 0; destination < edges.length; destination++) {
                    if(edges[source][destination] > 0) {
                        int minCost = minCostSet.containsKey(destination) ? minCostSet.get(destination) : Integer.MAX_VALUE;
                        int newCost = Math.min(minCost, minCostSet.get(source) + edges[source][destination]);
                        if (minCost > newCost) {
                            minCostSet.put(destination, newCost);
                            current.add(destination);
                        }
                    }
                }
            }
            prev = current;
            k--;
        }
        return minCostSet.containsKey(edges.length - 1) ? minCostSet.get(edges.length - 1): -1;
    }

    private int[][] parseString(String flights, String start, String end) {
        Map<String, Integer> map = new HashMap<>();
        List<int[]> edges = new ArrayList<>();
        String[] f = flights.split(",");
        map.put(start, 0);
        map.put(end, -1);
        for(int i = 0; i < f.length; i+=2) {
            int idx = f[i].indexOf('-');
            String src = f[i].substring(0, idx);
            String des = f[i].substring(idx + 2);
            if(!map.containsKey(src)) {
                map.put(src, map.size() - 1);
            }
            if(!map.containsKey(des)){
                map.put(des, map.size() - 1);
            }
            if(map.get(src) != -1) {    //exclude flights that departs from the ultimate destination
                edges.add(new int[]{map.get(src), map.get(des), Integer.parseInt(f[i + 1])});
            }
        }
        int n = map.size();
        int[][] graph = new int[n][n];
        for(int[] edge: edges) {
            if(edge[1] == -1) edge[1] = n - 1;
            graph[edge[0]][edge[1]] = edge[2];
        }
        return graph;
    }
}
