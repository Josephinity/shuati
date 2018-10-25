package Graph;

import java.util.*;
/** Amazon OA
 Return the min span tree for a graph
 https://en.wikipedia.org/wiki/Kruskal%27s_algorithm

 */

public class MinSpanningTree {


    // A class to represent a graph edge
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;

        // Comparator function used for sorting edges based on
        // their weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };
    

    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges


    // A utility function to find set of an element i
    // (uses path compression technique)
    int find(int src[], int i)
    {
        // find root and make root as parent of i (path compression)
        int root = src[i];
        while (root != src[root]) {  //find current root for x
            root = src[root];
        }

        int p = src[i];
        while (p != src[p]) {    //change all path nodes' parent to root
            int temp = src[p];
            src[p] = root;
            p = temp;
        }
        return root;
    }

    // A function that does union of two sets of x and y
    void Union(int src[], int x, int y)
    {
        int xroot = find(src, x);
        int yroot = find(src, y);

        // Attach the two trees into one single tree
        src[xroot] = yroot;
    }

    // The main function to construct MST using Kruskal's algorithm
    void KruskalMST()
    {
        Edge result[] = new Edge[V];  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges
        for (i=0; i<V; ++i)
            result[i] = new Edge();

        // Step 1:  Sort all the edges in non-decreasing order of their
        // weight.  If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        Arrays.sort(edge);

        // initialize src array
        int src[] = new int[V];

        for(i = 0; i < V; i++) {
            src[i] = i;
        }


        i = 0;  // Index used to pick next edge
        Edge next_edge;
        // Number of edges to be taken is equal to V-1
        while (e < V - 1)
        {
            // Step 2: Pick the smallest edge. And increment the index
            // for next iteration
            next_edge = edge[i++];

            int x = find(src, next_edge.src);
            int y = find(src, next_edge.dest);

            // If including this edge does't cause cycle, include it
            // in result and increment the index of result for next edge
            if (x != y)
            {
                result[e++] = next_edge;
                Union(src, x, y);
            }
            // Else discard the next_edge
        }

    }



}
