package FB;

/**
 phone:
 postorder traversal recursive -> iterative
 add two binary number


 on-site:
 ring buffer
 merge intervals
 LC alien dictionary
 list of words sorted

 */

import java.util.*;
public class AlexPoon {

    int buffer_size = 0;
    int[] buffer;


    static class Graph {
        Set<Integer> nodes; // 1, 3, 4, 5, 7, 8, 9, 10
        Map<Integer, List<Pair>> pairsStartingWithNode;
        // 1 : [[1,3]], 4: [[4,1],[4,7]]

        public Graph(int[][] pairs) {
            nodes = new HashSet<>();
            pairsStartingWithNode = new HashMap<>();
            for(int[] pair: pairs) {
                nodes.add(pair[0]);
                nodes.add(pair[1]);
                List<Pair> entry= pairsStartingWithNode.getOrDefault(pair[0], new ArrayList<>());
                entry.add(new Pair(pair[0], pair[1]));
                pairsStartingWithNode.put(pair[0], entry);
            }
        }

    }

    static class Pair {
        int src;
        int target;
        public Pair(int a, int b) {
            src = a;
            target = b;
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph(new int[][]{{1, 3},
                {4, 1},
                {4, 7},
                {5, 9},
                {10,8}});


        Set<Integer> visited = new HashSet<>();
        Stack<Integer> buildOrder = new Stack<>();


        for(Integer n : g.nodes)
        {
            dfs(visited, n, buildOrder,g.pairsStartingWithNode);
        }
        System.out.println(buildOrder);

    }

    public static void dfs(Set<Integer> visited, Integer n, Stack<Integer> s,
                           Map<Integer, List<Pair>> pairsMap) {
        System.out.println(n);
        if(!visited.contains(n)) {
            visited.add(n);
            List<Pair> pairs = pairsMap.get(n);

            if(pairs != null) {
                for(Pair pair : pairs)
                {
                        dfs(visited, pair.target, s, pairsMap);
                }
            }
            s.add(n);
        }
    }

}
