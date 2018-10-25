package Graph;

/**
 * Union Find loop
 *
 * second: Morris Traversal
 *
 * a tree has no loop
 * a tree has n - 1 edges
 */

public class UnionFind1 {



    public int find(int[] parent, int x) {
        int x_root = parent[x];
        while (x_root != parent[x_root]) {  //find current root for x
            x_root = parent[x_root];
        }

        int p = parent[x];
        while (p != parent[p]) {    //change all path nodes' parent to root
            int temp = parent[p];
            parent[p] = x_root;
            p = temp;
        }
        return p;
    }

//    public void union(int[] parent, int x, int y) {
//        int x_root = find(parent, x);
//        int y_root = find(parent, y);
//        if (x_root != y_root) {
//            parent[x_root] = y_root;
//        }
//    }

    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {

        if(n == 0 && edges.length == 0) return true;
        if (n - 1 != edges.length) return false;
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < edges.length; i++) {
            int p0 = find(parent, edges[i][0]), p1 = find(parent, edges[i][1]);
            if (p0 == p1) return false;
            parent[p0] = p1;    //merge x and y to same root
        }
        return true;
    }

    private void print(int[] a) {
        for(int x: a) System.out.print(x + "  ");
    }
}
