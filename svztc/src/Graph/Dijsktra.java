package Graph;

/**
 DIJSKTRA'S SHORTEST PATH
 */
public class Dijsktra {

    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < visited.length; v++)
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    // Dijkstra's shortest path from src to every other node in graph
    int[] dijkstra(int graph[][], int src) {
        int dist[] = new int[graph.length]; //dist[i] will hold the shortest distance from src to i
        boolean visited[] = new boolean[graph.length];

        // Initialize all distances as INFINITE
        for (int i = 0; i < graph.length; i++) dist[i] = Integer.MAX_VALUE;
        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < graph.length - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed
            int u = minDistance(dist, visited);
            if(u == -1) return dist; //all nodes are processed
            // Mark the picked vertex as processed
            visited[u] = true;
            // Update dist value of the adjacent vertex v of the picked vertex u
            for (int v = 0; v < graph.length; v++)
                // Update dist[v] only if there is an edge from u to v,
                // and total weight of path from u to v through u is smaller than current value of dist[v]
                if (graph[u][v]!= 0 && dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        return dist;
    }

    // A utility function to print the constructed distance array
    void printSolution(int dist[], int n) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < n; i++)
            System.out.println(i+" \t\t "+dist[i]);
    }



    // Driver method
    public static void main (String[] args)
    {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][]{
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        Dijsktra t = new Dijsktra();
        t.dijkstra(graph, 0);
        for(int k: t.dijkstra(graph, 0)) System.out.println(k);

    }

}
